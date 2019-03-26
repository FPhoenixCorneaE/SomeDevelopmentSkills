###### 一、环境变量配置：

**1. JAVA_HOME** ：C:\Program Files\Java\jdk1.8.0_171（**JDK路径**）

**2. NDK_HOME** ：D:\Sdk\ndk-bundle（**ndk-bundle路径**）

**3. SDK_HOME** ： D:\Sdk（**SDK路径**）

**4. CLASSPATH** ： .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar

**5. Path** ：%JAVA_HOME%\bin;%JAVA_HOME%\jre;%SDK_HOME%\platform-tools;%NDK_HOME%;

**6.** 项目**local.properties**文件下添加ndk.dir=D\:\\Sdk\\ndk-bundle

##### 二、JNI实现步骤：

**1. 编写带有native声明的方法的java类。**

- 项目的**gradle.properties**配置文件添加

```groovy
android.useDeprecatedNdk=true
```

 - 项目**主module**的build.gradle配置添加

```groovy
android {
    defaultConfig {
        ndk {
			// so库的文件名，可自定义
            moduleName "JniUtil"
        }
        ndk {
            // 设置支持的SO库架构
            // ndk（v17）已不再支持mips、armeabi等CPU架构
            abiFilters /*'armeabi' ,*/ 'x86', 'armeabi-v7a', 'x86_64', 'arm64-v8a'
        }
    }
	
    sourceSets {
        main {
			// so库的目录路径
            jniLibs.srcDirs = ['src/main/jniLibs']
        }
    }
}
```

- 新建带有native声明的方法的JniUtil类，添加加载c库的代码，其中“JniUtil”为so的文件名

```java
public class JniUtil {

    static {
        try {
			// 加载so库，不带前缀"lib"与后缀".so"
            System.loadLibrary("JniUtil");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static native String getJniString();
}
```
    
**2. 使用命令 "javah -jni 包名.类名" 生成.h文件。**

- **terminal**控制台使用命令 "cd app/src/main/java" 切换到java文件夹，然后使用命令 "javah -jni com.example.testjni.JniUtil" ，将会在java文件夹下生成.h文件。
    
**3. 使用C/C++实现本地方法。**

- 在main文件夹下新建jniLibs文件夹，将上个步骤生成的.h文件剪切到该文件夹

- jniLibs文件夹下新建一个名称与.h文件一样的后缀名为.cpp的C/C++Source File

```c
#include <jni.h>
//导入.h文件
#include <com_example_testjni_JniUtil.h>

// C和C++对函数的处理方式是不同的. extern "C"是使C++能够调用C写作的库文件的一个手段,
// 如果要对编译器提示使用C的方式来处理函数的话, 那么就要使用extern "C"来说明.
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_example_testjni_JniUtil_getJniString
        (JNIEnv *env, jclass thiz) {
    return env->NewStringUTF("Hello!TestJni!");
}

#ifdef __cplusplus
}
#endif
```

-  jniLibs文件夹下新建名为Android.mk的MakeFile文件

```makefile
#每个Android.mk文件必须以LOCAL_PATH开头，在整个开发中，它通常被用做定位资源文件，例如，功能宏my-dir提供给编译系统当前的路径。
LOCAL_PATH := $(call my-dir)

#CLEAR_VARS指编译系统提供一个特殊的GUN MakeFile来为你清除所有的LOCAL_XXX变量，LOCAL_PATH不会被清除。
#使用这个变量是因为在编译系统时，所有的控制文件都会在一个GUN Make上下文进行执行，而在此上下文中所有的LOCAL_XXX都是全局的。
include $(CLEAR_VARS)

#LOCAL_MODULE变量是为了确定模块名，并且必须要定义。这个名字必须是唯一的同时不能含有空格。会自动的为文件添加适当的前缀或后缀，
#模块名为“foo”它将会生成一个名为“libfoo.so”文件。
LOCAL_MODULE    := JniUtil

#包含一系列被编译进模块的C 或C++资源文件
LOCAL_SRC_FILES := com_example_testjni_JniUtil.cpp

#指明一个GUN Makefile脚本，并且收集从最近“include$(CLEAR_VARS)”下的所有LOCAL_XXX变量的信息，最后告诉编译系统如何正确的进行编译。
#将会生成一个静态库hello-jni.a文件。
include $(BUILD_SHARED_LIBRARY)
```
- jniLibs文件夹下新建名为Application.mk的MakeFile文件
```makefile
APP_STL := c++_static
APP_CPPFLAGS := -frtti -fexceptions -std=c++0x
APP_ABI := all
APP_PLATFORM := android-28
```
    
**4. 将C/C++编写的文件生成动态连接库。**

 - 项目主module的build.gradle配置添加

```groovy
android {
    externalNativeBuild {
        ndkBuild {
			//Android.mk文件路径
            path '../app/src/main/jniLibs/Android.mk'
        }
    }
}
```

- 使用Gradle执行项目主module的clean、assemble任务

- 将会在项目主module的\build\intermediates\ndkBuild\debug\obj\local目录下生成各种CPU架构的so库文件
    
**5. 编写Java调用代码、进行测试。**

- 项目中调用
```java
Log.i("MainActivity", JniUtil.getJniString());
```
- 把代码注释掉
```groovy
//    externalNativeBuild {
//        ndkBuild {
//            //Android.mk文件路径
//            path '../app/src/main/jniLibs/Android.mk'
//        }
//    }
```
- 将项目主module的\build\intermediates\ndkBuild\debug\obj\local目录下的so库复制到jniLibs文件夹下，删掉各个CPU架构文件夹里边的objs-debug文件夹

- 运行项目，将会在logcat中打印出 "Hello!TestJni!" 的日志