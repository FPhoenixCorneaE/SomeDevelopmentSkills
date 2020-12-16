onTouchEvent，onClick 及 onLongClick 的调用机制：


	ACTION_DOWN --> OnLongClick --> ACTION_UP --> OnClick.


在 onLongClick() 方法 return false 的情况下，在 ACTION 后仍然出发 onClick() 方法。

​	     