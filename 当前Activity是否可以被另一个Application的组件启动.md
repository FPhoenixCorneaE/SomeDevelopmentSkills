### 当前 Activity 是否可以被另一个 Application 的组件启动?

有时候当前 Application 的 Activity，需要可以被另一个 Application 的组件启动，这就要为其它应用调用当前组件提供支持。



有三种方案：

**1.在 AndroidManifest.xml 文件中设置 Activity 的 exported 属性为 true，exported 的默认值根据 Activity 中是否有 intent filter 来定；**

**2.如果 Activity 里面至少有一个 filter 的话，意味着这个 Activity 可以被其它应用从外部唤起，这个时候它的默认值是 true；**

**3.也可以使用 permission 来限制外部实体唤醒当前 Activity；**


​	
在另一个 Application 启动当前 Activity，并传值：

```java
Intent intent = new Intent();
intent.setClassName(packageName, className);
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.putExtra("gift_id", giftId);
intent.putExtra("game_id", gameId);
intent.putExtra("source_type", source_type);
mContext.startActivity(intent);
```

`intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);` 表示虚拟机另起一个任务栈，否则在当前app的任务栈打开。