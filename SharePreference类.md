### SharedPreferences

它是一个轻量级应用程序内部轻量级的存储方案，特别适合用于保存软件配置参数，其实质是采用 xml 文件存放数据，路径为：

/data/data/<package name>/shared_prefs.



### 获取方式：

#### 1.Context 对象的 getSharedPreferences() 方法。

#### 2.Activity 对象的 getPreferences() 方法。



**区别：**前者获得的 SharePreferences 对象可以被同一应用程序下的其他组件共享，后者的对象只能在 Activity 中使用。



### 操作模式：

Context.MODE_PRIVATE 为默认模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写下的内容会覆盖原文件的内容。


​	

如果为了简便，就直接将 SharePreferences.Editor 来用做临时变量存储，代码如下：

```java
SharePreferences sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
sp.edit().putString(key,value);
sp.edit().commit();
```

结果在获得的时候就可能会为空。

​	

真正的原因是 Editor 作为临时变量有区别，so，正确存储方法如下：

```java
SharedPreferences.Editor e = sp.edit();
e.putString(key,value);
e.commit();
```