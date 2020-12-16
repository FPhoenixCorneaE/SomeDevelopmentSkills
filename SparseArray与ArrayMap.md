### 在 Android，当我们需要定义 HashMap 时，我们可以使用 SparseArray 来取得更好的性能。

**SparseArray** 是 Android 里为 **HashMap** 专门写的类，目的是提高效率，其核心是折半查找函数（**BinarySearch**）。

**SparseArray** 满足条件：

* 数据量不大，最好在千级以内；

* key 必须为 int 类型。

**ArrayMap** 使用场景：

* 数据量不大，最好在千级以内；

* 数据结构类型为 Map 类型。