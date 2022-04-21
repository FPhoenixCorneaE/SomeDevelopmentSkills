## Android 多渠道打包



### 一、添加渠道，在 application 的 build.gradle 文件下新增如下配置：

1. ##### defalutConfig 中新增 flavorDimensions

   ```groovy
   android {
       defaultConfig {
           flavorDimensions project.name
       }
   }
   ```

2. ##### 新增 productFlavors

   ```groovy
   android {
       productFlavors {
           dev {
               buildConfigField "String", "Environment", "\"Dev\""
           }
           sit {
               buildConfigField "String", "Environment", "\"Sit\""
           }
           uat {
               buildConfigField "String", "Environment", "\"Uat\""
           }
       }
   }
   ```

3. ##### apk 重命名

   ```groovy
   static def releaseTime() {
       return new Date().format("yyyy-MM-dd-HH-mm", TimeZone.getTimeZone("UTC"))
   }
   
   android {
       applicationVariants.all { variant ->
           variant.outputs.all { output ->
               outputFileName = "${project.name}-${variant.productFlavors[0].name}-${variant.buildType.name}-${defaultConfig.versionName}-${releaseTime()}.apk"
           }
       }
   }
   ```



### 二、打渠道包

1. ##### Android Studio Gralde 视图框中，找到 app --> build --> assemble，双击 ，可以打所有渠道 apk 包(包括 debug 和 release)。

<img src="https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/Android%E5%A4%9A%E6%B8%A0%E9%81%93%E6%89%93%E5%8C%85-1.png" alt="Android多渠道打包-1"  />

2. ##### 打包结果如下图：

![Android多渠道打包-2](https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/Android%E5%A4%9A%E6%B8%A0%E9%81%93%E6%89%93%E5%8C%85-2.png)