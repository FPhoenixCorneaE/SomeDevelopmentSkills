<font size="5" color="#000000" face="隶书">**一、SO库类型和CPU架构类型**</font>

不同CPU架构的设备需要用不同类型SO库。
	
早期的Android系统几乎只支持ARM的CPU架构，不过现在至少支持以下七种不同的CPU架构：ARMv5，ARMv7，x86，MIPS，ARMv8，MIPS64和x86_64。每一种CPU类型都对应一种ABI（Application Binary Interface），“armeabi-v7a”文件夹前面的“armeabi”指的就是ARM这种类型的ABI，后面的“v7a”指的是ARMv7。这7种CPU类型对应的SO库的文件夹名是：armeabi，armeabi-v7a，x86，mips，arm64-v8a，mips64，x86_64。
	
	
不同类型的移动设备在运行APP时，需要加载自己支持的类型的SO库，不然就GG了。通过 Build.SUPPORTED_ABIS 我们可以判断当前设备支持的ABI，不过一般情况下，不需要开发者自己去判断ABI，android系统在安装APK的时候，不会安装APK里面全部的SO库文件，而是会根据当前CPU类型支持的ABI，从APK里面拷贝最合适的SO库，并保存在APP的内部存储路径的 libs 下面。（这里说一般情况，是因为有例外的情况存在，比如我们动态加载外部的SO库的时候，就需要自己判断ABI类型了。）
	
	
`一种CPU架构　=　一种对应的ABI参数　=　 一种对应类型的SO库`
	
<br>
	
<font size="5" color="#000000" face="隶书">**二、使用SO库时要注意的一些问题**</font>

**1. 别把SO库放错地方**

SO库其实都是APP运行时加载的，也就是说APP只有在运行的时候才知道SO库文件的存在，这就无法通过静态代码检查或者在编译APP时检查SO库文件是否正常。所以，Android开发对SO库的存放路径有严格的要求。

使用SO库的时候，除了“armeabi-v7a”等文件夹名需要严格按照规定的来自外，SO库要放在项目的哪个文件夹下也要按照套路来，以下是一些总结：

-	Android Studio 工程放在 jniLibs/xxxabi 目录中（当然也可以通过在build.gradle文件中的设置jniLibs.srcDir属性自己指定）；

-	Eclipse 工程放在 libs/xxxabi 目录中（这也是使用ndk-build命令生成SO库的默认目录）；

-	aar 依赖包中位于 jni/ABI 目录中（SO库会自动包含到引用AAR压缩包到APK中）；

-	最终构建出来的APK文件中，SO库存在 lib/xxxabi 目录中（也就是说无论你用什么方式构建，只要保证APK包里SO库的这个路径没错就没问题）；

-	通过 PackageManager 安装后，在小于 Android 5.0 的系统中，SO库位于 APP 的 nativeLibraryPath 目录中；在大于等于 Android 5.0 的系统中，SO库位于 APP 的 nativeLibraryRootDir/CPU_ARCH 目录中；
			
			
**2. 尽可能提供CPU支持的最优SO库**
	
当一个应用安装在设备上，只有该设备支持的CPU架构对应的SO库会被安装。但是，有时候，设备支持的SO库类型不止一种，比如大多的X86设备除了支持X86类型的SO库，还兼容ARM类型的SO库（目前应用市场上大部分的APP只适配了ARM类型的SO库，X86类型的设备如果不能兼容ARM类型的SO库的话，大概要嗝屁了吧）。

所以如果你的APK只适配了ARM类型的SO库的话，还是能以兼容的模式在X86类型的设备上运行（比如华硕的平板），但是这不意味着你就不用适配X86类型的SO库了，因为X86的CPU使用兼容模式运行ARM类型的SO库会异常卡顿。
		
		
**3. 注意SO库的编译版本**

除了要注意使用了正确CPU类型的SO库，也要注意SO库的编译版本的问题。虽然现在的Android Studio支持在项目中直接编译SO库，但是更多的时候我们还是选择使用事先编译好的SO库，这时就要注意了，编译APK的时候，我们总是希望使用最新版本的build-tools来编译，因为Android SDK最新版本会帮我们做出最优的向下兼容工作。

但是这对于编译SO库来说就不一样了，因为NDK平台不是向下兼容的，而是向上兼容的。应该使用app的minSdkVersion对应的版本的NDK标本来编译SO库文件，如果使用了太高版本的NDK，可能会导致APP性能低下，或者引发一些SO库相关的运行时异常，比如“UnsatisfiedLinkError”，“dlopen: failed”以及其他类型的Crash。

一般情况下，我们都是使用编译好的SO库文件，所以当你引入一个预编译好的SO库时，你需要检查它被编译所用的平台版本。
		
		
**4. 尽可能为每种CPU类型都提供对应的SO库**

比如有时候，因为业务的需求，我们的APP不需要支持AMR64的设备，但这不意味着我们就不用编译ARM64对应的SO库。举个例子，我们的APP只支持armeabi-v7a和x86架构，然后我们的APP使用了一个第三方的Library，而这个Library提供了AMR64等更多类型CPU架构的支持，构建APK的时候，这些ARM64的SO库依然会被打包进APK里面，也就是说我们自己的SO库没有对应的ARM64的SO库，而第三方的Library却有。这时候，某些ARM64的设备安装该APK的时候，发现我们的APK里带有ARM64的SO库，会误以为我们的APP已经做好了AMR64的适配工作，所以只会选择安装APK里面ARM64类型的SO库，这样会导致我们自己项目的SO库没有被正确安装（虽然armeabi-v7a和x86类型的SO库确实存在APK包里面）。

这时正确的做法是，给我们自己的SO库也提供AMR64支持，或者不打包第三方Library项目的ARM64的SO库。使用第二种方案时，可以把APK里面不需要支持的ABI文件夹给删除，然后重新打包，而在Android Studio下，则可以通过以下的构建方式指定需要类型的SO库。

```groovy
productFlavors {
	flavor1 {
		ndk {
			abiFilters "armeabi-v7a"
			abiFilters "x86"
			abiFilters "armeabi"
		}
	}
	flavor2 {
		ndk {
			abiFilters "armeabi-v7a"
			abiFilters "x86"
			abiFilters "armeabi"
			abiFilters "arm64-v8a"
			abiFilters "x86_64"
		}
	}
}
```

需要说明的是，如果我们的项目是SDK项目，我们最好提供全平台类型的SO库支持，因为APP能支持的设备CPU类型的数量，就是项目中所有SO库支持的最少CPU类型的数量（使用我们SDK的APP能支持的CPU类型只能少于等于我们SDK支持的类型）。
		
		
**5. 不要通过“减少其他CPU类型支持的SO库”来减少APK的体积**

确实，所有的x86/x86_64/armeabi-v7a/arm64-v8a设备都支持armeabi架构的SO库，因此似乎移除其他ABIs的SO库是一个减少APK大小的好办法。但事实上并不是，这不只影响到函数库的性能和兼容性。

X86设备能够很好的运行ARM类型函数库，但并不保证100%不发生crash，特别是对旧设备，兼容只是一种保底方案。64位设备（arm64-v8a, x86_64, mips64）能够运行32位的函数库，但是以32位模式运行，在64位平台上运行32位版本的ART和Android组件，将丢失专为64位优化过的性能（ART，webview，media等等）。

通过减少其他CPU类型支持的SO库来减少APK的体积不是很明智的做法，如果真的需要通过减少SO库来做APK瘦身，我们也有其他办法。
		
		
<font size="5" color="#000000" face="隶书">**三、减少SO库体积的正确姿势**</font>

**1. 构建特定ABI支持的APK**

我们可以构建一个APK，它支持所有的CPU类型。但是反过来，我们可以为每个CPU类型都单独构建一个APK，然后不同CPU类型的设备安装对应的APK即可，当然前提是应用市场得提供用户设备CPU类型设别的支持，就目前来说，至少PLAY市场是支持的。

Gradle可以通过以下配置生成不同ABI支持的APK（引用自别的文章，没实际使用过）：

```groovy
android {
	...
	splits {
		abi {
			enable true
			reset()
			include 'x86', 'x86_64', 'armeabi-v7a', 'arm64-v8a' //select ABIs to build APKs for
			universalApk true //generate an additional APK that contains all the ABIs
		}
	}

	// map for the version code
	project.ext.versionCodes = ['armeabi': 1, 'armeabi-v7a': 2, 'arm64-v8a': 3, 'mips': 5, 'mips64': 6, 'x86': 8, 'x86_64': 9]

	android.applicationVariants.all { variant ->
		// assign different version code for each output
		variant.outputs.each { output ->
			output.versionCodeOverride =
					project.ext.versionCodes.get(output.getFilter(com.android.build.OutputFile.ABI), 0) * 1000000 + android.defaultConfig.versionCode
		}
	}
}
```

 
**2. 从网络下载当前设备支持的SO库**

使用Android的动态加载技术，可以加载外部的SO库，所以我们可以从网络下载SO库文件并加载了。我们可以下载所有类型的SO库文件，然后加载对应类型的SO库，也可以下载对应类型的SO库然后加载，不过无论哪种方式，我们最好都在加载SO库前，对SO库文件的类型做一下判断。

我个人的方案是，存储在服务器的SO库依然按照APK包的压缩方式打包，也就是，SO库存放在APK包的 libs/xxxabi 路径下面，下载完带有SO库的APK包后，我们可以遍历libs路径下的所有SO库，选择加载对应类型的SO库。

具体实现代码看上去像是：

```java
/**
 * 将一个SO库复制到指定路径，会先检查改SO库是否与当前CPU兼容                                                                                           
 *                                                                                                                                                      
 * @param sourceDir     SO库所在目录                                                                                                                    
 * @param so            SO库名字                                                                                                                        
 * @param destDir       目标根目录                                                                                                                      
 * @param nativeLibName 目标SO库目录名                                                                                                                  
 * @return                                                                                                                                              
 */                                                                                                                                                     
public static boolean copySoLib(File sourceDir, String so, String destDir, String nativeLibName) throws IOException {                                   
																																						
	boolean isSuccess = false;                                                                                                                          
	try {                                                                                                                                               
		LogUtil.d(TAG, "[copySo] 开始处理so文件");                                                                                                      
																																						
		if (Build.VERSION.SDK_INT >= 21) {                                                                                                              
			String[] abis = Build.SUPPORTED_ABIS;                                                                                                       
			if (abis != null) {                                                                                                                         
				for (String abi : abis) {                                                                                                               
					LogUtil.d(TAG, "[copySo] try supported abi:" + abi);                                                                                
					String name = "lib" + File.separator + abi + File.separator + so;                                                                   
					File sourceFile = new File(sourceDir, name);                                                                                        
					if (sourceFile.exists()) {                                                                                                          
						LogUtil.i(TAG, "[copySo] copy so: " + sourceFile.getAbsolutePath());                                                            
						isSuccess = FileUtil.copyFile(sourceFile.getAbsolutePath(), destDir + File.separator + nativeLibName + File.separator + so);    
						//api21 64位系统的目录可能有些不同                                                                                              
						//copyFile(sourceFile.getAbsolutePath(), destDir + File.separator +  name);                                                     
						break;                                                                                                                          
					}                                                                                                                                   
				}                                                                                                                                       
			} else {                                                                                                                                    
				LogUtil.e(TAG, "[copySo] get abis == null");                                                                                            
			}                                                                                                                                           
		} else {                                                                                                                                        
			LogUtil.d(TAG, "[copySo] supported api:" + Build.CPU_ABI + " " + Build.CPU_ABI2);                                                           
																																						
			String name = "lib" + File.separator + Build.CPU_ABI + File.separator + so;                                                                 
			File sourceFile = new File(sourceDir, name);                                                                                                
																																						
			if (!sourceFile.exists() && Build.CPU_ABI2 != null) {                                                                                       
				name = "lib" + File.separator + Build.CPU_ABI2 + File.separator + so;                                                                   
				sourceFile = new File(sourceDir, name);                                                                                                 
																																						
				if (!sourceFile.exists()) {                                                                                                             
					name = "lib" + File.separator + "armeabi" + File.separator + so;                                                                    
					sourceFile = new File(sourceDir, name);                                                                                             
				}                                                                                                                                       
			}                                                                                                                                           
			if (sourceFile.exists()) {                                                                                                                  
				LogUtil.i(TAG, "[copySo] copy so: " + sourceFile.getAbsolutePath());                                                                    
				isSuccess = FileUtil.copyFile(sourceFile.getAbsolutePath(), destDir + File.separator + nativeLibName + File.separator + so);            
			}                                                                                                                                           
		}                                                                                                                                               
																																						
		if (!isSuccess) {                                                                                                                               
			LogUtil.e(TAG, "[copySo] 安装 " + so + " 失败 : NO_MATCHING_ABIS");                                                                         
			throw new IOException("install " + so + " fail : NO_MATCHING_ABIS");                                                                        
		}                                                                                                                                               
																																						
	} catch (IOException e) {                                                                                                                           
		e.printStackTrace();                                                                                                                            
		throw e;                                                                                                                                        
	}                                                                                                                                                   
																																						
	return true;                                                                                                                                        
}    
```


<font size="5" color="#000000" face="隶书">**四、总结**</font>

- **一种CPU架构 = 一种ABI = 一种对应的SO库；**

- **加载SO库时，需要加载对应类型的SO库；**

- **尽量提供全平台CPU类型的SO库支持；**

题外话，SO库的使用本身就是一种最纯粹的动态加载技术，SO库本身不参与APK的编译过程，使用JNI调用SO库里的Native方法的方式看上去也像是一种“硬编程”，Native方法看上去与一般的Java静态方法没什么区别，但是它的具体实现却是可以随时动态更换的（更换SO库就好），这也可以用来实现热修复的方案，与Java方法一旦加载进内存就无法再次更换不同，Native方法不需要重启APP就可以随意更换。

出于安全和生态控制的原因，Google Play市场不允许APP有加载外部可执行文件的行为，一旦你的APK里被检查出有额外的可执行文件时就不好玩了，所以现在许多APP都偷偷把用于动态加载的可执行文件的后缀名换成“.so”，这样被发现的几率就降低了，因为加载SO库看上去就是官方合法版本的动态加载啊（不然SO库怎么工作），虽然这么做看起来有点掩耳盗铃。
	
	
	
转载自：https://blog.csdn.net/OnafioO/article/details/74974364







