### ViewPager 一屏显示多张图片的步骤:

#### 1.配置 ViewPager 和其父布局的 android:clipChildren属性为”false”.

​            (android:clipChildren 表示是否限制子 View 在其范围内，默认为 true. 代码设置 setClipChildren(false);
​            因为如果 clipChildren 属性设置为 true,就表明我们要将 children 给 clip 掉，
​            就是说对于子元素来说，超出当前 view 的部分都会被切掉，那我们在这里把它设置成 false，
​            就表明超出 view 的部分，不要切掉，依然显示。

#### 2.设置 ViewPager 的父布局,`android:layerType="software"`,这样才能显示左右两边的图片,否则不显示为空白。

#### 3.设置幕后 item 的缓存数目。如果一屏展示的 pager 数目多的话就需要设置此项。

```java
viewPager.setOffscreenPageLimit(3); //具体缓存页数看需求
```

#### 4.设置页与页之间的间距。

```java
viewPager.setPageMargin(int marginPixls);// setPageMargin表示设置page之间的间距
```

#### 5.设置居中显示的 pager 距离屏幕左右两边的距离。

```xml
android:layout_marginLeft="50dp"
android:layout_marginRight="50dp"
```

#### 6.将 ViewPager 的父布局触摸事件分发给 viewpager，否则只能滑动 ViewPager 中间的那个 view 对象。

#### 7.必须在 ViewPager 的滑动监听中刷新界面才能看到效果。


​		

	链接：https://www.cnblogs.com/tangs/articles/5933233.html