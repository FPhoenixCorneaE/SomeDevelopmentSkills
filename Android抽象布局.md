**Android抽象布局：**

<font size="5" color="#000000" face="隶书">**include、merge、ViewStub**</font>


**1.** `布局重用<include/>`

 - include标签可以使用单独的layout属性，这个也是必须使用的。

 - 可以使用其他属性。include标签若指定了id属性，而你的layout也定义了id，则你的layout的id会被覆盖。

	<font size="3" color="#ff0000" face="隶书">**解决方案：**</font>可以直接查找子控件的id，或者查找include标签的id来查找子控件。

 - 在include标签中所有的Android：layout_* 都是有效的，前提是必须要写layout_width和layout_height两个属性。

 - 布局中可以包含两个相同的include标签，但必须设定两个不同的id，原因是在R.java文件中不同的id对应不同的控件，一个id只能对应一个控件。

**2.** `减少视图层级<merge/>`

- merge标签在UI的结构优化中起着非常重要的作用，它可以删减多余的层级，优化UI。

- merge多用于替换FrameLayout或者当一个布局包含另一个时，merge标签消除视图层次结构中多余的视图组。例如，你的主布局文件是垂直布局，引入了一个垂直布局的include，这时include布局使用的LinearLayout就没意义了，使用的话反而减慢你的UI表现，这时可以使用merge标签优化。

**3.** `需要时使用<ViewStub/>`

- ViewStub标签最大的优点是当你需要时才会加载，使用它并不会影响UI初始化时的性能。各种不常用的布局像进度条、显示错误信息等可以使用ViewStub标签，以减少内存使用量，加快渲染速度。