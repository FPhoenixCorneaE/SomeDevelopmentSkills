
**一、引用aar的好处：**

为了**减少apk体积**和**加快gradle编译速度**，比起依赖module，引用**aar**更为友好。
	
<br>

**二、如何生成aar？**

**1.** 使用Terminal命令 gradlew 模块名:clean 以及 gradlew 模块名:assemble,会在模块名/build/outputs/aar文件夹下生成aar文件。

**2.** 直接运行一下项目也可以在上述文件夹生成aar文件。
	
<br>	
	
**三、如何在library模块中正确引用aar？**
	
	
**1.** 在**application**所在模块的build.gradle文件中**allprojects**节点加入如下一段：
	
```
repositories {
    flatDir {
        dirs "$rootDir/aar"
    }
}
```	

**2.** 将aar文件复制到library模块下的aar文件夹；
		
		
**3.** 然后在library模块的build.gradle文件中还要正确引入：
	
- `在android节点下添加：`

	```		
	repositories {
		flatDir {
			dirs 'aar'
		}
	}
	```		
- `dependencies节点：`
	
	```
	dependencies {
		implementation fileTree(include: ['*.jar'], dir: 'libs')
		implementation(name: 'aar名字', ext: 'aar')
	}
	```		
		
<font size="3" color="#ee1234" face="宋体">**有一个需要特别注意的地方：</font>
	
- 引用aar的library模块需要在build.gradle文件添加依赖原aar模块依赖的所有库；
		
- 引用的aar本身有依赖其他aar，需要复制其他aar文件到library模块的aar文件夹下；
		
		
		
		
		