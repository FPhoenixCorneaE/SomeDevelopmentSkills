#### **目录介绍**

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#1" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">1.OOM和崩溃优化</font></a>
</div>
<br><br>
<a href="#1.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.1 OOM优化</font></strong></a>
<br>
<a href="#1.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.2 ANR优化</font></strong></a>
<br>
<a href="#1.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.3 Crash优化</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#2" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">2.内存泄漏优化</font></a>
</div>
<br><br>
<a href="#2.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.1 动画资源未释放</font></strong></a>
<br>
<a href="#2.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.2 错误使用单利</font></strong></a>
<br>
<a href="#2.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.3 错误使用静态变量</font></strong></a>
<br>
<a href="#2.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.4 handler内存泄漏</font></strong></a>
<br>
<a href="#2.5" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.5 线程造成内存泄漏</font></strong></a>
<br>
<a href="#2.6" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.6 非静态内部类</font></strong></a>
<br>
<a href="#2.7" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.7 未移除监听</font></strong></a>
<br>
<a href="#2.8" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.8 持有activity引用</font></strong></a>
<br>
<a href="#2.9" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.9 资源未关闭</font></strong></a>
<br>
<a href="#2.10" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.10 其他原因</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#3" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">3.布局优化</font></a>
</div>
<br><br>
<a href="#3.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.1 include优化</font></strong></a>
<br>
<a href="#3.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.2 ViewStub优化</font></strong></a>
<br>
<a href="#3.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.3 merge优化</font></strong></a>
<br>
<a href="#3.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.4 其他建议</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#4" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">4.代码优化</font></a>
</div>
<br><br>
<a href="#4.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.1 lint代码检测</font></strong></a>
<br>
<a href="#4.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.2 代码规范优化</font></strong></a>
<br>
<a href="#4.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.3 View异常优化</font></strong></a>
<br>
<a href="#4.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.4 去除淡黄色警告优化</font></strong></a>
<br>
<a href="#4.5" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.5 合理使用集合</font></strong></a>
<br>
<a href="#4.6" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.6 Activity不可见优化</font></strong></a>
<br>
<a href="#4.7" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.7 节制的使用Service</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#5" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">5.网络优化</font></a>
</div>
<br><br>
<a href="#5.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.1 图片分类</font></strong></a>
<br>
<a href="#5.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.2 获取网络数据优化</font></strong></a>
<br>
<a href="#5.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.3 网络请求异常拦截优化</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#6" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">6.线程优化</font></a>
</div>
<br><br>
<a href="#6.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">6.1 使用线程池</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#7" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">7.图片优化</font></a>
</div>
<br><br>
<a href="#7.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">7.1 bitmap优化</font></strong></a>
<br>
<a href="#7.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">7.2 glide加载优化</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#8" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">8.加载优化</font></a>
</div>
<br><br>
<a href="#8.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">8.1 懒加载优化</font></strong></a>
<br>
<a href="#8.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">8.2 启动页优化</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#9" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">9.其他优化</font></a>
</div>
<br><br>
<a href="#9.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.1 静态变量优化</font></strong></a>
<br>
<a href="#9.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.2 注解替代枚举</font></strong></a>
<br>
<a href="#9.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.3 多渠道打包优化</font></strong></a>
<br>
<a href="#9.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.4 TrimMemory和LowMemory优化</font></strong></a>
<br>
<a href="#9.5" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.5 轮询操作优化</font></strong></a>
<br>
<a href="#9.6" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.6 去除重复依赖库优化</font></strong></a>
<br>
<a href="#9.7" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.7 四种引用优化</font></strong></a>
<br>
<a href="#9.8" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.8 加载loading优化</font></strong></a>
<br>
<a href="#9.9" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.9 对象池Pools优化</font></strong></a>
<br><br>

<div style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:orange;font-size: 20px;font-family:宋体;">
<a href="#10" style="text-decoration:none"><font color=#ffa500 size=5 face="正楷">10.RecyclerView优化</font></a>
</div>
<br><br>
<a href="#10.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">10.1 页面为何卡顿</font></strong></a>
<br>
<a href="#10.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">10.2 具体优化方案</font></strong></a>
<br><br>
<br><br>


<a name="1" style="text-decoration:none"><strong><font color=#222222 size=5 face="微软雅黑">1.OOM和崩溃优化</font></strong></a>

<a name="1.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.2 ANR优化</font></strong></a>
- ANR的产生需要满足三个条件:

	- 主线程：只有应用程序进程的主线程响应超时才会产生ANR；
	- 超时时间：产生ANR的上下文不同，超时时间也会不同，但只要在这个时间上限内没有响应就会ANR；
	- 输入事件/特定操作：输入事件是指按键、触屏等设备输入事件，特定操作是指BroadcastReceiver和Service的生命周期中的各个函数，产生ANR的上下文不同，导致ANR的原因也会不同；
<br>
- ANR优化具体措施:

	- 将所有耗时操作，比如访问网络，Socket通信，查询大量SQL 语句，复杂逻辑计算等都放在子线程中去，然后通过handler.sendMessage、runonUIThread、AsyncTask 等方式更新UI。无论如何都要确保用户界面作的流畅度。如果耗时操作需要让用户等待，那么可以在界面上显示度条。
	- 使用AsyncTask处理耗时IO操作。在一些同步的操作主线程有可能被锁，需要等待其他线程释放相应锁才能继续执行，这样会有一定的ANR风险，对于这种情况有时也可以用异步线程来执行相应的逻辑。另外，要避免死锁的发生。
	- 使用Handler处理工作线程结果，而不是使用Thread.wait()或者Thread.sleep()来阻塞主线程。
	- Activity的onCreate和onResume回调中尽量避免耗时的代码
	- BroadcastReceiver中onReceive代码也要尽量减少耗时，建议使用IntentService处理。
	- 各个组件的生命周期函数都不应该有太耗时的操作，即使对于后台Service或者ContentProvider来讲，应用在后台运行时候其onCreate()时候不会有用户输入引起事件无响应ANR，但其执行时间过长也会引起Service的ANR和ContentProvider的ANR。
<br><br>

<a name="2" style="text-decoration:none"><strong><font color=#222222 size=5 face="微软雅黑">2.内存泄漏优化</font></strong></a>

- 内存检测第一种：代码方式获取内存
```java
/**
 * 内存使用检测：可以调用系统的getMemoryInfo()来获取当前内存的使用情况
 */
private void initMemoryInfo() {
    ActivityManager activityManager = (ActivityManager) Utils.getApp()
            .getSystemService(Context.ACTIVITY_SERVICE);
    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
    if (activityManager != null) {
        activityManager.getMemoryInfo(memoryInfo);
        LogUtils.d("totalMem=" + memoryInfo.totalMem + ",availMem=" + memoryInfo.availMem);
        if (!memoryInfo.lowMemory) {
            // 运行在低内存环境
        }
    }
}
```
- 内存检测第二种：leakcanary工具

	- LeakCanary的原理是监控每个activity，在activity destory后，在后台线程检测引用，然后过一段时间进行gc，gc后如果引用还在，那么dump出内存堆栈，并解析进行可视化显示。
<br>

<a name="2.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.1 动画资源未释放</font></strong></a>

- 问题代码:
```java
public class LeakActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        textView = (TextView)findViewById(R.id.text_view);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView,"rotation",0,360);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }
}
```
- 解决办法:

	- 在属性动画中有一类无限循环动画，如果在Activity中播放这类动画并且在onDestroy中去停止动画，那么这个动画将会一直播放下去，这时候Activity会被View所持有，从而导致Activity无法被释放。解决此类问题则是需要早Activity中onDestroy()去调用objectAnimator.cancel()来停止动画。

```java
@Override
protected void onDestroy() {
    super.onDestroy();
    mAnimator.cancel();
}
```

<a name="2.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.2 错误使用单例</font></strong></a>

- 在开发中单例经常需要持有Context对象，如果持有的Context对象生命周期与单例生命周期更短时，或导致Context无法被释放回收，则有可能造成内存泄漏。比如：在一个Activity中调用的，然后关闭该Activity则会出现内存泄漏。

- 解决办法：

	- 要保证Context和AppLication的生命周期一样，修改后代码如下：
	`this.mContext = context.getApplicationContext();`
	- 如果此时传入的是 Application 的 Context，因为 Application 的生命周期就是整个应用的生命周期，所以这将没有任何问题。
	- 如果此时传入的是 Activity 的 Context，当这个 Context 所对应的 Activity 退出时，由于该 Context 的引用被单例对象所持有，其生命周期等于整个应用程序的生命周期，所以当前 Activity 退出时它的内存并不会被回收，这就造成泄漏了。
<br>

<a name="2.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.3 错误使用静态变量</font></strong></a>

- 使用静态方法是十分方便的。但是创建的对象，建议不要全局化，全局化的变量必须加上static。全局化后的变量或者对象会导致内存泄漏！

- 原因分析:

	- 这里内部类AClass隐式的持有外部类Activity的引用，而在Activity的onCreate方法中调用了。这样AClass就会在Activity创建的时候是有了他的引用，而AClass是静态类型的不会被垃圾回收，Activity在执行onDestory方法的时候由于被AClass持有了引用而无法被回收，所以这样Activity就总是被AClass持有而无法回收造成内存泄露。
<br>

<a name="2.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.4 handler内存泄漏</font></strong></a>

- 造成内存泄漏原因分析:

	- 通过内部类的方式创建mHandler对象,此时mHandler会隐式地持有一个外部类对象引用这里就是MainActivity，当执行postDelayed方法时，该方法会将你的Handler装入一个Message，并把这条Message推到MessageQueue中，MessageQueue是在一个Looper线程中不断轮询处理消息，那么当这个Activity退出时消息队列中还有未处理的消息或者正在处理消息，而消息队列中的Message持有mHandler实例的引用，mHandler又持有Activity的引用，所以导致该Activity的内存资源无法及时回收，引发内存泄漏。
<br>
- 解决Handler内存泄露主要有2点:

	- 有延时消息，要在Activity销毁的时候移除Messages监听;
	- 匿名内部类导致的泄露改为匿名静态内部类，并且对上下文或者Activity使用弱引用。
<br>

<a name="2.5" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.5 线程造成内存泄漏</font></strong></a>
- 早时期的时候处理耗时操作多数都是采用Thread+Handler的方式，后来逐步被AsyncTask取代，直到现在采用RxJava的方式来处理异步。

- 造成内存泄漏原因分析:

	- 在处理一个比较耗时的操作时，可能还没处理结束MainActivity就执行了退出操作，但是此时AsyncTask依然持有对MainActivity的引用就会导致MainActivity无法释放回收引发内存泄漏。

- 解决办法:

	- 在使用AsyncTask时，在Activity销毁时候也应该取消相应的任务AsyncTask.cancel()方法，避免任务在后台执行浪费资源，进而避免内存泄漏的发生。
<br>

<a name="2.6" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.6 非静态内部类</font></strong></a>
- 非静态内部类创建静态实例造成的内存泄漏。有的时候我们可能会在启动频繁的Activity中，为了避免重复创建相同的数据资源，可能会出现这种写法。
- 问题代码:
```java
private static TestResource mResource = null;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if(mResource == null){
        mResource = new TestResource();
    }
}

class TestResource {
     //里面代码引用上下文，Activity.this会导致内存泄漏
}
```
- 分析问题:

	-这样就在Activity内部创建了一个非静态内部类的单例，每次启动Activity时都会使用该单例的数据，这样虽然避免了资源的重复创建，不过这种写法却会造成内存泄漏，因为非静态内部类默认会持有外部类的引用，而该非静态内部类又创建了一个静态的实例，该实例的生命周期和应用的一样长，这就导致了该静态实例一直会持有该Activity的引用，导致Activity的内存资源不能正常回收。

- 解决办法:

	- 将该内部类设为静态内部类或将该内部类抽取出来封装成一个单例，如果需要使用Context，请按照上面推荐的使用Application 的 Context。
<br>

<a name="2.7" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.7 未移除监听</font></strong></a>