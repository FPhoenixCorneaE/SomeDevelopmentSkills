### 一、情景分析
######	在开发中，我们会遇到某个图标可拖动的需求，我们会同时使用到onClick和onTouch事件，这个时候onClick和onTouch会发生冲突。

### 二、涉及知识点：Android的触摸事件
#### `Android 的触摸事件分为两类，即，我们使用手机，每次点击屏幕的时候，都会触发这两种事件之一：`
#### **1. MotionEvent.ACTION_DOWN --> MotionEvent.ACTION_UP，按下手指 ，然后松开手指。**
#### **2. MotionEvent.ACTION_DOWN --> MotionEvent.ACTION_MOVE --> MotionEvent.ACTION_UP，按下手指，移动，然后松开手指。**

#### `所谓的MotionEvent.ACTION_DOWN就是按下手指，MotionEvent.ACTION_MOVE就是移动手指，MotionEvent.ACTION_UP就是松开手指。`
#### `Android中把触摸事件封装成了一个类MotionEvent，用户的一次点击、触摸或者滑动都会产生一系列的MotionEvent。这个类的内容很简单，就两个东西：事件类型+坐标xy。`

#### 事件类型有四种：
##### - MotionEvent.ACTION_DOWN 表示用户的手指刚接触到屏幕。
##### - MotionEvent.ACTION_MOVE 表示用户的手指正在移动。
##### - MotionEvent.ACTION_UP 表示用户的手指从屏幕上抬起。
##### - MotionEvent.ACTION_CANCEL 表示事件被上层拦截时触发。

### 三、两种情况
#### **1. 单独使用setOnTouchListener()。**
#### **2. setOnTouchListener()和setOnclickListener()一起使用（不建议一起使用，可以将点击事件放在MotionEvent.ACTION_UP中）。**

#### onTouch返回ture和false的区别：
##### **1. 第一种情况，onTouch返回false的时候，只会执行MotionEvent.ACTION_DOWN方法，不会执行MotionEvent.ACTION_MOVE和MotionEvent.ACTION_UP。只有返回true的时候，三个都会执行。**
##### **2. 第二种情况，onTouch返回true时，不会执行onClick方法，为false的才会执行onClick方法。无论返回true还是false，onTouch的MotionEvent.ACTION_DOWN，MotionEvent.ACTION_MOVE，MotionEvent.ACTION_UP这三种事件都会执行。**

### 四、总结
#### `综合以上，解决onClick和onTouch冲突，在setOnTouchListenenr中返回true，并将点击事件放在MotionEvent.ACTION_UP事件中。监测MotionEvent.ACTION_MOVE事件，判断是否发生x或y轴的位移，用whetherMove记录状态，在MotionEvent.ACTION_UP事件中whetherMove为false的时候执行callOnClick()方法。`
