
**在7.0以前的版本获取Uri：**
```
Uri uri = Uri.fromFile(file);
```
这个file文件直接非常简单的转换成"file://xxx/xxx/xxx"的uri格式

**7.0后的版本：**
当把targetSdkVersion指定成24及之上并且在API>=24的设备上运行时。这种方式则会出现FileUriExposedException异常。

--------------------------------------------------------------------------------------------
**原因：**
Android7.0后不再允许在app中把file://Uri暴露给其他app，包括但不局限于通过Intent或ClipData 等方法。
原因在于使用file://Uri会有一些风险，比如：

 - 文件是私有的，接收file://Uri的app无法访问该文件。
 - 在Android6.0之后引入运行时权限，如果接收file://Uri的app没有申请READ_EXTERNAL_STORAGE权限，在读取文件时会引发崩溃。

因此，google提供了FileProvider，使用它可以生成content://Uri来替代file://Uri。

---------------------------------------------------------------------------------------------

**解决方案：**

 1. 在AndroidManifest.xml中加上摄像头、读写磁盘等所需权限，如下：
 
```
<uses-permission android:name="android.permission.CAMERA" />  
<uses-permission android:name="android.permission.RECORD_AUDIO" />  
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />  
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />  
```

 2. 在Application.onCreate()加入如下代码，置入一个不设防的VmPolicy：
 
```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
}
```

 3. 在项目res目录下创建一个xml文件夹，里面创建一个file_paths_public.xml文件,如下：
 
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <paths>
        <!--代表外部存储区域的根目录下的文件 Environment.getExternalStorageDirectory()/DCIM/camera目录-->
        <external-path
            name="mq_DCIM"
            path="DCIM/camera" />

        <!--代表外部存储区域的根目录下的文件 Environment.getExternalStorageDirectory()/pictures/camera目录-->
        <external-path
            name="mq_external_pictures"
            path="pictures/camera" />

        <!--代表app私有的存储区域 Context.getFilesDir()目录下的images目录 /data/user/0/${applicationId}/files/images-->
        <files-path
            name="mq_private_files_images"
            path="images" />

        <!--代表app私有的存储区域 Context.getCacheDir()目录下的images目录 /data/user/0/${applicationId}/cache/images-->
        <cache-path
            name="mq_private_cache_images"
            path="images" />

        <!--代表app外部存储区域根目录下的文件 Context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)目录下的pictures目录-->
        <external-files-path
            name="mq_external_files_pictures"
            path="pictures" />

        <!--name="" URI路径段，取值会隐藏你分享的目录的名字-->
        <!--path="" 你分享的目录的名字-->
        <!--mq_external_download 替代/storage/emulated/0/download/-->
        <external-path
            name="mq_external_download"
            path="download" />
    </paths>
</resources>
```
 
内部的element可以是files-path，cache-path，external-path，external-files-path，external-cache-path，分别对应Context.getFilesDir()，Context.getCacheDir()，Environment.getExternalStorageDirectory()，Context.getExternalFilesDir()，Context.getExternalCacheDir()等几个方法。后来翻看源码发现还有一个没有写进文档的，但是也可以使用的element，是root-path，直接对应文件系统根目录。不过既然没有写进文档中，其实还是有将来移除的可能的。使用的话需要注意一下风险。
 
**注意：**
在file_paths_public.xml 文件中，如果要在同一个存储路径下，指定两个共享的目录，如下所示，那么两个共享路径的name字段取值不应该相同，如果两者相同，那么后面的一行指定的path(/storage/emulated/0/pictures/camera)会覆盖上面一行指定的path(/storage/emulated/0/DCIM/camera)。
 
 4. 在AndroidManifest.xml中加上自定义权限的ContentProvider，如下：
```
<!-- FileProvider配置访问路径，适配7.0及其以上 -->
<provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="${applicationId}.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths_public" />
</provider>
```

**注意：**
android:exported必须设置成false，否则会报异常：
**java.lang.SecurityException: Provider must not be exported**

 5. 具体使用：

```
/**
 * 安装apk
 *
 * @param path apk路径
 */
public static void installApk(String path) {
    //下载完成安装,安装完成后返回显示启动
    File apkFile = new File(path);
    Intent intent = new Intent(Intent.ACTION_VIEW);
    Uri uri;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        String authority = BuildConfig.APPLICATION_ID + ".fileprovider";
        uri = FileProvider.getUriForFile(Utils.getContext(), authority, apkFile);
    } else {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        uri = Uri.fromFile(apkFile);
    }
    intent.setDataAndType(uri, "application/vnd.android.package-archive");
    Utils.getContext().startActivity(intent);
}
```

**注意：**
此处的authority必须跟AndroidManifest.xml处的自定义provider的android:authorities一样，否则会报异常：
**Attempt to invoke virtual method 'android.content.res.XmlResourceParser android.content.pm.PackageItemInfo.loadXmlMetaData(android.content.pm.PackageManager, java.lang.String)' on a null object reference**


**Android7.0适配：**
https://blog.csdn.net/Chay_Chan/article/details/57083383