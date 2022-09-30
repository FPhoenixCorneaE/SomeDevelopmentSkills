# 完美解决Direct local .aar file dependencies are not supported when building an AAR



最近gradle升级了，然后编译就出现了这个错误

```groovy
  Direct local .aar file dependencies are not supported when building an AAR.
```

然后求助了百度，给出了下面解决方案

在module的`build.gradle`文件中增加如下内容：

```groovy
android {
      ........
   repositories {
      flatDir {
            dirs 'libs'
        }
   }
}

dependencies {
     ....
    implementation(name: 'BaiduLBS_AndroidSDK_Lib', ext: 'aar')
}
```

这样子确实可以了，其实就是将libs作为本地仓库，然后引用参考下的库文件。但是编译时候会有这个提示

```groovy
  Using flatDir should be avoided because it doesn't support any meta-data formats.
```

我是个完美的人，网上又中了一圈，提出File->New->New Module -> Import .jar/.aar。

然后在`build.gradle`文件中引入依赖`implementation project(path: ':xxxxx')`

在旧版本的AndroidStudio中是可以的，在新版的AndroidStudio中已经移除了这个功能，你奶奶的也不知道为啥。
我也懒得翻看更新日志了。所以我用旧版本导入一个aar作为一个module。导入后发现就多了一个build.gradle文件。



综上总结，在高版本的Android Studio并且使用了版本的gradle出现了上述问题可以按着如下引用

1. 在你工程根目录下新建一个文件夹YouLib，将你的aar文件放入，然后在该目录下新建一个build.gradle文件

![LocalRepo AAR-1](https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/LocalRepo%20AAR-1.png)

​	2. 在settings.gradle 导入该工程

```groovy
include ':YouLib'
```

​	3. 在你需要依赖的工程里面的build.gradle中增加依赖

```groovy
implementation project(path: ':YouLib') // 这里需要注意的是，YouLib是你aar库所在文件夹
```

当然如果你有很多aar库，那么你需要在根目录创建一个LocalRepo目录，然后将你不同的aar库放在不同文件夹下。在setting.gradle分别导入

![LocalRepo AAR-2](https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/LocalRepo%20AAR-2.png)

![LocalRepo AAR-3](https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/LocalRepo%20AAR-3.png)



**本文作者**：**[拜雨](https://www.cnblogs.com/baiyuas/p/14383723.html)**
**本文链接**：https://www.cnblogs.com/baiyuas/p/14383723.html
**关于博主**：评论和私信会在第一时间回复。或者[直接私信](https://msg.cnblogs.com/msg/send/baiyuas)我。
**版权声明**：本博客所有文章除特别声明外，均采用 [BY-NC-SA](https://creativecommons.org/licenses/by-nc-nd/4.0/) 许可协议。转载请注明出处！
**声援博主**：如果您觉得文章对您有帮助，可以点击文章右下角**【[推荐](javascript:void(0);)】**一下。您的鼓励是博主的最大动力！