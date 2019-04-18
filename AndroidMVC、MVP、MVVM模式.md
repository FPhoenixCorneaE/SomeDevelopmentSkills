<font size="5" color="#000000" face="隶书">**MVC、MVP、MVVM**</font>

**常见的软件架构设计模式（Architectural Pattern），不同于设计模式（Design Pattern），只是为了解决一类问题而总结出的抽象方法，它通过分离关注点来改进代码的组织方式，一种架构模式往往使用了多种设计模式。**

<br>

<font size="5" color="#000000" face="隶书">**MVC模式：**</font>

**1. MVC模式结构：**

- **Model（模型）:**

	- 代表一个存取数据的对象或 JAVA POJO。

	- Model层用来存储业务的数据，一旦数据发生变化，模型将通知有关的视图。

	- Model和View之间使用观察者模式，View事先在此Model上注册，进而观察Model，以便更新在Model上发生改变的数据。

	- Model不依赖View和Controller。

	- **也可以带有逻辑**，在数据变化时更新控制器。

	`POJO的内在含义是指那些没有从任何类继承、也没有实现任何接口，更没有被其它框架侵入的java对象。`

- **View（视图）:**

	- 代表模型包含的数据的可视化。

	- View和Controller之间使用策略模式，View引入Controller的实例来实现特定的响应策略。

	- 如果要实现不同的响应的策略只要用不同的Controller实例替换即可。

- **Controller（控制器）:**

	- 控制器是模型和视图之间的纽带，MVC将响应机制封装在controller对象中，当用户和你的应用产生交互时，控制器中的事件触发器就开始工作了。

	- 以观察者模式的方式实例化View并向对应的Model实例注册。

	- 控制数据流向模型对象，当Model发生变化时就去通知View做更新。

	- 使视图与模型分离开。


	`MVC允许在不改变视图的情况下改变视图对用户输入的响应方式，用户对View的操作交给了Controller处理，在Controller中响应View的事件调用Model的接口对数据进行操作，一旦Model发生变化便通知相关视图进行更新。`


`可以明显感觉到，MVC模式的业务逻辑主要集中在Controller，而前端的View其实已经具备了独立处理用户事件的能力，当每个事件都流经Controller时，这层会变得十分臃肿。而且MVC中View和Controller一般是一一对应的，捆绑起来表示一个组件，视图与控制器间的过于紧密的连接让Controller的复用性成了问题。`


**2. 优点：**

- 耦合性低
- 重用性高
- 生命周期成本低
- 部署快
- 可维护性高
- 有利软件工程化管理

**3. 缺点：**

- 没有明确的定义
- 不适合小型，中等规模的应用程序
- 增加系统结构和实现的复杂性
- 视图与控制器间的过于紧密的连接
- 视图对模型数据的低效率访问
- 一般高级的界面工具或构造器不支持模式

<br>

<font size="5" color="#000000" face="隶书">**MVP模式：**</font>

`MVP是从经典的MVC模式演变而来，它们的基本思想有相通的地方：Controller/Presenter负责逻辑的处理，Model提供数据，View负责显示。`


**1. 区别：**

- **MVC短板：**

	- View是可以直接访问Model，不可避免的还要包括一些业务逻辑。

	- Model不依赖于View，但是View是依赖于Model的。
	
	- 在开发中，activity充当Controller角色，但在实际开发中处理View的逻辑和角色，一旦业务界面复杂，activity会显得很庞大。

- **两者重大区别：**

	- 在MVP中View并不直接使用Model，它们之间的通信是通过Presenter (MVC中的Controller)来进行的，所有的交互都发生在Presenter内部。
	
	- 在MVC中View会直接从Model中读取数据而不是通过Controller。



**2. MVP模式结构：**

- <font size="3" color="#000000" face="隶书">**Model：**</font>主要数据结构，作为实现逻辑业务的核心。

- <font size="3" color="#000000" face="隶书">**View：**</font>主要显示界面使用。通过调用 Presenter 的接口，实现回调响应，更新数据。

- <font size="3" color="#000000" face="隶书">**Presenter：**</font>主要作为一个桥梁，Model 去访问一个网站数据，解析回来，通过View 接口提供给界面显示


`MVP模式相当于在MVC模式中又加了一个Presenter用于处理模型和逻辑，将View和Model完全独立开，在android开发的体现就是activity仅用于显示界面和交互，activity不参与模型结构和逻辑。`


**3. 优点：**

- 降低耦合度，实现了M层和V层的完全分离，可以修改V层不影响M层。

- 模块职责划分明显，层次清晰。

- P层可以复用，一个P可以对应多个V，不需要修改P的逻辑。

- 单元测试更加简单方便。

- 代码灵活度高。


**4. 缺点：**

- 由于对视图的渲染放在了Presenter中，V层和P层交互频繁。

- 如果Presenter过多地渲染了视图，往往会使得它与特定的视图的联系过于紧密。一旦视图需要变更，那么Presenter也需要变更了。

- 使用了接口来解耦模块之间的关联，代码量多，类变多了。在一些很简单的逻辑业务里面，可以不采用MVP。

<br>

<font size="5" color="#000000" face="隶书">**MVP和MVC对比**</font>

！[图 MVP和MVC对比.png](https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/MVP%E5%92%8CMVC%E5%AF%B9%E6%AF%94.png)

<br>

<font size="5" color="#000000" face="隶书">**MVVM模式：**</font>

！[图 MVVM模式.png](https://github.com/FPhoenixCorneaE/SomeDevelopmentSkills/blob/master/%E9%A2%84%E8%A7%88%E5%9B%BE/MVVM%E6%A8%A1%E5%BC%8F.png)

**1. MVVM模式的组成：**

- <font size="3" color="#000000" face="隶书">**Model：**</font>指任何一个领域模型(domain model)，它代表了真实情况的内容（一个面向对象的方法），或表示内容（以数据为中心的方法）的数据访问层。

- <font size="3" color="#000000" face="隶书">**View：**</font>就像在 MVC 和 MVP 模式下，View就是用户界面（UI）。

- <font size="3" color="#000000" face="隶书">**Viewmodel：**</font>是一个公开公共属性和命令的抽象的view。取代了 MVC 模式的 controller，或 MVP 模式的任命者(presenter)，MVVM 有一个驱动。在 viewmodel 中，这种驱动传达视图和数据绑定的通信。此 viewmodel 已被描述为该 model 中的数据的状态。

- <font size="3" color="#000000" face="隶书">**Binder机制：**</font>MVVM 模式中包含了说明性数据和命令绑定机制。微软的做法中，binder是一种叫 XAML 的标记语言。binder 使得开发人员不需要去编写样板化的代码来同步视图模型和视图。

**2. 优势：**

- View和Model双向绑定，一方的改变都会影响另一方，开发者不用再去手动修改UI的数据。

- 不需要findViewById也不需要butterknife，不需要拿到具体的View去设置数据绑定监听器等等，这些都可以用DataBinding完成。

- View和Model的双向绑定是支持生命周期检测的，不会担心页面销毁了还有回调发生，这个由lifeCycle完成。

- 不会像MVC一样导致Activity中代码量巨大，也不会像MVP一样出现大量的View和Presenter接口。项目结构更加低耦合。

- 更低的耦合把各个模块分开开发，分开测试，可以分给不同的开发人员来完成。


**3. 不足之处：**

- 对于简单的用户界面操作，执行 MVVM 的花费“ 过头了”。

- 对于较大的应用，推广 ViewModel 变得更加困难。

- 大型应用项目里数据绑定引起相当大的内存消耗。