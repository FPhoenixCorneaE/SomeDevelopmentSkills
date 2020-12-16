### 某些机型 Toast 会显示 APP 名字，例如，小米 MIUI 版本。	



#### 解决方案：

自定义 Toast 布局，代码如下：

```java
private static Toast getToast(CharSequence text, int duration) {
    Toast mToast = new Toast(Utils.getContext());

    LinearLayout view = new LinearLayout(Utils.getContext());

    GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
    gradientDrawable.setColor(0x60000000);
    gradientDrawable.setCornerRadius(ConvertUtils.dp2px(30f));

    view.setBackground(gradientDrawable);
	view.setMinimumWidth(ConvertUtils.dp2px(200f));
    view.setGravity(Gravity.CENTER);
    view.setOrientation(LinearLayout.VERTICAL);
    view.setPadding(ConvertUtils.dp2px(15f), ConvertUtils.dp2px(5f),
            ConvertUtils.dp2px(15f), ConvertUtils.dp2px(5f));

    TextView textView = new TextView(Utils.getContext());
    textView.setText(text);
    textView.setTextColor(Color.WHITE);
    textView.setTextSize(13f);
    textView.setGravity(Gravity.CENTER);

    view.addView(textView);

    mToast.setView(view);
    mToast.setDuration(duration);
    return mToast;
}
```