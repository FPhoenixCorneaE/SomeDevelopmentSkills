# 微信Mars-xlog模块简单集成

> xlog解决的主要问题是在生产环境打日志的问题。（注意直接github搜xlog会将你引导到另一个高stars的项目中，其为java实现的日志美化库，和Logger类似，但比Logger强大。）



Mars项目地址：[Tencent/mars: Mars is a cross-platform network component developed by WeChat. (github.com)](https://github.com/Tencent/mars)

设计思路：[微信终端跨平台组件 mars 系列（一） - 高性能日志模块xlog (qq.com)](https://mp.weixin.qq.com/s/cnhuEodJGIbdodh0IxNeXQ?)



可以高效率的将日志写入文件，对日志进行压缩，加密，之后的日志文件你可以上传到你需要的位置。在进行日志文件的解密，解压缩。

* **高效：**使用c++代码实现，使用mmap进行内存与文件的映射，减少数据拷贝次数，mmap即使进程死亡了也会将数据同步到文件中，保证日志的完整性。

* **低消耗：**使用C++代码实现，可以避免使用java造成的频繁的GC，会对单条日志在写入mmap之前进行压缩，减少了mmap与文件的同步频次。

* **功能丰富：**日志文件压缩，加密，自动清理等功能。

主要的技术点还是在低消耗和高效率上面。

可以在release包中，将写日志对用户体验的影响降的尽量的低。

xlog是提供了加密功能的，回叙会继续说明。



## 一、添加依赖

```groovy
dependencies {
    compile 'com.tencent.mars:mars-xlog:1.2.5'
}
```



## 二、进行初始化，最好方法主工程的MainApplication中

项目说明文档中的初始化方式不知道对应的是那个版本，这是从其对应sample中找到的初始化代码，亲测可用。


```kotlin
private fun initXlog() {
    //加载so库
    System.loadLibrary("c++_shared")
    System.loadLibrary("marsxlog")
    //定义日志文件的储存路径
    val SDCARD: String = Environment.getExternalStorageDirectory().absolutePath
    val logPath: String = SDCARD + "/marssample/log"
    //定义缓存路径
    // this is necessary, or may crash for SIGBUS
    val cachePath: String = Ktx.app.filesDir.absolutePath + "/xlog"
 
    val xlog = Xlog()
    Log.setLogImp(xlog)
    //日志是否输出到logcat
    Log.setConsoleLogOpen(true)
    //开启日志
    Log.appenderOpen(Xlog.LEVEL_DEBUG,//日志打印级别
                     Xlog.AppednerModeAsync, //日志同步方式    
                     cachePath, //缓存路径
                     logPath, //日志储存路径
                     "dbxLog",//日志文件前缀
                       0);//缓存时长
}
```
##  

## 三、直接打Log即可

```kotlin
import com.tencent.mars.xlog.Xlog

Log.e("xlog", "测试测试测试测试测试测试测试")   
```

完成以上步骤，应该可以在logcat中看到正常的日志打印



## 四、在系统进程结束时关闭日志

照搬文档描述，不知道写在哪里，使用`ProcessLifecycleOwner`对进程进行监听，但是收不到`destroy`事件

```kotlin
Log.appenderClose()
```



## 五、获取log日志

从上面的方法不难看出，日志是直接存储到sdcard的公用目录下的，开发过程中，直接使用AndroidStudio就能够获取到日志文件。



## 六，日志文件解压

我们得到的是我们定义的**前缀+日期+.xlog**的日志文件。

因为没有进行加密，使用Mars项目中的**mars/log/crypt/decode_mars_nocrypt_log_file.py**进行解压缩。

解压缩需要**Python2**的环境，可以使用 **Anaconda**,  进行多python环境的切换，Anaconda的使用就不在这里说明了，请自行查询。

切换到Python2后 直接在**terminal**中执行命令

**python decode_mars_nocrypt_log_file.py dbx-20221113.xlog**

即可在xlog文件目录生成对应的解压缩的日志文件。



## 七、常见问题

### 1、“No module named zstandard”。

在**terminal**中使用`pip install zstandard`命令，成功后重新执行命令就可以了。



## 总结：

很牛，但是项目的文档几乎没什么用，介绍的十分潦草，没有对应的Api文档说明，目前还有人维护，但是使用过程中要自己踩坑，网上能找到的集成教程大多因为版本原因会有一些坑，需要自己踩。

————————————————
版权声明：本文为CSDN博主「HanSnowqiang」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/HanSnowqiang/article/details/121355126