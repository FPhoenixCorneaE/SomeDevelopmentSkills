### Activity 一共有 4 种 LaunchMode（启动模式）：

#### 1.standard

#### 2.singleTop

#### 3.singleTask

#### 4.singleInstance


我们可以在 AndroidManifest.xml 配置 <activity> 的 Android:launchMode 属性为以上四种之一即可。



### 一、standard

standard 模式是默认的启动模式，不用为 <activity> 配置 android:launchMode 属性即可，当然也可以指定值为 standard。

**standard 模式的原理：**

每次跳转系统，不管有没有已存在的实例，都会在 task 中生成一个新的 Activity 实例，并且放于栈结构的顶部。



### 二、singleTop

为 <activity> 指定属性 `android:launchMode="singleTop"`，系统就会按照 singleTop 启动模式处理跳转行为。

**singleTop模式的原理：**

跳转时系统会先在栈结构中寻找是否有一个 Activity 实例正位于栈顶，如果有则不再生成新的，而是直接使用。如果有 Activity 实例但不是位于栈顶，则重新生成一个实例。



### 三、singleTask

为 <activity> 指定属性 `android:launchMode="singleTask"`，系统就会按照 singleTask 启动模式处理跳转行为。

**singleTask 模式的原理：**

如果发现有对应的 Activity 实例，则使此 Activity 实例之上的其他 Activity 实例统统出栈，使此 Activity 实例成为栈顶对象，显示到幕前。

​		

### 四、singleInstance

为 <activity> 指定属性 `android:launchMode="singleInstance"`，系统就会按照 singleInstance 启动模式处理跳转行为。

**singleInstance 模式的原理：**

singleInstance 启动模式可能是最复杂的一种模式，这种启动模式比较特殊，因为它会启用一个新的栈结构，将 Acitvity 放置于这个新的栈结构中，并保证不再有其他 Activity 实例进入。