### ImageView 的一些属性妙用：

#### 1.tint：

给 src 的 Drawable 资源图片着色，可以将图片渲染成指定的颜色。

#### 2.src：

设置 ImageView 的 drawable (如图片，也可以是颜色，但是需要指定 View 的大小)。

#### 3.adjustViewBounds：

是否保持宽高比。需要与 maxWidth、maxHeight 一起使用，单独使用没有效果。

#### 4.cropToPadding：

是否截取指定区域用空白代替。单独设置无效果，需要与 scrollX 或者 scrollY 一起使用。

#### 5.maxHeight：

设置 View 的最大高度，单独使用无效，需要与 setAdjustViewBounds 一起使用。如果想设置图片固定大小，又想保持图片宽高比，需要如下设置：

* 设置setAdjustViewBounds为true；

* 设置maxWidth、maxHeight；

* 设置layout_width和layout_height为wrap_content。

#### 6.maxWidth：

设置View的最大宽度。同上。

#### 7.scaleType：

设置图片的填充方式。

* **matrix**：用矩阵来绘图。
* **fitXY**：拉伸图片（不按比例）以填充View的宽高。
* **fitStart**：按比例拉伸图片，拉伸后图片的高度为View的高度，且显示在View的左边。
* **fitCenter**：按比例拉伸图片，拉伸后图片的高度为View的高度，且显示在View的中间。
* **fitEnd**：按比例拉伸图片，拉伸后图片的高度为View的高度，且显示在View的右边。
* **center**：按原图大小显示图片，但图片宽高大于View的宽高时，截图图片中间部分显示。
* **centerCrop**：按比例放大原图直至等于某边View的宽高显示。
* **centerInside**：当原图宽高小于或等于View的宽高时，按原图大小居中显示；反之将原图缩放至View的宽高居中显示。