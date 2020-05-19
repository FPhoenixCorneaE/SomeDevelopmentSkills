	全屏主题,不能用**android:windowFullscreen**属性,而要用**android:windowTranslucentStatus**属性;

```java
<style name="AppTheme.Fullscreen" parent="AppTheme">
    <item name="android:windowTranslucentStatus">true</item>
    <item name="android:windowContentOverlay">@null</item>
</style>
```