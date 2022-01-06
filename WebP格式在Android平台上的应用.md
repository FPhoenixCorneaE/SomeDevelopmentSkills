# WebP格式在Android平台上的应用：

### 一、背景

​	早在2010年，Google便推出了WebP图片格式，相较于JPEG格式，它拥有更高的压缩率，并在次年增加了对动画和透明度的支持。
在Android平台上，Google自Android4.0（API level 14）开始提供支持WebP，Android 4.3开始提供透明度支持。Google
发布的Android Studio 2.3 版本中则在工具层面上提供了更加完备的支持：支持WebP格式的预览以及PNG,JPEG,BMP和WebP格式之间的
转换。

### 二、格式转换

##### 1.Android Studio

Android Studio 2.3起可以直接将JPEG/PNG格式转换成WebP格式，具体方法可参考官方指引: 
</br>
[Convert Images to WebP](https://developer.android.com/studio/write/convert-webp)

注意：9-patch 文件无法转换为 WebP 图片。转换器工具总是会自动跳过 9-patch 图片。

##### 2.NOS

如果使用网易NOS提供的图片，则只需在url加一个参数“&type=webp”。

##### 3.官方相互转换工具

WebP download page：https://storage.googleapis.com/downloads.webmproject.org/releases/webp/index.html
</br>
可参考：https://blog.csdn.net/lmj623565791/article/details/53240600

### 三、优点

##### 1.压缩率

按照Google官方的说法是，将JPEG/PNG无损转换成WebP可以减少25%~35%的体积，肉眼上却几乎看不出区别。

##### 2.兼容性

1）无论网络图片、本地图片、透明背景图片还是Gif图片，从图片质量来看，WebP格式图片并没有因为更高的压缩率而出现损耗。
</br>
2）根据Android Profiler的Memory情况来看，WebP格式图片和JPEG/PNG格式图片二者总体差距不明显。

##### 3.WebP支持24位RGB和8位透明通道，GIF仅支持8位色彩及1位透明度。

##### 4.WebP支持无损和有损两种模式，而且对于动态图，能同时结合有损和无损的图片。而GIF仅支持无损的压缩。WebP的有损压缩技术也更好地适应从现实世界视频中创建的动图。

##### 5.WebP相比GIF占用更小的空间。Animated GIFs转换为有损WebP减少64%，转换成无损WebP减少19%，这对移动网络十分重要。

##### 6.WebP使用更短的解码时间，WebP所用解码时间是GIF的57%。

### 四、缺点

##### 1.透明背景图片

Android 4.3以下的设备，使用Glide加载是不支持WebP格式图片的。据了解，Facebook 的 Fresco 在WebP的支持方面做了些工作。

##### 2.动图

目前Android本身没有提供WebP动图支持，不过Fresco已经支持了。可将直接GIF格式动图直接转换成WebP格式：
</br>
[GIF to WebP converter](https://ezgif.com/gif-to-webp)

##### 3.WebView

在WebView上，透明背景的WebP，其表现与Native一致，Android 4.3 以下版本并不支持。

### 五、总结

WebP格式在Android平台上应用，优势主要是它能以更小的体积展示质量相近的图片，这意味着节省更多的用户数据流量，更快的图片下载速度，更少的磁盘存储空间占用。
它的局限主要是要在Android 4.3及以上才能完美支持，但是目前Android 4.3 以下还有一定用户量并不能完全放弃。

解决方案主要有：
1. ##### 对版本进行判断：Android 4.3以下采用传统的图片格式，Android 4.3及以上启用WebP格式；
2. ##### 使用Fresco 等第三方支持方案。
3. ##### 使用so库：

  webp-android-backport：https://github.com/alexey-pelykh/webp-android-backport
  </br>
  webp-android：https://github.com/EverythingMe/webp-android
