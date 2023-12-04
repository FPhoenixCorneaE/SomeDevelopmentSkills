# VersionCatalog依赖库统一版本号管理技术分析调研：

## AGP 7.0.0以上依赖库统一版本号管理。
> <font color=red><b>注意:</b></font> 该功能需要把`gradle`文件夹下的`gradle-wrapper.properties`中的`gradle`版本改为 <font color=red>7.4.2</font> 以上，否则无法解析。代码提示支持仅限于`build.gradle.kts`。

### Version Catalog的特性：

* 对所有module可见，可统一管理所有module的依赖
* 支持声明依赖bundles，即总是一起使用的依赖可以组合在一起
* 支持版本号与依赖名分离，可以在多个依赖间共享版本号
* 支持在单独的libs.versions.toml文件中配置依赖
* 支持在项目间共享依赖

<br>

### 目前的方案：

1. 使用循环优化Gradle依赖管理。
> a. 在项目根目录下创建一个`config.gradle`文件，然后将项目所有需要用到的依赖放到该文件里：
> `ext {
>	 android = []
>	 dependencies = []
> }`
> b. 在`build.gradle`文件中引用该文件：
> `apply from:"config.gradle"`
> c. 在`build.gradle`的`dependencies`中添加依赖：
> `dependencies {
>	 implementation project.ext.xxx
> }`

2. 使用`buildSrc`管理Gradle依赖。
3. 使用`includeBuild`统一配置依赖版本。
4. 使用`Version Catalog`统一配置依赖版本。

<br>

### 使用Version Catalog：

1. 首先在`settings.gradle`里面声明启动**VERSION_CATALOGS**功能：

```groovy
// 开启 VERSION_CATALOGS
enableFeaturePreview("VERSION_CATALOGS")
```

2. 然后在`settings.gradle`里面声明引入**maven**插件**catalog**库：

```groovy
dependencyResolutionManagement {
    versionCatalogs {
        deps {
            from("com.infore.robot:version-catalog:latest")
        }
    }
}
```

3. 使用`deps`依赖库：
```groovy
dependencies {

    implementation(deps.androidx.core.ktx)
    implementation(deps.androidx.appcompat)
    implementation(deps.material)
    implementation(deps.androidx.constraintlayout)
    testImplementation(deps.test.junit.junit)
    androidTestImplementation(deps.test.junit.ktx)
    androidTestImplementation(deps.test.espresso.core)
}
```