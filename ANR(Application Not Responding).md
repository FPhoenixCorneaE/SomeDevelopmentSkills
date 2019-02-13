**ANR定义：**

在Android上，如果你的应用程序有一段时间响应不够灵敏，系统会向用户显示一个对话框，这个对话框称作应用程序无响应（ANR：Application Not Responding）对话框。用户可以选择“等待”而让程序继续运行，也可以选择“强制关闭”。
所以一个流畅的合理的应用程序中不能出现ANR，而让用户每次都要处理这个对话框。因此，在程序里对响应性能的设计很重要，这样系统不会显示ANR给用户。

**ANR常见类型：**

 - KeyDispatchTimeout 
 按键或触摸事件在特定时间内无响应(谷歌default 5s，MTK平台上是8s) --主要类型
 
 - BroadcastTimeout 
 BroadcastReceiver在特定时间内无法处理完成(前台10s，后台60s)
 
 - ServiceTimeout
 Service在特定时间内无法处理完成(前台20s，后台200s) --小概率类型
 
 - PublishingContentProvidersTimeout 
 ContentProvider的publish在特定时间内没进行完(10s)

**如何避免ANR？**

 1. 避免在各个组件的生命周期函数做太耗时的操作：

	①Activity应该在它的关键生命周期方法（如onCreate()和onResume()）里尽可能少的去做创建操作。	
    
	②潜在的耗时操作，例如网络或数据库操作，或者高耗时的计算如改变位图尺寸，应该在子线程里（或者以数据库操作为例，通过异步请求的方式）来完成。主线程应该为子线程提供一个Handler，以便完成时能够提交给主线程。
    
    ③即使对于后台Service或者ContentProvider来讲，应用在后台运行时候其onCreate()时候不会有用户输入引起事件无响应ANR，但其执行时间过长也会引起Service的ANR和ContentProvider的ANR。

 2. 避免在BroadcastReceiver里做耗时的操作或计算：
 一种解决方案是直接开的异步线程执行，但此时应用可能在后台，系统优先级较低，进程很容易被系统杀死，所以可以选择开个IntentService去执行相应操作，即使是后台Service也会提高进程优先级，降低被杀可能性。
 
3. 避免在Intent Receiver里启动一个Activity：
如果响应Intent广播需要执行一个耗时的动作的话，应用程序应该启动一个Service。如果你的应用程序在响应Intent广播时需要向用户展示什么，你应该使用Notification Manager来实现。在后台里做小的、琐碎的工作如保存设定或者注册一个Notification。
	
 4. 避免主线程读取数据：
 Android不允许主线程从网络读数据，但系统允许主线程从数据库或者其他地方获取数据，但这种操作ANR风险很大， 也会造成掉帧等，影响用户体验。
 
 	①避免在主线程query provider。
    首先这会比较耗时，另外这个操作provider那一方的进程如果挂掉了或者正在启动，我们应用的query就会很长时间不会返回，我们应该在其他线程中执行数据库query、provider的query等获取数据的操作。
    
    ②SharePreferences在主线程不要用commit()，用apply()替换。
    首先SharePreferences的commit()方法是同步的，apply()方法一般是异步执行的。
    其次SharePreferences的写是全量写而非增量写，所以尽量都修改完统一apply，避免改一点apply一次(apply()方法在Activity stop的时候主线程会等待写入完成，提交多次就很容易卡)。并且存储文本也不宜过大，这样会很慢。
    另外，如果写入的是json或者xml，由于需要加和删转义符号，速度会比较慢。
    
5. 尽量避免主线程被锁的情况：
在一些同步的操作中，主线程有可能被锁，需要等待其他线程释放相应锁才能继续执行，这样会有一定的ANR风险，对于这种情况有时也可以用异步线程来执行相应的逻辑。另外， 我们要避免死锁的发生(主线程被死锁基本就等于要发生ANR了)。

	
一般来说，在应用程序里，100到200ms是用户能感知阻滞的时间阈值。因此，这里有一些额外的技巧来避免ANR，并有助于让你的应用程序看起来有响应性：

 1. 如果你的应用程序为响应用户输入正在后台工作的话，可以显示工作的进度（ProgressBar和ProgressDialog对这种情况来说很有用）。特别是游戏，在子线程里做移动的计算。
 
 2. 如果你的应用程序有一个耗时的初始化过程的话，考虑可以显示一个SplashScreen或者快速显示主画面并异步来填充这些信息。在这两种情况下，你都应该显示正在进行的进度，以免用户认为应用程序被冻结了。

