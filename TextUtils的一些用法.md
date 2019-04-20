<font size="5" color="#000000" face="隶书">**TextUtils的一些用法**</font>

**1. TextUtils.isEmpty(CharSequence str)**

字符串是否为null或""。

**2. TextUtils.isDigitsOnly(CharSequence str)**

字符串是否全是数字。

**3. TextUtils.isGraphic(CharSequence str)**

字符串是否含有可打印的字符。

**4. TextUtils.concat(CharSequence... text)**

拼接多个字符串。

**5. TextUtils.getTrimmedLength(CharSequence s)**

去掉字符串前后两端空格(相当于trim())之后的长度。

**6. TextUtils.getReverse(CharSequence source, int start, int end)**

在字符串中，start和end之间字符的逆序。

**7. TextUtils.join(CharSequence delimiter, Object[] tokens)**
**TextUtils.join(CharSequence delimiter, Iterable tokens)**

在数组中每个元素之间使用delimiter来连接。

**8. TextUtils.split(String text, String expression)**

以expression来分组。

**TextUtils.split(String text, Pattern pattern)**

以pattern正则表达式来分组。

**9. TextUtils.htmlEncode(String s)**

使用html编码字符串。

**10. TextUtils.ellipsize(CharSequence text,TextPaint p,float avail,TruncateAt where)**

获取TextView已setMaxLines后，显示出来的字内容。

**eg：** 在RecyclerView的item中展开、收起文案

```java
//文字描述
if (!TextUtils.isEmpty(discoverGraphicItemBean.getTextContent())) {
    holder.getView(R.id.tv_desc).setVisibility(View.VISIBLE);
    ((TextView) holder.getView(R.id.tv_desc)).setText(discoverGraphicItemBean.getTextContent());
    ((TextView) holder.getView(R.id.tv_desc)).setMaxLines(5);
    ((TextView) holder.getView(R.id.tv_desc)).setEllipsize(TextUtils.TruncateAt.END);
    holder.setText(R.id.tv_expand_collapse, ResUtil.getS(R.string.expand));
    ((ImageView) holder.getView(R.id.iv_expand_collapse)).setImageResource(R.drawable.recommend_icon_arrow_down_black);

    holder.getView(R.id.tv_desc).getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    holder.getView(R.id.tv_desc).getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    int paddingLeft = holder.getView(R.id.tv_desc).getPaddingLeft();
                    int paddingRight = holder.getView(R.id.tv_desc).getPaddingRight();

                    int lineWidth = holder.getView(R.id.tv_desc).getWidth() - paddingLeft - paddingRight;
                    CharSequence ellipsize = TextUtils.ellipsize(discoverGraphicItemBean.getTextContent(), ((TextView) holder.getView(R.id.tv_desc)).getPaint(), lineWidth * 5, TextUtils.TruncateAt.END);

                    if (TextUtils.equals(ellipsize, discoverGraphicItemBean.getTextContent())) {
                        holder.getView(R.id.ll_expand_collapse).setVisibility(View.GONE);
                    } else {
                        holder.getView(R.id.ll_expand_collapse).setVisibility(View.VISIBLE);
                    }
                }
            });
} else {
    holder.getView(R.id.tv_desc).setVisibility(View.GONE);
    holder.getView(R.id.ll_expand_collapse).setVisibility(View.GONE);
}

//展开收起点击监听
holder.getView(R.id.ll_expand_collapse).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (ResUtil.getS(R.string.expand).contentEquals(((TextView) holder.getView(R.id.tv_expand_collapse)).getText())) {
            holder.setText(R.id.tv_expand_collapse, ResUtil.getS(R.string.fold));
            ((ImageView) holder.getView(R.id.iv_expand_collapse)).setImageResource(R.drawable.recommend_icon_arrow_up_black);
            ((TextView) holder.getView(R.id.tv_desc)).setMaxLines(Integer.MAX_VALUE);
            ((TextView) holder.getView(R.id.tv_desc)).setEllipsize(null);
        } else {
            holder.setText(R.id.tv_expand_collapse, ResUtil.getS(R.string.expand));
            ((ImageView) holder.getView(R.id.iv_expand_collapse)).setImageResource(R.drawable.recommend_icon_arrow_down_black);
            ((TextView) holder.getView(R.id.tv_desc)).setMaxLines(5);
            ((TextView) holder.getView(R.id.tv_desc)).setEllipsize(TextUtils.TruncateAt.END);
        }
    }
});
```