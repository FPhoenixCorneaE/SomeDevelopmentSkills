# 日志存储 Mars Xlog 调研：

## 优点：

1、Mars 支持自定义 xlog 的加密部分和长短连协议加解包部分。
2、可独立使用的高性能跨平台日志模块。
3、使用流式方式对单行日志进行压缩，压缩加密后写进作为 log 中间 buffer的 mmap 中。
4、兼顾流畅性、完整性、容错性、安全性这四点的前提下做到：高性能高压缩率、不丢失任何一行日志、避免系统卡顿和 CPU 波峰。



## 缺点：

1、gradle接入使用的日志加密算法是不加密的，只提供了 armeabi 和 x86_64 两种 CPU 架构的 so，如果使用的其他 so 有其他架构的，
需要本地编译编出需要的 so，否则会报 Couldn’t find “xxxx.so”的错误。
2、自定义 xlog 加密和长短连协议需要再本地编译 Mars 才可以，本地编译需要安装cmake、python2.7以及cygwin。
3、Xlog 文件查看需要安装 python2.7.12、openssl windows、python setuptools 工具、python Pip 工具、pyelliptic1.5.7。
4、文件写入模式分异步和同步，Release版本一定要用 AppednerModeAsync， Debug 版本两个都可以，但是使用 AppednerModeSync 可能会有卡顿。
5、Github Issues 里边问题比较多，却少有回答的。



## 注意：

1、在自定义实现时，可以增加函数，但是不能删除头文件中已有的函数，也不能修改头文件中的函数原型。
2、自定义加密算法后，一定要修改解密 xlog 的 python 脚本。修改python的解压逻辑需要注意：因为日志是先压缩后再让你重定义加密的，
所以解密脚本中需要先解密再解压，而且需要注意解密脚本中有两种类型：同步日志和异步日志，注意和你的加密算法对应上。
3、如果你的程序使用了多进程，不要把多个进程的日志输出到同一个文件中，保证每个进程独享一个日志文件。
4、保存 log 的目录请使用单独的目录，不要存放任何其他文件防止被 xlog 自动清理功能误删。
5、debug 版本下建议把控制台日志打开，日志级别设为 Verbose 或者 Debug, release 版本建议把控制台日志关闭，日志级别使用 Info.
6、cachePath这个参数必传，而且要data下的私有文件目录，例如 /data/data/packagename/files/xlog， mmap文件会放在这个目录，
如果传空串，可能会发生 SIGBUS 的crash。