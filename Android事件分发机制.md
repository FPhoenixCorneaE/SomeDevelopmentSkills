在Android开发中，事件分发机制是一块Android比较重要的知识体系，了解并熟悉整套的分发机制有助于更好的分析各种点击滑动失效问题，更好去扩展控件的事件功能和开发自定义控件。

事件的流向，如下图：

！[图 事件分发机制.png](https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/Android%E4%BA%8B%E4%BB%B6%E5%88%86%E5%8F%91%E6%9C%BA%E5%88%B6.png)

 **1. 如果事件不被中断，整个事件流向是一个类U型图；**
 
 **2. dispatchTouchEvent 和 onTouchEvent 一旦return true,事件就被消费了，也就是事件停止传递了，到达终点，没有谁能再收到这个事件；**
 
 **3. dispatchTouchEvent 和 onTouchEvent return false的时候事件都回传给父控件的onTouchEvent处理；**
 
 - dispatchTouchEvent return false 的含义是：事件停止往子View传递和分发同时开始往父控件回溯（父控件的onTouchEvent开始从下往上回传，直到某个onTouchEvent return true），事件分发机制就像递归，return false 的意义就是递归停止然后开始回溯；
 - onTouchEvent return false 的含义是：不消费事件，并让事件继续往父控件的方向从下往上流动；

**4. Activity、ViewGroup、View的dispatchTouchEvent、onTouchEvent方法以及ViewGroup的onInterceptTouchEvent方法的默认实现 return super.xxx() 会让事件依照U型的方向，完整走完整个事件流动路径，中间不做任何改动，不回溯、不终止，每个环节都走到；**

**5. onInterceptTouchEvent 的作用**

- Intercept 的意思就是拦截，每个ViewGroup每次在做分发的时候，问一问拦截器要不要拦截（也就是问问自己这个事件要不要自己来处理）如果要自己处理那就在onInterceptTouchEvent方法中 return true就会交给自己的onTouchEvent的处理，如果不拦截就是继续往子控件往下传；

- 默认是不会去拦截的，因为子View也需要这个事件，所以onInterceptTouchEvent拦截器return super.onInterceptTouchEvent()和return false是一样的，是不会拦截的，事件会继续往子View的dispatchTouchEvent传递；

**6. ViewGroup 和View 的dispatchTouchEvent方法返回super.dispatchTouchEvent()的时候事件流走向**

- ViewGroup 的dispatchTouchEvent return true是终结传递，return false 是回溯到父View的onTouchEvent，如果要把事件分发到自己的onTouchEvent处理，只能通过Interceptor把事件拦截下来给自己的onTouchEvent，所以ViewGroup dispatchTouchEvent方法的super默认实现就是去调用onInterceptTouchEvent；
- 同样的道理，View的dispatchTouchEvent return true是终结，return false 是回溯到父类的onTouchEvent，如果要把事件分发给自己的onTouchEvent 处理，那只能return super.dispatchTouchEvent,View类的dispatchTouchEvent方法默认实现就是能调用View自己的onTouchEvent方法；

### 总结：

1. 对于 dispatchTouchEvent，onTouchEvent，return true是终结事件传递，return false 是回溯到父View的onTouchEvent方法；
2. ViewGroup 想把事件分发给自己的onTouchEvent，需要拦截器onInterceptTouchEvent方法return true 把事件拦截下来；
3. ViewGroup 的拦截器onInterceptTouchEvent 默认是不拦截的，所以return super.onInterceptTouchEvent = return false；
4. View 没有拦截器，View 想把事件分发给自己的onTouchEvent，View的dispatchTouchEvent默认实现（super，相当于拦截）就会把事件分发给自己的onTouchEvent；


**###关于ACTION_MOVE 和 ACTION_UP**

1. ACTION_DOWN事件在哪个控件消费了（return true）， 那么ACTION_MOVE和ACTION_UP就会从上往下（通过dispatchTouchEvent）做事件分发往下传，就只会传到这个控件，不会继续往下传；
2. 如果ACTION_DOWN事件是在dispatchTouchEvent消费，那么事件到此为止停止传递；
3. 如果ACTION_DOWN事件是在onTouchEvent消费的，那么会把ACTION_MOVE或ACTION_UP事件传给该控件的onTouchEvent处理并结束传递；



@see:**图解 Android 事件分发机制**https://www.jianshu.com/p/e99b5e8bd67b




 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
