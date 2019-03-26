**一、ADB Idea**

1. 用途：方便清除应用本地数据等，功能方便简单
 
2. 快捷键：
	windows : ctrl + alt + shift + a
	osx : cmd + shift + a

3. 主要功能：

	 - ADB Uninstall App  卸载当前app
	 
	 - ADB Kill App  杀掉当前app进程
	 
	 - ADB Start App  启动当前app
	 
	 - ADB Restart App  重启当前app
	 
	 - ADB Clear App Data  清除当前app数据
	 
	 - ADB Clear App Data and Restart  清除当前app数据并重启app

<br/>

**二、Alibaba Java Coding Guidelines**

1. 用途：阿里巴巴推出的一款插件，主要就是规范代码使用。

<br/>

**三、Android Code Generator**

1. 用途：根据布局文件快速生成对应的Activity，Fragment，Adapter，Menu。

2. 如何使用：
	xml文件你都能右键弹出Generate Android Code 菜单项，选择要生成的代码类型（activity、fragment、adapter）
	
3. 主要功能：

	- 从layout中生成Activity类
	
	- 从layout中生成Fragment类
	
	- 从item layout中生成Adapter类
	
	- 从menu xml中生成menu代码
	
	- 根据代码生成文件
	
	- 创建文件之前可以预览，可以修改了再创建文件
	
	- detection of project package
	
	- detection of source directories in project
	
	- 可以在Preferences中编辑生成代码的模版：Activity, Fragment, Adapter, Menu

<br/>

**四、GenerateFindViewById**

1. 用途：该插件就会遍历一个布局layout，并将有id的控件使用findviewbyid方法。

2. 快捷键：
	alt+ctrl+E，或者是alt+insert
	
<br/>

**五、LayoutFormatter**

1. 用途：用来格式化布局xml文件的代码规范。

2. 如何使用：
	在一个layout布局文件中右键，之后选择refactor->reformat layout xml。
	
<br/>

**六、Material Theme Ui**

1. 用途：android sutdio主题，属于material风格的。

2. 如何使用：
	如果觉得丑，可以去一个网站下载编辑器的主题，网址：http://color-themes.com/?view=index&layout=Generic&order=popular&search=&page=1
	
	下载好后，不需要解压，你下载 的是一个jar包，直接使用android studio导入，file->import setting,找到你刚才jar包下载的位置，选择完毕直接导入就OK了。
	
<br/>

**七、Selector Drawable Generator**

1. 用途：按照图片名字生成一个selector的xml文件。

2. 命名规则如下：

	|   _normal  |  (默认状态)|   
	| --- | --- | 
	| _pressed|	state_pressed|
	| _focused|	state_focused|
	| _disabled|	state_enabled|
	| _checked|	state_checked|
	| _selected|	state_selected|
	| _hovered|	state_hovered|
	| _checkable|	state_checkable|
	| _activated|	state_activated|
	| _windowfocused|	state_window_focused|
	
<br/>

**八、Sexy Editor**

1. 用途：可以在代码区后增加背景图片并调整背景的透明度。

2. 如何使用：
	Settings->Other Settings->SexyEditor
	
<br/>

**九、SVG2VectorDrawable**

1. 用途：将svg转化成xml文件。

<br/>

**十、GsonFormat**

1. 用途：根据JSONObject格式的字符串,自动生成实体类参数。

2. 快捷键：alt+insert

<br/>

**十一、Android Studio Prettify**

1. 如何使用：

	- 选中字符串鼠标右键选择Extract String resource

	- 找到布局文件(如activity_main)－>右键－>generate..(command ＋N)-->View  variables(局部属性) /View Field(全局属性)


2. 主要功能：

	- 可以将代码中的字符串写在string.xml文件中

	- 这个插件还可以自动书写findViewById

<br/>

**十二、PermissionsDispatcher plugin**

1. 用途：自动生成6.0权限的代码。

<br/>

**十三、Android Parcelable code generator**

1. 一键生成Parcalable相关代码。

2. 快捷键：alt+insert

<br/>

**十四、Angular Component Folding**
