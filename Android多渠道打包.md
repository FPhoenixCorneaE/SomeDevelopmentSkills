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

1. ##### Android Studio Gralde 视图框中，找到app->build->assemble，双击 ，可以打所有渠道apk包（包括debug和release）。

<img src="D:\workspace\github\SomeDevelopmentSkills\预览图\Android多渠道打包-1.png" alt="image-20220421112702233"  />

2. ##### 打包结果

![image-20220421113500590](D:\workspace\github\SomeDevelopmentSkills\预览图\Android多渠道打包-2.png)