**一、什么是OOM？**

1. OOM(out of memory)即内存溢出，是指程序在申请内存时，没有足够的内存空间供其使用，出现out of memory。比如申请了一个integer,但给它存了long才能存下的数，那就是内存溢出。
    
2. 内存泄露(memory leak)，当一个对象已经不需要再使用本该被回收时，另外一个正在使用的对象持有它的引用从而导致它不能被回收，这导致本该被回收的对象不能被回收而停留在堆内存中，这就产生了内存泄漏。
    
3. 内存泄露是OOM的主要原因之一。
	
4. Android的一个应用程序的内存泄露对别的应用程序影响不大，因为为了能够使得Android应用程序安全且快速的运行，Android的每个应用程序都会使用一个专有的Dalvik虚拟机实例来运行，也就是说每个应用程序都是在属于自己的进程中运行的。如果程序内存溢出，Android系统只会kill掉该进程，而不会影响其他进程的使用（如果system_process等系统进程出问题的话，则会引起系统重启）。
	
		
**二、内存泄漏原因：**

1. **资源对象没关闭造成的内存泄露，try catch finally中将资源回收放到finally语句可以有效避免OOM。资源性对象比如：**

   - 未关闭Cursor
   - 调用registerReceiver()后未调用unregisterReceiver()
   - 未关闭InputStream/OutputStream
   - Bitmap使用后未调用recycle()并设置为null

2. **作用域不一样，导致对象不能被垃圾回收器回收，比如：**
 
   - 非静态内部类会隐式地持有外部类的引用(Handler、AsyncTask、Runnable等)
   - static修饰的Activity的Context
   - 单例持有的Activity的Context对象
   - Thread引用了其他对象
   - onReceive()方法里执行了太多的操作
		
3. **内存压力过大：**
	
    - 集合容器中没调用clear()清理对象，集合就会越来越大，static修饰的集合尤甚。
	- 图片过大或资源加载过多，超过内存使用空间，例如Bitmap 的使用，bitmap分辨率越高，所占用的内存就越大，这个是以2为指数级增长的。
	- 重复创建view，ListView应该使用convertview和viewholder。
	- 频繁申请新内存，对没使用的内存没进行有效利用或及时的释放。
	- 存在死循环，循环产生过多重复的对象实体。
	- WebView不使用时，应调用destory()函数销毁释放内存。
	
4. **动画导致的内存泄漏**
		
		
**三、如何避免OOM？**

1. **使用缓存技术，比如LruCache、DiskLruCache、对象重复并且频繁调用可以考虑对象池。**

2. **对于引用生命周期不一样的对象，可以用软引用SoftReference或弱引用WeakReference。**
	
3. **对于资源对象，使用finally强制关闭。**
	
4. **内存压力过大就要统一的管理内存。**
	
5. **Context相关：**
	
	①不要保留对Activity的Context长时间的引用（对Activity的引用的时候，必须确保拥有和Activity一样的生命周期）。
		
	②尝试使用Application的Context来替代Activity的Context。
		
	③如果你不想控制内部类的生命周期，应避免在Activity中使用非静态的内部类，而应该使用静态的内部类，并在其中创建一个对Activity的弱引用WeakReference。
        
6. **避免非静态内部类，可以使用静态内部类或者独立出来。**

7. **为WebView另外开启一个进程，通过AIDL与主线程进行通信，WebView所在的进程可以根据业务的需要选择合适的时机进行销毁，从而达到内存的完整释放。在activity退出后，WebView做相关的清理销毁操作。**

8. **对于Handler的使用如果有sendEmptyMessageDelayed()来延迟任务执行的话，最好在Activity的onDestroy()里面把Handler的任务都移除（removeCallbacks(null)）。**

9. **在activity退出后，内部线程一定要做相关的清理操作，中断线程，取消网络请求等等。**

10. **进入Activity界面后，如果有一些和控件绑定在一起的属性动画在运行，退出的时候要记得cancel掉这些动画；属性动画的对象尽量不要用static修饰，一定要用的话，在Activity退出时，调用动画的cancel()之前，加一句监听器的移除代码removeAllUpdateListeners()。**
11. **资源文件需要选择合适的文件夹进行存放。**
12. **优化布局层次，越扁平化的视图布局，占用的内存就越少，效率越高。**
13. **减小Bitmap对象的内存占用。**
14. **使用更小的图片，是否存在可以压缩的空间，是否可以使用一张更小的图片。**
15. **复用系统自带的资源，比如字符串/颜色/图片/动画/样式以及简单布局等等，这些资源都可以在应用程序中直接引用。**
16. **注意在ListView/GridView等出现大量重复子组件的视图里面对ConvertView的复用。**
17. **类似onDraw等频繁调用的方法，一定需要注意避免在这里做创建对象的操作，因为他会迅速增加内存的使用，而且很容易引起频繁的gc，甚至是内存抖动。**
18. **在有些时候，代码中会需要使用到大量的字符串拼接的操作，这种时候有必要考虑使用StringBuilder来替代频繁的“+”。**
        
        
**四、如何检查和分析内存泄漏？**  
因为内存泄漏是在堆内存中，所以对我们来说并不是可见的。通常我们可以借助MAT、LeakCanary等工具来检测应用程序是否存在内存泄漏。

1. **MAT**是一款强大的内存分析工具，功能繁多而复杂。

2. **LeakCanary**则是由Square开源的一款轻量级的第三方内存泄漏检测工具，当检测到程序中产生内存泄漏时，它将以最直观的方式告诉我们哪里产生了内存泄漏和导致谁泄漏了而不能被回收。
        
        
        
        
        
        
        
        