**==Android开发小技巧==**<font size="5" color="#ee1234">@IntDef</font>

<font size="5" color="#ee1234">Tips:</font>
使用IntDef不仅可以使你的代码更具可读性，也可以让lint阻止你犯错，能够让你写代码更快


***Example 1:***
转换一组枚举到@IntDef注解里

```java
public enum Gravity{
    LEFT,TOP,RIGHT,BOTTOM,CENTER
}
```
```java
@IntDef({
        Gravity.LEFT,
        Gravity.TOP,
        Gravity.RIGHT,
        Gravity.BOTTOM,
        Gravity.CENTER
        })
@Retention(RetentionPolicy.CLASS)
public @interface Gravity {
    int LEFT = 0;
    int TOP = 1;
    int RIGHT = 2;
    int BOTTOM = 3;
    int CENTER = 4;
}
```

***Example 2:***
转换一组常量到@IntDef注解里

```java
public class Gravity_Outer{
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;
    public static final int CENTER = 4;
}
```
```java
@IntDef({
         Gravity_Outer.LEFT,
         Gravity_Outer.TOP,
         Gravity_Outer.RIGHT,
         Gravity_Outer.BOTTOM,
         Gravity_Outer.CENTER
         })
@Retention(RetentionPolicy.CLASS)
public @interface Gravity {

}
```


***Example 3:***
如果你有一大堆常量，又经常要对他们做switch判断。@IntDef可以帮你。你可以只用写一次IntDef接口，然后在switch判断分支实现。

**1.** Android Studio先安装IntDef插件；
**2.** 在做switch判断之前，先定义一个临时变量，使用@IntDef注解，然后将该变量传给switch判断，最后将光标移到switch处使用快捷键Alt+Enter，点击Add Missing @IntDef Constants，可快速生成case。


示例：
```java
@Gravity
int transformerType = type;
switch (transformerType) {
}
```
