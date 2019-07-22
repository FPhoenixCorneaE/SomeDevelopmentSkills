### **目录介绍**

<a href="#1" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">1.OOM和崩溃优化</a>
<br>
<a href="#1.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.1 OOM优化</font></strong></a>
<br>
<a href="#1.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.2 ANR优化</font></strong></a>
<br>
<a href="#1.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.3 Crash优化</font></strong></a>
<br><br>

<a href="#2" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">2.内存泄漏优化</a>
<br>
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

<a href="#3" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">3.布局优化</a>
<br>
<a href="#3.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.1 include重用布局文件</font></strong></a>
<br>
<a href="#3.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.2 ViewStub需要时加载</font></strong></a>
<br>
<a href="#3.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.3 merge优化视图层级</font></strong></a>
<br>
<a href="#3.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.4 其他建议</font></strong></a>
<br><br>

<a href="#4" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">4.代码优化</a>
<br>
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

<a href="#5" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">5.网络优化</a>
<br>
<a href="#5.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.1 图片分类</font></strong></a>
<br>
<a href="#5.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.2 获取网络数据优化</font></strong></a>
<br>
<a href="#5.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.3 网络请求异常拦截优化</font></strong></a>
<br><br>

<a href="#6" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">6.线程优化</a>
<br>
<a href="#6.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">6.1 使用线程池</font></strong></a>
<br><br>

<a href="#7" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">7.图片优化</a>
<br>
<a href="#7.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">7.1 bitmap优化</font></strong></a>
<br>
<a href="#7.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">7.2 glide加载优化</font></strong></a>
<br><br>

<a href="#8" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">8.加载优化</a>
<br>
<a href="#8.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">8.1 懒加载优化</font></strong></a>
<br>
<a href="#8.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">8.2 启动页优化</font></strong></a>
<br><br>

<a href="#9" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">9.其他优化</a>
<br>
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

<a href="#10" style="background-color:grey;display: inline;width:100%;height:100%;border-radius:8px;padding:5px;color:#ffa500;font-size: 25px;font-family:正楷;font-weight:bold;text-decoration:none;">10.RecyclerView优化</a>
<br>
<a href="#10.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">10.1 页面为何卡顿</font></strong></a>
<br>
<a href="#10.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">10.2 具体优化方案</font></strong></a>
<br><br>
<br><br>


<a name="1" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">1.OOM和崩溃优化</font></strong></a>

<a name="1.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">1.2 ANR优化</font></strong></a>
- ANR的产生需要满足三个条件:

	- 主线程：只有应用程序进程的主线程响应超时才会产生ANR；
	- 超时时间：产生ANR的上下文不同，超时时间也会不同，但只要在这个时间上限内没有响应就会ANR；
	- 输入事件/特定操作：输入事件是指按键、触屏等设备输入事件，特定操作是指BroadcastReceiver和Service的生命周期中的各个函数，产生ANR的上下文不同，导致ANR的原因也会不同；

- ANR优化具体措施:

	- 将所有耗时操作，比如访问网络，Socket通信，查询大量SQL 语句，复杂逻辑计算等都放在子线程中去，然后通过handler.sendMessage、runonUIThread、AsyncTask 等方式更新UI。无论如何都要确保用户界面作的流畅度。如果耗时操作需要让用户等待，那么可以在界面上显示度条。
	- 使用AsyncTask处理耗时IO操作。在一些同步的操作主线程有可能被锁，需要等待其他线程释放相应锁才能继续执行，这样会有一定的ANR风险，对于这种情况有时也可以用异步线程来执行相应的逻辑。另外，要避免死锁的发生。
	- 使用Handler处理工作线程结果，而不是使用Thread.wait()或者Thread.sleep()来阻塞主线程。
	- Activity的onCreate和onResume回调中尽量避免耗时的代码
	- BroadcastReceiver中onReceive代码也要尽量减少耗时，建议使用IntentService处理。
	- 各个组件的生命周期函数都不应该有太耗时的操作，即使对于后台Service或者ContentProvider来讲，应用在后台运行时候其onCreate()时候不会有用户输入引起事件无响应ANR，但其执行时间过长也会引起Service的ANR和ContentProvider的ANR。
<br><br>

<a name="2" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">2.内存泄漏优化</font></strong></a>

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

	- 这样就在Activity内部创建了一个非静态内部类的单例，每次启动Activity时都会使用该单例的数据，这样虽然避免了资源的重复创建，不过这种写法却会造成内存泄漏，因为非静态内部类默认会持有外部类的引用，而该非静态内部类又创建了一个静态的实例，该实例的生命周期和应用的一样长，这就导致了该静态实例一直会持有该Activity的引用，导致Activity的内存资源不能正常回收。

- 解决办法:

	- 将该内部类设为静态内部类或将该内部类抽取出来封装成一个单例，如果需要使用Context，请按照上面推荐的使用Application 的 Context。
<br>

<a name="2.7" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.7 未移除监听</font></strong></a>

- 问题代码:
```java
//add监听，放到集合里面
tv.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
    @Override
    public void onWindowFocusChanged(boolean b) {
        //监听view的加载，view加载出来的时候，计算他的宽高等。
    }
});
```
- 解决办法:
```java
//计算完后，一定要移除这个监听
tv.getViewTreeObserver().removeOnWindowFocusChangeListener(this);
```
- 注意事项：
```java
tv.setOnClickListener();//监听执行完回收对象，不用考虑内存泄漏
tv.getViewTreeObserver().addOnWindowFocusChangeListener(),add监听，放到集合里面，需要考虑内存泄漏
```

<a name="2.8" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.8 持有activity引用</font></strong></a>
<br>

<a name="2.9" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.9 资源未关闭</font></strong></a>

- 在使用IO、File流或者Sqlite、Cursor等资源时要及时关闭。这些资源在进行读写操作时通常都使用了缓冲，如果及时不关闭，这些缓冲对象就会一直被占用而得不到释放，以致发生内存泄露。因此我们在不需要使用它们的时候就及时关闭，以便缓冲能及时得到释放，从而避免内存泄露。

- BroadcastReceiver，ContentObserver，FileObserver，Cursor，Callback等在 Activity onDestroy 或者某类生命周期结束之后一定要 unregister 或者 close 掉，否则这个 Activity 类会被 system 强引用，不会被内存回收。值得注意的是，关闭的语句必须在finally中进行关闭，否则有可能因为异常未关闭资源，致使activity泄漏。
<br>

<a name="2.10" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">2.10 其他原因</font></strong></a>

- 静态集合使用不当导致的内存泄漏

	- 有时候我们需要把一些对象加入到集合容器（例如ArrayList）中，当不再需要当中某些对象时，如果不把该对象的引用从集合中清理掉，也会使得GC无法回收该对象。如果集合是static类型的话，那内存泄漏情况就会更为严重。因此，当不再需要某对象时，需要主动将之从集合中移除。
<br><br>

<a name="3" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">3.布局优化</font></strong></a>

<a name="3.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.1 include重用布局文件</font></strong></a>
- 标签可以允许在一个布局当中引入另一个布局，那么比如说我们程序的所有界面都有一个公共的部分，这个时候最好的做法就是将这个公共的部分提取到一个独立的布局中，然后每个界面的布局文件当中来引用这个公共的布局。

- 如果我们要在标签中覆写layout属性，必须要将layout_width和layout_height这两个属性也进行覆写，否则覆写效果将不会生效。

- 标签是作为标签的一种辅助扩展来使用的，它的主要作用是为了防止在引用布局文件时引用文件时产生多余的布局嵌套。布局嵌套越多，解析起来就越耗时，性能就越差。因此编写布局文件时应该让嵌套的层数越少越好。

- 举例：比如在LinearLayout里边使用一个布局。里边又有一个LinearLayout，那么其实就存在了多余的布局嵌套，使用merge可以解决这个问题。
<br>

<a name="3.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.2 ViewStub需要时加载</font></strong></a>
- 仅在需要时才加载布局

	- 某个布局当中的元素不是一起显示出来的，普通情况下只显示部分常用的元素，而那些不常用的元素只有在用户进行特定操作时才会显示出来。
	- 举例：填信息时不是需要全部填的，有一个添加更多字段的选项，当用户需要添加其他信息的时候，才将另外的元素显示到界面上。用VISIBLE性能表现一般，可以用ViewStub。
	- ViewStub也是View的一种，但是没有大小，没有绘制功能，也不参与布局，资源消耗非常低，可以认为完全不影响性能。
	- ViewStub所加载的布局是不可以使用标签的，因此这有可能导致加载出来出来的布局存在着多余的嵌套结构。

- 自定义全局的状态管理器(充分使用ViewStub)

	- 针对多状态，有数据，空数据，加载失败，加载异常，网络异常等。针对空数据，加载失败，异常使用viewStub布局，一键设置自定义布局，也是优化的一种。
<br>

<a name="3.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.3 merge优化视图层级</font></strong></a>
- 这个标签在UI的结构优化中起着非常重要的作用，它可以删减多余的层级，优化UI。但是就有一点不好，无法预览布局效果！
<br>

<a name="3.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">3.4 其他建议</font></strong></a>

- 减少太多重叠的背景(overdraw)
	- 这个问题其实最容易解决，建议就是检查你在布局和代码中设置的背景，有些背景是隐藏在底下的，它永远不可能显示出来，这种没必要的背景一定要移除，因为它很可能会严重影响到app的性能。如果采用的是selector的背景，将normal状态的color设置为”@android:color/transparent”,也同样可以解决问题。

- 避免复杂的Layout层级

	- 这里的建议比较多一些，首先推荐使用Android提供的布局工具Hierarchy Viewer来检查和优化布局。第一个建议是：如果嵌套的线性布局加深了布局层次，可以使用相对布局来取代。第二个建议是：用标签来合并布局。第三个建议是：用标签来重用布局，抽取通用的布局可以让布局的逻辑更清晰明了。记住，这些建议的最终目的都是使得你的Layout在Hierarchy Viewer里变得宽而浅，而不是窄而深。

- 总结：可以考虑多使用merge和include，ViewStub。尽量使布局浅平，根布局尽量少使用RelactivityLayout,因为RelactivityLayout每次需要测量2次。
<br><br>

<a name="4" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">4.代码优化</font></strong></a>

- 都是一些微优化，在性能方面看不出有什么显著的提升的。使用合适的算法和数据结构是优化程序性能的最主要手段。
<br>

<a name="4.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.1 lint代码检测</font></strong></a>

- lint去除无效资源和代码

	- 如何检测哪些图片未被使用?
		- 点击菜单栏 Analyze -> Run Inspection by Name -> unused resources -> Moudule ‘app’ -> OK，这样会搜出来哪些未被使用到未使用到xml和图片

	- 如何检测哪些无效代码?
		- 使用Android Studio的Lint，步骤：点击菜单栏 Analyze -> Run Inspection by Name -> unused declaration -> Moudule ‘app’ -> OK
<br>

<a name="4.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.2 代码规范优化</font></strong></a>

- 避免创建不必要的对象，不必要的对象应该避免创建：
	- 如果有需要拼接的字符串，那么可以优先考虑使用StringBuffer或者StringBuilder来进行拼接，而不是加号连接符，因为使用加号连接符会创建多余的对象，拼接的字符串越长，加号连接符的性能越低。
	- 当一个方法的返回值是String的时候，通常需要去判断一下这个String的作用是什么，如果明确知道调用方会将返回的String再进行拼接操作的话，可以考虑返回一个StringBuffer对象来代替，因为这样可以将一个对象的引用进行返回，而返回String的话就是创建了一个短生命周期的临时对象。
	- 尽可能地少创建临时对象，越少的对象意味着越少的GC操作。
	- onDraw()方法里面不要执行对象的创建。

- 静态优于抽象
	- 如果你并不需要访问一个对系那个中的某些字段，只是想调用它的某些方法来去完成一项通用的功能，那么可以将这个方法设置成静态方法，调用速度提升15%-20%，同时也不用为了调用这个方法去专门创建对象了，也不用担心调用这个方法后是否会改变对象的状态(静态方法无法访问非静态字段)。

- 对常量使用static final修饰符

	- `static int intVal = 42; static String strVal = "Hello, world!";`
	- 编译器会为上面的代码生成一个初始方法，称为方法，该方法会在定义类第一次被使用的时候调用。这个方法会将42的值赋值到intVal当中，从字符串常量表中提取一个引用赋值到strVal上。当赋值完成后，我们就可以通过字段搜寻的方式去访问具体的值了。
	- final进行优化:`static final int intVal = 42; static final String strVal = "Hello, world!";`
	- 这样，定义类就不需要方法了，因为所有的常量都会在dex文件的初始化器当中进行初始化。当我们调用intVal时可以直接指向42的值，而调用strVal会用一种相对轻量级的字符串常量方式，而不是字段搜寻的方式。
	- 这种优化方式只对基本数据类型以及String类型的常量有效，对于其他数据类型的常量是无效的。

- 在没有特殊原因的情况下，尽量使用基本数据类型来代替封装数据类型，int比Integer要更加有效，其它数据类型也是一样。
	- 基本数据类型的数组也要优于对象数据类型的数组。另外两个平行的数组要比一个封装好的对象数组更加高效，举个例子，Foo[]和Bar[]这样的数组，使用起来要比Custom(Foo,Bar)[]这样的一个数组高效的多。
<br>

<a name="4.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.3 View异常优化</font></strong></a>

- view自定义控件异常销毁保存状态，经常容易被人忽略，但是为了追求高质量代码，这个也有必要加上。举个例子！
```java
@Override
protected Parcelable onSaveInstanceState() {
    // 异常情况保存重要信息。
    final Bundle bundle = new Bundle();
    bundle.putInt("selectedPosition",selectedPosition);
    bundle.putInt("flingSpeed",mFlingSpeed);
    bundle.putInt("orientation",orientation);
    return bundle;
}

@Override
protected void onRestoreInstanceState(Parcelable state) {
    if (state instanceof Bundle) {
        final Bundle bundle = (Bundle) state;
        selectedPosition = bundle.getInt("selectedPosition",selectedPosition);
        mFlingSpeed = bundle.getInt("flingSpeed",mFlingSpeed);
        orientation = bundle.getInt("orientation",orientation);
        return;
    }
    super.onRestoreInstanceState(state);
}
```

<a name="4.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.4 去除淡黄色警告优化</font></strong></a>

- 淡黄色警告虽然不会造成崩溃，但是作为程序员还是要尽量去除淡黄色警告，规范代码。

<br>

<a name="4.5" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.5 合理使用集合</font></strong></a>

- 使用优化过的数据集合。Android提供了一系列优化过后的数据集合工具类，如SparseArray、SparseBooleanArray、LongSparseArray，使用这些API可以让我们的程序更加高效。HashMap工具类会相对比较低效，因为它需要为每一个键值对都提供一个对象入口，而SparseArray就避免掉了基本数据类型转换成对象数据类型的时间。
<br>

<a name="4.6" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.6 Activity不可见优化</font></strong></a>

-  当Activity界面不可见时释放内存。当用户打开了另外一个程序，我们的程序界面已经不可见的时候，我们应当将所有和界面相关的资源进行释放。重写Activity的onTrimMemory()方法，然后在这个方法中监听TRIM_MEMORY_UI_HIDDEN这个级别，一旦触发说明用户离开了程序，此时就可以进行资源释放操作了。

<br>

<a name="4.7" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">4.7 节制的使用Service</font></strong></a>

- 如果应用程序需要使用Service来执行后台任务的话，只有当任务正在执行的时候才应该让Service运行起来。当启动一个Service时，系统会倾向于将这个Service所依赖的进程进行保留，系统可以在LRUcache当中缓存的进程数量也会减少，导致切换程序的时候耗费更多性能。我们可以使用IntentService，当后台任务执行结束后会自动停止，避免了Service的内存泄漏。
<br><br>

<a name="5" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">5.网络优化</font></strong></a>

<a name="5.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.1 图片分类</font></strong></a>

- 图片网络优化
	- 比如我之前看到豆瓣接口，提供一种加载图片方式特别好。接口返回图片的数据有三种，一种是高清大图，一种是正常图片，一种是缩略小图。当用户处于wifi下给控件设置高清大图，当4g或者3g模式下加载正常图片，当弱网条件下加载缩略图【也称与加载图】。
	- 简单来说根据用户的当前的网络质量来判断下载什么质量的图片（电商用的比较多）。豆瓣开源接口可以参考一下！
<br>

<a name="5.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.2 获取网络数据优化</font></strong></a>

- 移动端获取网络数据优化的几个点。

- 连接复用：节省连接建立时间，如开启 keep-alive。
	- 对于Android来说默认情况下HttpURLConnection和HttpClient都开启了keep-alive。只是2.2之前HttpURLConnection存在影响连接池的Bug，具体可见：Android HttpURLConnection及HttpClient选择

- 请求合并：即将多个请求合并为一个进行请求，比较常见的就是网页中的CSS Image Sprites。如果某个页面内请求过多，也可以考虑做一定的请求合并。

- 减少请求数据的大小：对于post请求，body可以做gzip压缩的，header也可以做数据压缩(不过只支持http）
	- 返回数据的body也可以做gzip压缩，body数据体积可以缩小到原来的30%左右。（也可以考虑压缩返回的json数据的key数据的体积，尤其是针对返回数据格式变化不大的情况，支付宝聊天返回的数据用到了）
<br>

<a name="5.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">5.3 网络请求异常拦截优化</font></strong></a>

- 在获取数据的流程中，访问接口和解析数据时都有可能会出错，我们可以通过拦截器在这两层拦截错误。
	- 在访问接口时，我们不用设置拦截器，因为一旦出现错误，Retrofit会自动抛出异常。比如，常见请求异常404，500，503等等。
	- 在解析数据时，我们设置一个拦截器，判断Result里面的code是否为成功，如果不成功，则要根据与服务器约定好的错误码来抛出对应的异常。比如，token失效，禁用同账号登陆多台设备，缺少参数，参数传递异常等等。
	- 除此以外，为了我们要尽量避免在View层对错误进行判断，处理，我们必须还要设置一个拦截器，拦截onError事件，然后使用ExceptionUtils，让其根据错误类型来分别处理。
	- 具体可以直接看lib中的ExceptionUtils类，那么如何调用呢？入侵性极低，不用改变之前的代码！
```java
@Override
public void onError(Throwable e) {
    //直接调用即可
    ExceptionUtils.handleException(e);
}
```

<a name="6" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">6.线程优化</font></strong></a>

<a name="6.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">6.1 使用线程池</font></strong></a>

- 将全局线程用线程池管理
	- 直接创建Thread实现runnable方法的弊端
		- 大量的线程的创建和销毁很容易导致GC频繁的执行，从而发生内存抖动现象，而发生了内存抖动，对于移动端来说，最大的影响就是造成界面卡顿。
		- 线程的创建和销毁都需要时间，当有大量的线程创建和销毁时，那么这些时间的消耗则比较明显，将导致性能上的缺失。

	- 为什么要用线程池？
		- 重用线程池中的线程，避免频繁地创建和销毁线程带来的性能消耗；有效控制线程的最大并发数量，防止线程过大导致抢占资源造成系统阻塞；可以对线程进行一定地管理。

	- 使用线程池管理的经典例子
		- RxJava，RxAndroid，底层对线程池的封装管理特别值得参考

	- 关于线程池，线程，多线程的具体内容
		- 参考：[轻量级线程池封装库，支持异步回调，可以检测线程执行的状态](https://github.com/yangchong211/YCThreadPool)。
		- 该项目中哪里用到频繁new Thread()？
			- 保存图片【注意，尤其是大图和多图场景下注意耗时太久】；某些页面从数据库查询数据；设置中心清除图片，视频，下载文件，日志，系统缓存等缓存内容。
			- 使用线程池管理库好处，比如保存图片，耗时操作放到子线程中，处理过程中，可以检测到执行开始，异常，成功，失败等多种状态。
<br><br>

<a name="7" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">7.图片优化</font></strong></a>

<a name="7.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">7.1 bitmap优化</font></strong></a>

- 加载图片所占的内存大小计算方式
	- 加载网络图片：bitmap内存大小 = 图片长度 x 图片宽度 x 单位像素占用的字节数【看到网上很多都是这样写的，但是不全面】。
	- 加载本地图片：bitmap内存大小 = width * height * nTargetDensity/inDensity 一个像素所占的内存。注意不要忽略了一个影响项：Density。

- 第一种加载图片优化处理：压缩图片
	- **质量压缩方法**：在保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的，这样适合去传递二进制的图片数据，比如分享图片，要传入二进制数据过去，限制500kb之内。
	- **采样率压缩方法**：设置inSampleSize的值(int类型)后，假如设为n，则宽和高都为原来的1/n，宽高都减少，内存降低。
	- **缩放法压缩**：Android中使用Matrix对图像进行缩放、旋转、平移、斜切等变换的。功能十分强大！

- 第二种加载图片优化：不压缩加载高清图片如何做？
	- 使用BitmapRegionDecoder，主要用于显示图片的某一块矩形区域，如果你需要显示某个图片的指定区域，那么这个类非常合适。
<br>

<a name="7.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">7.2 glide加载优化</font></strong></a>

- 在画廊中加载大图。假如你滑动特别快，glide加载优化就显得非常重要呢，具体优化方法如下所示：
```java
recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            LoggerUtils.e("initRecyclerView"+ "恢复Glide加载图片");
            Glide.with(ImageBrowseActivity.this).resumeRequests();
        }else {
            LoggerUtils.e("initRecyclerView"+"禁止Glide加载图片");
            Glide.with(ImageBrowseActivity.this).pauseRequests();
        }
    }
});
```
<br>

<a name="8" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">8.加载优化</font></strong></a>

<a name="8.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">8.1 懒加载优化</font></strong></a>

- 该优化在新闻类app中十分常见
	- ViewPager+Fragment的搭配在日常开发中也比较常见，可用于切换展示不同类别的页面。
	- 懒加载,其实也就是延迟加载,就是等到该页面的UI展示给用户时,再加载该页面的数据(从网络、数据库等),而不是依靠ViewPager预加载机制提前加载两三个，甚至更多页面的数据。这样可以提高所属Activity的初始化速度,也可以为用户节省流量.而这种懒加载的方式也已经/正在被诸多APP所采用。

- 具体看这篇文章
	- <a target="_blank" href="https://link.juejin.im?target=https%3A%2F%2Fwww.jianshu.com%2Fp%2Fcf1f4104de78" rel="nofollow noopener noreferrer">www.jianshu.com/p/cf1f4104d…</a>
<br>

<a name="8.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">8.2 启动页优化</font></strong></a>

- 启动时间分析
	- 系统创建进程的时间和应用进程启动的时间，前者是由系统自行完成的，一般都会很快，我们也干预不了，我觉得能做的就是去优化应用进程启动，具体说来就是从发Application的onCreate()执行开始到MainActivity的onCreate()执行结束这一段时间。

- 启动时间优化
	- Application的onCreate()方法
	- MainActivity的onCreate()方法
	- 优化的手段也无非三种，如下所示：

		- 延迟初始化
		- 后台任务
		- 启动界面预加载

- 启动页白屏优化
	- 为什么存在这个问题？
		- 当系统启动一个APP时，zygote进程会首先创建一个新的进程去运行这个APP，但是进程的创建是需要时间的，在创建完成之前，界面是呈现假死状态，于是系统根据你的manifest文件设置的主题颜色的不同来展示一个白屏或者黑屏。而这个黑（白）屏正式的称呼应该是Preview Window，即预览窗口。
		- 实际上就是是activity默认的主题中的android:windowBackground为白色或者黑色导致的。
		- 总结来说启动顺序就是：app启动——Preview Window(也称为预览窗口)——启动页。

	- 解决办法：
		- 常见有三种，这里解决办法是给当前启动页添加一个有背景的style样式，然后SplashActivity引用当前theme主题，注意在该页面将window的背景图设置为空！
		- 更多关于启动页为什么白屏闪屏，以及不同解决办法，可以看这篇博客：[优化App冷启动,实现启动页错觉秒开](https://www.jianshu.com/p/dddbfc9a312c)

- 启动时间优化
	- IntentService子线程分担部分初始化工作。
		- 现在application初始化内容有：阿里云推送初始化，腾讯bugly初始化，im初始化，神策初始化，内存泄漏工具初始化，头条适配方案初始化，阿里云热修复……等等。将部分逻辑放到IntentService中处理，可以缩短很多时间。
		- 开启IntentSerVice线程，将部分逻辑和耗时的初始化操作放到这里处理，可以减少application初始化时间
		- 关于IntentService使用和源码分析，性能分析等可以参考博客：[IntentService源码分析](https://www.jianshu.com/p/f7dd2cd64d11)
<br><br>

<a name="9" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">9.其他优化</font></strong></a>

<a name="9.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.1 静态变量优化</font></strong></a>

- 尽量不使用静态变量保存核心数据。这是为什么呢？这是因为android的进程并不是安全的，包括application对象以及静态变量在内的进程级别变量并不会一直呆着内存里面，因为它很有会被kill掉。当被kill掉之后，实际上app不会重新开始启动。Android系统会创建一个新的Application对象，然后启动上次用户离开时的activity以造成这个app从来没有被kill掉的假象。而这时候静态变量等数据由于进程已经被杀死而被初始化，所以就有了不推荐在静态变量（包括Application中保存全局数据静态数据）的观点。
<br>

<a name="9.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.2 注解替代枚举</font></strong></a>

- 使用注解限定传入类型
	- 比如，尤其是写第三方开源库，对于有些暴露给开发者的方法，需要限定传入类型是有必要的。举个例子：
	- 刚开始的代码:
	```java
	/**
	 * 设置播放器类型，必须设置
	 * 注意：感谢某人建议，这里限定了传入值类型
	 * 输入值：111   或者  222
	 * @param playerType IjkPlayer or MediaPlayer.
	 */
	public void setPlayerType(int playerType) {
		mPlayerType = playerType;
	}
	```
	- 优化后的代码，有效避免第一种方式开发者传入值错误。
	```java
	/**
	 * 设置播放器类型，必须设置
	 * 注意：感谢某人建议，这里限定了传入值类型
	 * 输入值：ConstantKeys.IjkPlayerType.TYPE_IJK   或者  ConstantKeys.IjkPlayerType.TYPE_NATIVE
	 * @param playerType IjkPlayer or MediaPlayer.
	 */
	public void setPlayerType(@ConstantKeys.PlayerType int playerType) {
		mPlayerType = playerType;
	}

	/**
	 * 通过注解限定类型
	 * TYPE_IJK                 IjkPlayer，基于IjkPlayer封装播放器
	 * TYPE_NATIVE              MediaPlayer，基于原生自带的播放器控件
	 */
	@Retention(RetentionPolicy.SOURCE)
	public @interface IjkPlayerType {
		int TYPE_IJK = 111;
		int TYPE_NATIVE = 222;
	}
	@IntDef({IjkPlayerType.TYPE_IJK,IjkPlayerType.TYPE_NATIVE})
	public @interface PlayerType{}
	```
- 使用注解替代枚举，代码如下所示
	```java
	@Retention(RetentionPolicy.SOURCE)
	public @interface ViewStateType {
		int HAVE_DATA = 1;
		int EMPTY_DATA = 2;
		int ERROR_DATA = 3;
		int ERROR_NETWORK = 4;
	}
	```

<a name="9.3" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.3 多渠道打包优化</font></strong></a>

- 还在手动打包吗？尝试一下python自动化打包吧……
	- 瓦力多渠道打包的Python脚本测试工具，通过该自动化脚本，自需要run一下或者命令行运行脚本即可实现美团瓦力多渠道打包，打包速度很快。配置信息十分简单，代码中已经注释十分详细。可以自定义输出文件路径，可以修改多渠道配置信息，简单实用。 项目地址：<a target="_blank" href="https://link.juejin.im?target=https%3A%2F%2Fgithub.com%2Fyangchong211%2FYCWalleHelper" rel="nofollow noopener noreferrer">github.com/yangchong21…</a>
<br>

<a name="9.4" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.4 TrimMemory和LowMemory优化</font></strong></a>

- 可以优化什么？
	- 在 onTrimMemory() 回调中，应该在一些状态下清理掉不重要的内存资源。对于这些缓存，只要是读进内存内的都算，例如最常见的图片缓存、文件缓存等。拿图片缓存来说，市场上，常规的图片加载库，一般而言都是三级缓存，所以在内存吃紧的时候，我们就应该优先清理掉这部分图片缓存，毕竟图片是吃内存大户，而且再次回来的时候，虽然内存中的资源被回收掉了，依然可以从磁盘或者网络上恢复它。

- 大概的思路如下所示
	- 在lowMemory的时候，调用Glide.cleanMemory()清理掉所有的内存缓存。
	- 在App被置换到后台的时候，调用Glide.cleanMemory()清理掉所有的内存缓存。
	- 在其它情况的onTrimMemory()回调中，直接调用Glide.trimMemory()方法来交给Glide处理内存情况。
<br>

<a name="9.5" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.5 轮询操作优化</font></strong></a>

- 什么叫轮询请求？
	- 简单理解就是App端每隔一定的时间重复请求的操作就叫做轮询请求，比如：App端每隔一段时间上报一次定位信息，App端每隔一段时间拉去一次用户状态等，这些应该都是轮训请求。比如，电商类项目，某个抽奖活动页面，隔1分钟调用一次接口，弹出一些获奖人信息，你应该某个阶段看过这类轮询操作！

- 具体优化操作
	- 长连接并不是稳定的可靠的，而执行轮训操作的时候一般都是要稳定的网络请求，而且轮训操作一般都是有生命周期的，即在一定的生命周期内执行轮训操作，而长连接一般都是整个进程生命周期的，所以从这方面讲也不太适合。
	- 建议在service中做轮询操作，轮询请求接口，具体做法和注意要点，可以直接看该项目代码。看app包下的LoopRequestService类即可。
	- 大概思路：当用户打开这个页面的时候初始化TimerTask对象，每个一分钟请求一次服务器拉取订单信息并更新UI，当用户离开页面的时候清除TimerTask对象，即取消轮训请求操作。
<br>

<a name="9.6" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.6 去除重复依赖库优化</font></strong></a>

- 我相信你看到了这里会有疑问，网上有许多博客作了这方面说明。但是我在这里想说，如何查找自己项目的所有依赖关系树。
	- 注意要点：其中app就是项目mudule名字。 正常情况下就是app！
	`gradlew app:dependencies`

- 关于依赖关系树的结构图如下所示，此处省略很多代码
	```gradle
	|    |    |    |    |    |    \--- android.arch.core:common:1.1.1 (*)
	|    |    |    |         \--- com.android.support:support-annotations:26.1.0 -> 28.0.0
	|    +--- com.journeyapps:zxing-android-embedded:3.6.0
	|    |    +--- com.google.zxing:core:3.3.2
	|    |    \--- com.android.support:support-v4:25.3.1
	|    |         +--- com.android.support:support-compat:25.3.1 -> 28.0.0 (*)
	|    |         +--- com.android.support:support-media-compat:25.3.1
	|    |         |    +--- com.android.support:support-annotations:25.3.1 -> 28.0.0
	|    |         |    \--- com.android.support:support-compat:25.3.1 -> 28.0.0 (*)
	|    |         +--- com.android.support:support-core-utils:25.3.1 -> 28.0.0 (*)
	|    |         +--- com.android.support:support-core-ui:25.3.1 -> 28.0.0 (*)
	|    |         \--- com.android.support:support-fragment:25.3.1 -> 28.0.0 (*)
	\--- com.android.support:multidex:1.0.2 -> 1.0.3
	```
- 然后查看哪些重复jar

	<img src="https://user-gold-cdn.xitu.io/2019/6/17/16b640ac138ab54c?imageView2/0/w/1280/h/960/ignore-error/1" width="800px" height="338px">


- 然后修改gradle配置代码
	```gradle
	api (rootProject.ext.dependencies["zxing"]){
		exclude module: 'support-v4'
		exclude module: 'appcompat-v7'
	}
	```

<a name="9.7" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.7 四种引用优化</font></strong></a>

- 软引用使用场景
	- 正常是用来处理大图片这种占用内存大的情况。代码如下所示：
	```java
	Bitmap bitmap = bitmaps.get(position);
	//正常是用来处理图片这种占用内存大的情况
	bitmapSoftReference = new SoftReference<>(bitmap);
	if(bitmapSoftReference.get() != null) {
		viewHolder.imageView.setImageBitmap(bitmapSoftReference.get());
	}
	//其实看glide底层源码可知，也做了相关软引用的操作
	```
	- 这样使用软引用好处
		- 通过软引用的get()方法，取得bitmap对象实例的强引用，发现对象被未回收。在GC在内存充足的情况下，不会回收软引用对象。此时view的背景显示
		- 实际情况中,我们会获取很多图片.然后可能给很多个view展示, 这种情况下很容易内存吃紧导致oom,内存吃紧，系统开始会GC。这次GC后，bitmapSoftReference.get()不再返回bitmap对象，而是返回null，这时屏幕上背景图不显示，说明在系统内存紧张的情况下，软引用被回收。
		- 使用软引用以后，在OutOfMemory异常发生之前，这些缓存的图片资源的内存空间可以被释放掉的，从而避免内存达到上限，避免Crash发生。

- 弱引用使用场景
	- 弱引用–>随时可能会被垃圾回收器回收，不一定要等到虚拟机内存不足时才强制回收。
	- 对于使用频次少的对象，希望尽快回收，使用弱引用可以保证内存被虚拟机回收。比如handler，如果希望使用完后尽快回收，看下面代码:
		```java
		private MyHandler handler = new MyHandler(this);
		private static class MyHandler extends Handler{
			WeakReference<FirstActivity> weakReference;
			MyHandler(FirstActivity activity) {
				weakReference = new WeakReference<>(activity);
			}
		
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what){
				}
			}
		}
		```
- 到底什么时候使用软引用，什么时候使用弱引用呢？
	- 如果只是想避免OutOfMemory异常的发生，则可以使用软引用。如果对于应用的性能更在意，想尽快回收一些占用内存比较大的对象，则可以使用弱引用。
	- 还有就是可以根据对象是否经常使用来判断。如果该对象可能会经常使用的，就尽量用软引用。如果该对象不被使用的可能性更大些，就可以用弱引用。
<br>

<a name="9.8" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.8 加载loading优化</font></strong></a>

- 一般实际开发中会至少有两种loading。
	- 第一种是从A页面进入B页面时的加载loading，这个时候特点是显示loading的时候，页面是纯白色的，加载完数据后才显示内容页面。
	- 第二种是在某个页面操作某种逻辑，比如某些耗时操作，这个时候是局部loading【一般用个帧动画或者补间动画】，由于使用频繁，因为建议在销毁弹窗时，添加销毁动画的操作。

- 自定义loading加载：
	- <a target="_blank" href="https://link.juejin.im?target=https%3A%2F%2Fgithub.com%2Fyangchong211%2FYCDialog" rel="nofollow noopener noreferrer">github.com/yangchong21…</a>
<br>

<a name="9.9" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">9.9 对象池Pools优化</font></strong></a>

- 对象池Pools优化频繁创建和销毁对象。
- 使用对象池，可以防止频繁创建和销毁对象而出现内存抖动。
	- 在某些时候，我们需要频繁使用一些临时对象，如果每次使用的时候都申请新的资源，很有可能会引发频繁的 gc 而影响应用的流畅性。这个时候如果对象有明确的生命周期，那么就可以通过定义一个对象池来高效的完成复用对象。
	- 具体参考案例，可以看该项目：<a target="_blank" href="https://link.juejin.im?target=https%3A%2F%2Fgithub.com%2Fyangchong211%2FYCZoomImage" rel="nofollow noopener noreferrer">github.com/yangchong21…</a>
<br><br>

<a name="10" style="text-decoration:none"><strong><font color=#222222 size=5 face="Consolas">10.RecyclerView优化</font></strong></a>

<a name="10.1" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">10.1 页面为何卡顿</font></strong></a>

- RecyclerView滑动卡顿的原因有哪些？
	- 第一种：嵌套布局滑动冲突。
		- 导致嵌套滑动难处理的关键原因在于当子控件消费了事件, 那么父控件就不会再有机会处理这个事件了, 所以一旦内部的滑动控件消费了滑动操作, 外部的滑动控件就再也没机会响应这个滑动操作了。

	- 第二种：嵌套布局层次太深，比如六七层等。
		- 测量，绘制布局可能会导致滑动卡顿。

	- 第三种：比如用RecyclerView实现画廊，加载比较大的图片，如果快速滑动，则可能会出现卡顿，主要是加载图片需要时间。

	- 第四种：在onCreateViewHolder或者在onBindViewHolder中做了耗时的操作导致卡顿。按stackoverflow上面比较通俗的解释：RecyclerView.Adapter里面的onCreateViewHolder()方法和onBindViewHolder()方法对时间都非常敏感。类似I/O读写，Bitmap解码一类的耗时操作，最好不要在它们里面进行。

- 关于RecyclerView封装库
	- <a target="_blank" href="https://link.juejin.im?target=https%3A%2F%2Fgithub.com%2Fyangchong211%2FYCRefreshView" rel="nofollow noopener noreferrer">github.com/yangchong21…</a>
<br>

<a name="10.2" style="text-decoration:none"><strong><font color=#222222 size=4 face="微软雅黑">10.2 具体优化方案</font></strong></a>

- 01.SparseArray替代HashMap
- 02.瀑布流图片错乱问题解决
- 03.item点击事件放在哪里优化
- 04.ViewHolder优化
- 05.连续上拉加载更多优化
- 06.拖拽排序与滑动删除优化
- 07.暂停或停止加载数据优化
- 08.异常情况下保存状态
- 09.多线程下插入数据优化
- 10.recyclerView优化处理
- 11.adapter优化
- 12.具体看这篇博客：[recyclerView优化](https://github.com/yangchong211/YCBlogs/blob/master/android/recyclerView/21.RecyclerView%E4%BC%98%E5%8C%96%E5%A4%84%E7%90%86.md)
<br><br>


转载自<https://juejin.im/post/5d072dbc51882540b7104709#heading-4>