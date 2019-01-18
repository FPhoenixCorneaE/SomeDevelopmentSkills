android apk包无法覆盖安装的原因有很多:
比如:
1、包名问题
    解决办法是，保证包名一致。

2、签名问题
    解决办法是，保证包名，签名一致。

3、版本号问题
    解决办法是，需要更新的版本号必须大于已安装apk的版本号，版本号需要在gradle中修改。

4、targetSdkVersion问题
    解决办法是，需要更新的targetSdkVersion必须大于等于已安装apk的targetSdkVersion，
    targetSdkVersion需要在gradle中修改。