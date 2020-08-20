### 一、定义

		冷启动：当启动应用时，后台没有该应用的进程，这时系统会重新创建一个新的进程分配给该应用，这个启动方式就是冷启动。
		热启动：当启动应用时，后台已有该应用的进程（例：按back键、home键，应用虽然会退出，但是该应用的进程是依然会保留在后台，可进入任务列表查看），所以在已有进程的情况下，这种启动会从已有的进程中来启动应用，这个方式叫热启动。
							
	    因此，冷启动时，先执行Application中的代码，再启动Activity。而热启动，不执行Application的代码，直接启动Activity。
	
		理解了冷启动跟热启动后，就可以知道我们要优化的目标了，一个是Application，一个是SplashActivity，一个是MainActivity。
	
	
### 二、方法论

 1. 埋点先行，有对比就有差距。
 App启动开始时间可以在Application的attachBaseContext方法开始计时。主页面展示的结束时间是在view的第一次调用onDraw()方法，作为主页面的展示时机。
```java		
getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
	@Override
	public boolean onPreDraw() {
		getWindow().getDecorView().getViewTreeObserver().removeOnPreDrawListener(this);
		// do log
		return true;
	}
});
```		

 2. 插件化解决多dex问题。
出现原因：当App中的方法数超过了65536后，就会出现编译不通过。原因是打包流程代码里面，Class：MemberIdsSection有这么一段代码。
解决方案：①官方给出的解决方案是使用MultDex解决。在Application的attachBaseContext中添加代码。可是有一个问题，MultDex.install()这句代码，
			在Android5.0以下手机中执行非常地慢，性能比较差的机子有时候时间多达10s。这是绝对不能忍受的。
			②使用插件化解决多dex问题。目前来讲最好的解决方案。Android5.0以下的手机也能流畅运行。
            
 3. UI加载延迟化。
主界面太复杂了，可以减少一些界面绘制，等到界面展示出来，再进行绘制其他UI。这样看起来启动速度就快多了。

 4. 耗时非UI操作异步化。
在UI线程执行超过5S的任务，那么就会导致ANR。新手常犯的错误有在UI线程执行文件操作、调用耗时API(如ActivityManager获取应用信息等)、反射、网络操作等等。

 5. 减少Activity跳转层级。 
常见的启动流程是这样的，Application->SplashActivity->MainActivity。
糟糕一点的启动流程是这样的。Application->MainActivity->SplashActivity->MainActivity。
经过优化后的启动流程可以是Application->MainActivity。SplashActivity用SplashView替代。
			
 6. 布局优化。
布局优化常用的工具有HierarchyViewer，能够可视化的角度直观地获得UI布局设计结构和各种属性的信息。帮助我们优化布局。
布局时要准守下面的一些规则：
①尽量多使用RelativeLayout、LinearLayout，不要使用绝对布局AbsoluteLayout；
②将可复用的组件抽取出来并通过< include />标签使用；
③使用< ViewStub />标签来加载一些不常用的布局；
④使用< merge />标签减少布局的嵌套层次；
	
 7. 数据监控。性能优化是一件长期的任务。
①发版前的数据监控
其实也就是在启动流程的关键方法上面添加时间日志。这里要注意下，就是每次发版前用相同的手机记录下启动流程的各个方法的运行时间。保持单一变量，这样子哪个方法的耗时明显增加了，那么就可以看出来。但其实你会发现这样添加时间统计会导致代码很难看。所以，还是用AspectJ切面编程框架来实现耗时统计。真正做到代码无耦合。
②发版后的数据监控
将收上面的埋点数据做成每日报表，每天观察数据。在大量数据支撑下，平均值会趋于稳定。如果发版后，这个平均值明显上涨，那么就是本次发版代码有问题。这时候回到第1点。进行代码排查。

 8. 终极优化
最后一步只是理想状态，要做到这点非常困难。就是在启动流程中，始终保持单线程执行。保持CPU始终执行UI线程，不调度到其他线程去。基本上做不到，但要朝着这个方向前进。
			
参考：App优化之启动速度优化https://zhuanlan.zhihu.com/p/24867509
			
	
### 三、主题化的启动屏幕

通过主题化app的启动屏幕来改善启动体验。这样整个app的启动和接下来的操作会显得更加连贯。但这样只是将Activity的慢启动问题隐藏了。
使用Activity的windowBackground主题属性来为启动的Activity提供一个简单的drawable。
		
drawable布局文件layerlist_splash：
```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android"
			android:opacity="translucent">
	<item android:drawable="@android:color/white" />
	<item>
		<bitmap 
			android:gravity="center"
            android:scaleType="centerCrop"
			android:src="@mipmap/pic_splash"
			android:tileMode="disabled" />
	</item>
</layer-list>
```			
Manifest file:
```xml
<activity ...
	android:theme="@style/SplashTheme" />
```			
style theme文件SplashTheme：
```xml
<!--启动页主题-->
<style name="SplashTheme" parent="Theme.AppCompat">
	<item name="windowActionBar">false</item>
	<item name="windowNoTitle">true</item>
	<item name="android:windowFullscreen">true</item>
	<item name="android:windowContentOverlay">@null</item>
	<item name="android:windowBackground">@drawable/layerlist_splash</item>
</style>
```			
		
然后在代码中将主题切换回app的主题，最简单的方式是在super.onCreate() 和setContentView()方法之前调用 setTheme(R.style.AppTheme)
        
        
### 四、MultiDex

由于Android 5.0以下使用的Dalvik虚拟机天生对MultiDex支持不好，导致在4.4（及以下）的系统上，如果使用了 	MultiDex做为分包方案，启动速度可能会慢的多，实际数值跟dex文件的大小、数量有关，估计会慢300~500ms。
        
<h7> **解决方案：**
        
  - 限制APP在5.0以上使用：目前大多数用户已经在使用Android 5.0以上的版本了，当然，还有很多4.4用					户，很多APP也是只支持4.4以上（比如：百度APP），为了用户体验，可以考虑舍弃一部分用户

- 优化方法数：尽量避免方法超过65535个，同时可以开启Release配置的Minify选项，打包时删掉没有用的方法，不过如果框架引用的较多，基本没效果

- 少用一些不必要的框架：有些框架功能很强大，但不一定都能用得上，引进来会新增很多的方法，导致必须开启MultiDex，可以自己造轮子，或者找轻量级的框架

- 慎用Kotlin：由于Kotlin现在还没有内置在Android系统中，所以APP如果使用了Kotlin，可能会导致引入很多的Kotlin方法，导致必须分割Dex，这个有待Google在Android P中解决

- Dex懒加载：在APP功能日益复杂的今天，MultiDex几乎是已经无法避免了，为了启动速度的优化，可以将启动时必需的方法，放在主Dex中（即classes.dex），方法是在Gradle脚本中配置multiDexKeepFile或multiDexKeepProguard属性（代码如下），详见：官方文档，待App启动完成后，再使用MultiDex.install来加载其他的Dex文件。这种方法风险比较高，而且实现成本比较大，如果启动依赖的库比较多，还是无法实现。

```groovy
android { 
	buildTypes { 
		release { 
			multiDexKeepFile file('multidex-config.txt')  // multiDexKeepFile规则 
			multiDexKeepProguard file('multidex-config.pro')  // 类似ProGuard的规则 
		}
	} 
}
```

- 插件化或H5/React Native方案：即端只提供Native调用能力和容器，业务由插件来做，本地只需要加载基础的Native能力相关类即可，其他完全下发，或内置成资源文件调用


### 五、Glide及其他图片框架

Glide是一个很好用的图片加载框架，除了常用的图片加载、缓存功能以外，Glide支持对网络层进行定制，比如换成OkHttp来支持HTTP 2.0。不过，如果在追求启动速度的情况下，在Splash页或主界面加载某一张图片时，往往是第一次使用Glide，由于Glide没有初始化，会导致这次图片加载的时间比较长（不管本地还是网络），特别是在其他操作也在同时抢占CPU资源的时候，慢的特别明显！而后面再使用Glide加载图片时，还是比较快的。

Glide初始化耗时分析：Glide的初始化会加载所有配置的Module，然后初始化RequestManager（包括网络层、工作线程等，比较耗时），最后还要应用一些解码的选项（Options）。

<h7> **解决方案：**
在Application的onCreate方法中，在工作线程调用一次GlideApp.get(this)。

```java
@Override
public void onCreate() {
    super.onCreate();
    Executors.newSingleThreadExecutor().execute(new Runnable() {
        @Override
        public void run() {
            //获取一个Glide对象，Glide内部会进行初始化操作
            GlideApp.get(getContext());
        }
    });
}
```


### 六、GreenDAO和其他数据库框架

greenDAO实现了一种ORM框架，数据库基于SQLite，使用起来很方便，不需要自己写SQL语句、控制并发和事务等等，其他常见的数据库框架如：Realm、DBFlow等等，使用起来也很方便，但他们的初始化，尤其是需要升级、迁移数据时，往往会带来不小的CPU和I/O开销，一旦数据量比较多（比如：很长时间的聊天记录、浏览器浏览历史记录等），往往都需要专门一个界面来告知用户：APP正在做数据处理工作。所以，如果为了提高APP启动速度，避免在APP启动时做数据库的耗时任务，很有必要！

<h7> **解决方案：**

- 必要数据避免使用数据库：如果首屏的展示内容需要根据配置来决定，那么干脆放弃数据库存储和读取，直接放在文件、SharedPreference里面，特别是多组键值对的读取，如果使用数据库，在除过初始化占用的时间以后，可能还需要30~50ms来完成（因为需要多次读取），而如果存在SharedPreference中，即使是转换成JSON并解析，可能也就在10ms之内

- 数据库预先异步初始化：使用greenDAO时，预先初始化很有必要，可以保证在第一次读取数据库时，不占用主线程资源，防止拖慢启动速度，具体做法如下：

```java
// Application
override fun onCreate() { 
	super.onCreate() 
	// 使用Anko提供的异步工作协程，或者自行创建一个并发线程池 
	doAsync { 
		DbManager.daoSession  // 获取一次greenDao的DaoSession实例化对象即可 
	} 
} 

// DBManager（数据库相关单例） 
object DbManager {
	// greenDAO的DaoMaster，用来初始化数据库并建立连接 
	private val daoMaster: DaoMaster by lazy { 
		val openHelper = DaoMaster.OpenHelper(
		ContextUtils.getApplicationContext(), "Test.db") 
		DaoMaster(openHelper.writableDatabase) 
	} 

	// 具体的数据库会话 
	val daoSession: DaoSession by lazy { 
		daoMaster.newSession() 
	} 
}
```



	
	
	
	
	
	
	

