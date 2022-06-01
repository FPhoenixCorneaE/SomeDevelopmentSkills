# Android换肤技术：

## 一、换肤方案

1. ##### 通过自定义 style 换肤

2. ##### 通过 Hook LayoutInflater 换肤

3. ##### Databinding + LiveData 换肤

4. ##### 通过自定义控件 + 全局广播实现换肤

5. ##### TG 换肤

<br>

## 二、自定义 style 换肤方案

### 1.基本原理

- [ ] 用户提前自定义一些 theme 主题，然后当设置主题的时候将制定主题对应的 id 记录到本地文件中，当 Activity `onResume()` 的时候，判断 Activity 当前的主题是否和之前设置的主题一致，不一致的话就调用当前 Activity 的 `recreate()` 方法进行重建。
- [ ] 还可以预定义一些属性，例如

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <attr name="theme_divider_color" format="color"/>
    <attr name="theme_background" format="color"/>
</resources>
```

- [ ] 然后在自定义主题中为这些预定义属性赋值，

```xml
<?xml version="1.0" encoding="utf-8"?>
<style name="AppTheme.Base" parent="Theme.MaterialComponents.Light.NoActionBar">
    <item name="theme_divider_color">@color/theme_divider_color</item>
    <item name="theme_background">@color/theme_background</item>
</style>
```

- [ ] 最后在布局文件中通过如下的方式引用这些自定义属性，

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.appcompat.widget.AppCompatTextView
    android:background="?attr/theme_background" />

<View 
    android:background="?attr/theme_divider_color"
    android:layout_width="match_parent"
    android:layout_height="0.5dp" />
```

### 2.总结

- [ ] #### 优点

  只要切换了主题这些自定义属性可以动态发生变化。

- [ ] #### 缺点

  在换肤之后需要重启 Activity，特别是当主页存在多个嵌套 Fragment 的时候，状态处理起来可能会特别复杂。

> 对于简单类型的应用，这种方案是一种方便、快捷的选择。

### 3.开源库

- [ ] #### [Colorful](https://github.com/garretyoder/Colorful)

<br>

## 三、Hook LayoutInflater 换肤方案

### 1.基本原理

- [ ] 通常当我们想要自定义 Layout 的 Factory 的时候可以调用下面两个方法将我们的 Factory 设置到系统的 LayoutInflater 中，

```java
public abstract class LayoutInflater {
    public void setFactory(Factory factory) {
        if (mFactorySet) {
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        }
        if (factory == null) {
            throw new NullPointerException("Given factory can not be null");
        }
        mFactorySet = true;
        if (mFactory == null) {
            mFactory = factory;
        } else {
            mFactory = new FactoryMerger(factory, null, mFactory, mFactory2);
        }
    }

    public void setFactory2(Factory2 factory) {
        if (mFactorySet) {
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        }
        if (factory == null) {
            throw new NullPointerException("Given factory can not be null");
        }
        mFactorySet = true;
        if (mFactory == null) {
            mFactory = mFactory2 = factory;
        } else {
            mFactory = mFactory2 = new FactoryMerger(factory, factory, mFactory, mFactory2);
        }
    }
    // ...
}
```

> 从上面的两个方法看出，`setFactory()` 方法底层有防重入校验，所以，如果想要手动进行赋值，需要使用反射修改 `mFactorySet`、`mFactory` 和 `mFactory2`。

- [ ] 当我们调用 inflater 从 xml 中加载控件的时候，将会走到如下代码真正执行加载操作，

```java
public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
    synchronized (mConstructorArgs) {
        // ....
        final Context inflaterContext = mContext;
        final AttributeSet attrs = Xml.asAttributeSet(parser);
        Context lastContext = (Context) mConstructorArgs[0];
        mConstructorArgs[0] = inflaterContext;
        View result = root;

        try {
            advanceToRootNode(parser);
            final String name = parser.getName();

            // 处理 merge 标签
            if (TAG_MERGE.equals(name)) {
                rInflate(parser, root, inflaterContext, attrs, false);
            } else {
                // 从 xml 中加载布局控件
                final View temp = createViewFromTag(root, name, inflaterContext, attrs);
                // 生成布局参数 LayoutParams
                ViewGroup.LayoutParams params = null;
                if (root != null) {
                    params = root.generateLayoutParams(attrs);
                    if (!attachToRoot) {
                        temp.setLayoutParams(params);
                    }
                }
                // 加载子控件
                rInflateChildren(parser, temp, attrs, true);
                // 添加到根控件
                if (root != null && attachToRoot) {
                    root.addView(temp, params);
                }
                if (root == null || !attachToRoot) {
                    result = temp;
                }
            }

        } catch (XmlPullParserException e) {
            /*...*/
        }
        return result;
    }
}
```

- [ ] 先来看通过 tag 创建 view 的逻辑，

```java
View createViewFromTag(View parent, String name, Context context, AttributeSet attrs, boolean ignoreThemeAttr) {
    // 老的布局方式
    if (name.equals("view")) {
        name = attrs.getAttributeValue(null, "class");
    }
    // 处理 theme
    if (!ignoreThemeAttr) {
        final TypedArray ta = context.obtainStyledAttributes(attrs, ATTRS_THEME);
        final int themeResId = ta.getResourceId(0, 0);
        if (themeResId != 0) {
            context = new ContextThemeWrapper(context, themeResId);
        }
        ta.recycle();
    }
    try {
        View view = tryCreateView(parent, name, context, attrs);
        if (view == null) {
            final Object lastContext = mConstructorArgs[0];
            mConstructorArgs[0] = context;
            try {
                if (-1 == name.indexOf('.')) {
                    view = onCreateView(context, parent, name, attrs);
                } else {
                    view = createView(context, name, null, attrs);
                }
            } finally {
                mConstructorArgs[0] = lastContext;
            }
        }
        return view;
    } catch (InflateException e) {
        // ...
    }
}

public final View tryCreateView(View parent, String name, Context context, AttributeSet attrs) {
    if (name.equals(TAG_1995)) {
        return new BlinkLayout(context, attrs);
    }

    // 优先使用 mFactory2 创建 view，mFactory2 为空则使用 mFactory，否则使用 mPrivateFactory
    View view;
    if (mFactory2 != null) {
        view = mFactory2.onCreateView(parent, name, context, attrs);
    } else if (mFactory != null) {
        view = mFactory.onCreateView(name, context, attrs);
    } else {
        view = null;
    }

    if (view == null && mPrivateFactory != null) {
        view = mPrivateFactory.onCreateView(parent, name, context, attrs);
    }
    return view;
}
```

> 可以看出，这里优先使用 mFactory2 创建 view，mFactory2 为空则使用 mFactory，否则使用 mPrivateFactory 加载 view。所以，如果我们想要对 view 创建过程进行 hook，就应该 hook 这里的 mFactory2。因为它的优先级最高。注意到这里的 `inflate` 方法中并没有循环，所以，第一次的时候只能加载根布局。那么根布局内的子控件是如何加载的呢？

- [ ] 这就用到了 `rInflateChildren` 这个方法，

```java
final void rInflateChildren(XmlPullParser parser, View parent, AttributeSet attrs,
        boolean finishInflate) throws XmlPullParserException, IOException {
    rInflate(parser, parent, parent.getContext(), attrs, finishInflate);
}

void rInflate(XmlPullParser parser, View parent, Context context, AttributeSet attrs, boolean finishInflate) throws XmlPullParserException, IOException {

    final int depth = parser.getDepth();
    int type;
    boolean pendingRequestFocus = false;

    while (((type = parser.next()) != XmlPullParser.END_TAG || parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {
        if (type != XmlPullParser.START_TAG) continue;

        final String name = parser.getName();
        if (TAG_REQUEST_FOCUS.equals(name)) {
            // 处理 requestFocus 标签
            pendingRequestFocus = true;
            consumeChildElements(parser);
        } else if (TAG_TAG.equals(name)) {
            // 处理 tag 标签
            parseViewTag(parser, parent, attrs);
        } else if (TAG_INCLUDE.equals(name)) {
            // 处理 include 标签
            if (parser.getDepth() == 0) {
                throw new InflateException("<include /> cannot be the root element");
            }
            parseInclude(parser, context, parent, attrs);
        } else if (TAG_MERGE.equals(name)) {
            // 处理 merge 标签
            throw new InflateException("<merge /> must be the root element");
        } else {
            // 这里处理的是普通的 view 标签
            final View view = createViewFromTag(parent, name, context, attrs);
            final ViewGroup viewGroup = (ViewGroup) parent;
            final ViewGroup.LayoutParams params = viewGroup.generateLayoutParams(attrs);
            // 继续处理子控件
            rInflateChildren(parser, view, attrs, true);
            viewGroup.addView(view, params);
        }
    }
    if (pendingRequestFocus) {
        parent.restoreDefaultFocus();
    }
    if (finishInflate) {
        parent.onFinishInflate();
    }
}
```

> 注意到该方法内部又调用了 `createViewFromTag` 和 `rInflateChildren` 方法，也就是说，这里通过**递归**的方式实现对整个 view 树的遍历，从而将整个 xml 加载为 view 树。

- [ ] 以上是安卓的 LayoutInflater 从 xml 中加载控件的逻辑，可以看出我们可以通过 hook `mFactory2` 实现对创建 view 的过程的“监听”。

### 2.总结

### 3.开源库

- [ ] #### [Android-Skin-Loader](https://github.com/fengjundev/Android-Skin-Loader)

1. ##### 基本换肤流程(以 Activity 为例)

   ###### 1.1 实现 ISkinUpdate, IDynamicNewView 接口，在 `onCreate()` 中调用 `setFactory()`，将自定义的 Factory 设置给了 LayoutInflator。

   ```java
   public class BaseActivity extends Activity implements ISkinUpdate, IDynamicNewView{
   
       private SkinInflaterFactory mSkinInflaterFactory;
   
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           mSkinInflaterFactory = new SkinInflaterFactory();
           getLayoutInflater().setFactory(mSkinInflaterFactory);
       }
   
       // ...
   }
   ```

   ```java
   public class SkinInflaterFactory implements Factory {
       
       private static final boolean DEBUG = true;
       private List<SkinItem> mSkinItems = new ArrayList<SkinItem>();
   
       @Override
       public View onCreateView(String name, Context context, AttributeSet attrs) {
           // 读取自定义属性 enable，这里用了自定义的 namespace
           boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
           if (!isSkinEnable){
               return null;
           }
           // 创建 view
           View view = createView(context, name, attrs);
           if (view == null){
               return null;
           }
           parseSkinAttr(context, attrs, view);
           return view;
       }
   
       private View createView(Context context, String name, AttributeSet attrs) {
           View view = null;
           try {
               // 兼容低版本创建 view 的逻辑（低版本是没有完整包名）
               if (-1 == name.indexOf('.')){
                   if ("View".equals(name)) {
                       view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                   } 
                   if (view == null) {
                       view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                   } 
                   if (view == null) {
                       view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                   } 
               } else {
                   // 新的创建 view 的逻辑
                   view = LayoutInflater.from(context).createView(name, null, attrs);
               }
           } catch (Exception e) { 
               view = null;
           }
           return view;
       }
   
       private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
           List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
           // 对 xml 中控件的属性进行解析
           for (int i = 0; i < attrs.getAttributeCount(); i++){
               String attrName = attrs.getAttributeName(i);
               String attrValue = attrs.getAttributeValue(i);
               // 判断属性是否支持，属性是预定义的
               if(!AttrFactory.isSupportedAttr(attrName)){
                   continue;
               }
               // 如果是引用类型的属性值
               if(attrValue.startsWith("@")){
                   try {
                       int id = Integer.parseInt(attrValue.substring(1));
                       String entryName = context.getResources().getResourceEntryName(id);
                       String typeName = context.getResources().getResourceTypeName(id);
                       // 加入属性列表
                       SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
                       if (mSkinAttr != null) {
                           viewAttrs.add(mSkinAttr);
                       }
                   } catch (NumberFormatException e) {
                       /*...*/
                   }
               }
           }
           if(!ListUtils.isEmpty(viewAttrs)){
               // 构建该控件的属性关系
               SkinItem skinItem = new SkinItem();
               skinItem.view = view;
               skinItem.attrs = viewAttrs;
               mSkinItems.add(skinItem);
               if(SkinManager.getInstance().isExternalSkin()){
                   skinItem.apply();
               }
           }
       }
   }
   ```

   > 这里自定义了一个 xml 属性，用来指定是否启用换肤配置。然后在创建 view 的过程中解析 xml 中定义的 view 的属性信息，比如，`background` 和 `textColor` 等属性。并将其对应的属性、属性值和控件以映射的形式记录到缓存中。当发生换肤的时候根据这里的映射关系在代码中更新控件的属性信息。

   以背景的属性信息为例，看下其 apply 操作，

   ```java
   public class BackgroundAttr extends SkinAttr {
   
       @Override
       public void apply(View view) {
           if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
               // 注意这里获取属性值的时候是通过 SkinManager 的方法获取的
               view.setBackgroundColor(SkinManager.getInstance().getColor(attrValueRefId));
           }else if(RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)){
               Drawable bg = SkinManager.getInstance().getDrawable(attrValueRefId);
               view.setBackground(bg);
           }
       }
   }
   ```

   > 如果是动态添加的 view，比如在 java 代码中，该库提供了 `dynamicAddSkinEnableView` 等方法来动态添加映射关系到缓存中。

   ###### 1.2 在 activity 的生命周期方法中注册监听换肤事件（观察者模式）。

   ```java
   public class BaseActivity extends Activity implements ISkinUpdate, IDynamicNewView{
       @Override
       protected void onResume() {
           super.onResume();
           SkinManager.getInstance().attach(this);
       }
   
       @Override
       protected void onDestroy() {
           super.onDestroy();
           SkinManager.getInstance().detach(this);
           // 清理缓存数据
           mSkinInflaterFactory.clean();
       }
   
       @Override
       public void onThemeUpdate() {
           if(!isResponseOnSkinChanging){
               return;
           }
           mSkinInflaterFactory.applySkin();
       }
       // ... 
   }
   ```

   > 当换肤的时候会通知到 Activity 并触发 `onThemeUpdate()` 方法，这里调用了 SkinInflaterFactory 的 applySkin() 方法。SkinInflaterFactory 的 applySkin() 方法中对缓存的属性信息遍历更新实现换肤。

2. ##### 皮肤包的加载逻辑
   ###### 2.1 通过自定义的 AssetManager 实现，类似于插件化。

   ```java
   public void load(String skinPackagePath, final ILoaderListener callback) {
       new AsyncTask<String, Void, Resources>() {
   
           protected void onPreExecute() {
               if (callback != null) {
                   callback.onStart();
               }
           };
   
           @Override
           protected Resources doInBackground(String... params) {
               try {
                   if (params.length == 1) {
                       String skinPkgPath = params[0];
                       
                       File file = new File(skinPkgPath); 
                       if(file == null || !file.exists()){
                           return null;
                       }
                       
                       PackageManager mPm = context.getPackageManager();
                       PackageInfo mInfo = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
                       skinPackageName = mInfo.packageName;
   
                       AssetManager assetManager = AssetManager.class.newInstance();
                       Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                       addAssetPath.invoke(assetManager, skinPkgPath);
   
                       Resources superRes = context.getResources();
                       Resources skinResource = new Resources(assetManager,superRes.getDisplayMetrics(),superRes.getConfiguration());
                       
                       SkinConfig.saveSkinPath(context, skinPkgPath);
                       
                       skinPath = skinPkgPath;
                       isDefaultSkin = false;
                       return skinResource;
                   }
                   return null;
               } catch (Exception e) { /*...*/ }
           };
   
           protected void onPostExecute(Resources result) {
               mResources = result;
               if (mResources != null) {
                   if (callback != null) callback.onSuccess();
                   notifySkinUpdate();
               }else{
                   isDefaultSkin = true;
                   if (callback != null) callback.onFailed();
               }
           };
       }.execute(skinPackagePath);
   }
   ```

   ###### 2.2 获取值的时候使用如下方法：

   ```java
   public int getColor(int resId){
       int originColor = context.getResources().getColor(resId);
       if(mResources == null || isDefaultSkin){
           return originColor;
       }
       
       String resName = context.getResources().getResourceEntryName(resId);
       int trueResId = mResources.getIdentifier(resName, "color", skinPackageName);
       int trueColor = 0;
       
       try{
           trueColor = mResources.getColor(trueResId);
       }catch(NotFoundException e){
           e.printStackTrace();
           trueColor = originColor;
       }
       return trueColor;
   }
   ```

3. ##### 总结

- 换肤需要继承自定义 activity

- 皮肤包和 APK 如果使用了资源混淆加载的时候就会出现问题

- 没处理属性值通过 `?attr` 的形式引用的情况

- 每个换肤的属性需要自己注册并实现

- 有些控件的一些属性可能没有提供对应的 java 方法，因此在代码中换肤就行不通

- 没有处理使用 style 的情况

- 基于 `android.app.Activity` 实现，版本太老

- 在 inflater 创建 view 的时候，其实只做了对属性的拦截处理操作，可以通过代理系统的 Factory 实现创建 view 的操作

- [ ] #### [ThemeSkinning](https://github.com/burgessjp/ThemeSkinning)

> 这个库是基于上面的 Android-Skin-Loader 开发的，在其基础之上做了许多的调整。

1. ##### 基于 AppCompactActivity 实现

```java
public class SkinBaseActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView {

    private SkinInflaterFactory mSkinInflaterFactory;
    private final static String TAG = "SkinBaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSkinInflaterFactory = new SkinInflaterFactory(this);
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
        changeStatusColor();
    }

    // ...
}
```

> 该库基于 AppCompactActivity 和 `LayoutInflaterCompat.setFactory2` 开发，同时，该库也提供了修改状态栏的方法，虽然能力比较有限。（换肤的时候也应该考虑状态栏和底部导航栏的适配情况）

2. ##### SkinInflaterFactory 的调整

```java
public class SkinInflaterFactory implements LayoutInflater.Factory2 {
    
    private Map<View, SkinItem> mSkinItemMap = new HashMap<>();
    private AppCompatActivity mAppCompatActivity;

    public SkinInflaterFactory(AppCompatActivity appCompatActivity) {
        this.mAppCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(String s, Context context, AttributeSet attributeSet) {
        return null;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // 沿用之前的一些逻辑
        boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        AppCompatDelegate delegate = mAppCompatActivity.getDelegate();
        View view = delegate.createView(parent, name, context, attrs);

        // 对字体兼容做了支持，这里是通过静态方式将其缓存到内存，动态新增和移除，加载字体之后调用 textview 的 settypeface 方法替换
        if (view instanceof TextView && SkinConfig.isCanChangeFont()) {
            TextViewRepository.add(mAppCompatActivity, (TextView) view);
        }

        if (isSkinEnable || SkinConfig.isGlobalSkinApply()) {
            if (view == null) {
                // 创建 view 的逻辑做了调整
                view = ViewProducer.createViewFromTag(context, name, attrs);
            }
            if (view == null) {
                return null;
            }
            parseSkinAttr(context, attrs, view);
        }
        return view;
    }

    // ...
}
```

3. ##### view 的创建逻辑

> 这里只不过将之前的创建 View 的操作收拢到了一个类中。

```java
class ViewProducer {
    private static final Object[] mConstructorArgs = new Object[2];
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap<>();
    private static final Class<?>[] sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};

    static View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }

        try {
            // 构造参数，缓存，复用
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attrs;

            if (-1 == name.indexOf('.')) {
                for (int i = 0; i < sClassPrefixList.length; i++) {
                    final View view = createView(context, name, sClassPrefixList[i]);
                    if (view != null) {
                        return view;
                    }
                }
                return null;
            } else {
                // 通过构造方法创建 view
                return createView(context, name, null);
            }
        } catch (Exception e) {
            return null;
        } finally {
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
        }
    }

    // ...
}
```

4. ##### 属性解析对 style 做了兼容处理

```java
private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
    List<SkinAttr> viewAttrs = new ArrayList<>();
    for (int i = 0; i < attrs.getAttributeCount(); i++) {
        String attrName = attrs.getAttributeName(i);
        String attrValue = attrs.getAttributeValue(i);
        if ("style".equals(attrName)) {
            // 对 style 的处理，从 theme 中获取 TypedArray 然后获取 resource id，再获取对应的信息
            int[] skinAttrs = new int[]{android.R.attr.textColor, android.R.attr.background};
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, skinAttrs, 0, 0);
            int textColorId = a.getResourceId(0, -1);
            int backgroundId = a.getResourceId(1, -1);
            if (textColorId != -1) {
                String entryName = context.getResources().getResourceEntryName(textColorId);
                String typeName = context.getResources().getResourceTypeName(textColorId);
                SkinAttr skinAttr = AttrFactory.get("textColor", textColorId, entryName, typeName);
                if (skinAttr != null) {
                    viewAttrs.add(skinAttr);
                }
            }
            if (backgroundId != -1) {
                String entryName = context.getResources().getResourceEntryName(backgroundId);
                String typeName = context.getResources().getResourceTypeName(backgroundId);
                SkinAttr skinAttr = AttrFactory.get("background", backgroundId, entryName, typeName);
                if (skinAttr != null) {
                    viewAttrs.add(skinAttr);
                }
            }
            a.recycle();
            continue;
        }
        if (AttrFactory.isSupportedAttr(attrName) && attrValue.startsWith("@")) {
            // 老逻辑
            try {
                //resource id
                int id = Integer.parseInt(attrValue.substring(1));
                if (id == 0) continue;
                String entryName = context.getResources().getResourceEntryName(id);
                String typeName = context.getResources().getResourceTypeName(id);
                SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
                if (mSkinAttr != null) {
                    viewAttrs.add(mSkinAttr);
                }
            } catch (NumberFormatException e) { /*...*/ }
        }
    }
    if (!SkinListUtils.isEmpty(viewAttrs)) {
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        skinItem.attrs = viewAttrs;
        mSkinItemMap.put(skinItem.view, skinItem);
        if (SkinManager.getInstance().isExternalSkin() ||
                SkinManager.getInstance().isNightMode()) {//如果当前皮肤来自于外部或者是处于夜间模式
            skinItem.apply();
        }
    }
}
```

5. ##### 对 fragment 的处理

> 在 Fragment 的生命周期方法结束的时候从缓存当中移除指定的 View。

```java
@Override
public void onDestroyView() {
    removeAllView(getView());
    super.onDestroyView();
}

protected void removeAllView(View v) {
    if (v instanceof ViewGroup) {
        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            removeAllView(viewGroup.getChildAt(i));
        }
        removeViewInSkinInflaterFactory(v);
    } else {
        removeViewInSkinInflaterFactory(v);
    }
}
```

6. ##### 总结

- 相对第一个框架改进了很多
- 没必要区分夜间主题

- 项目依赖 `'com.android.support:appcompat-v7:26.1.0'`，不支持 AndroidX

- [ ] #### [Android-skin-support](https://github.com/ximsfei/Android-skin-support)

> 相比于上面的库， Android-skin-support 的 star 数量更多，代码也更加先进（利用了一些新的特性）。

1. ##### 基于 activity lifecycle 自动注册 layoutinflator.factory

```java
public class SkinActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private SkinActivityLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(this);
        installLayoutFactory(application);
        // 注册监听
        SkinCompatManager.getInstance().addObserver(getObserver(application));
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (isContextSkinEnable(activity)) {
            installLayoutFactory(activity);
            // 更新 acitvity 的窗口的背景
            updateWindowBackground(activity);
            // 触发换肤...如果 view 没有创建是不是就容易导致 NPE?
            if (activity instanceof SkinCompatSupportable) {
                ((SkinCompatSupportable) activity).applySkin();
            }
        }
    }

    private void installLayoutFactory(Context context) {
        try {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            LayoutInflaterCompat.setFactory2(layoutInflater, getSkinDelegate(context));
        } catch (Throwable e) { /* ... */ }
    }

    // 获取 LayoutInflater.Factory2，这里加了一层缓存
    private SkinCompatDelegate getSkinDelegate(Context context) {
        if (mSkinDelegateMap == null) {
            mSkinDelegateMap = new WeakHashMap<>();
        }
        SkinCompatDelegate mSkinDelegate = mSkinDelegateMap.get(context);
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(context);
            mSkinDelegateMap.put(context, mSkinDelegate);
        }
        return mSkinDelegate;
    }
    // ...
}
```

```java
public final class LayoutInflaterCompat {
    
    public static void setFactory2(LayoutInflater inflater, LayoutInflater.Factory2 factory) {
        inflater.setFactory2(factory);
        if (Build.VERSION.SDK_INT < 21) {
            final LayoutInflater.Factory f = inflater.getFactory();
            if (f instanceof LayoutInflater.Factory2) {
                forceSetFactory2(inflater, (LayoutInflater.Factory2) f);
            } else {
                forceSetFactory2(inflater, factory);
            }
        }
    }

    // 通过反射的方式直接修改 mFactory2 字段
    private static void forceSetFactory2(LayoutInflater inflater, LayoutInflater.Factory2 factory) {
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            } catch (NoSuchFieldException e) { /* ... */ }
            sCheckedField = true;
        }
        if (sLayoutInflaterFactory2Field != null) {
            try {
                sLayoutInflaterFactory2Field.set(inflater, factory);
            } catch (IllegalAccessException e) { /* ... */ }
        }
    }
    // ...
}
```

2. ##### LayoutInflater.Factory2 的实现逻辑

```java
public class SkinCompatDelegate implements LayoutInflater.Factory2 {
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createView(parent, name, context, attrs);
        if (view == null) return null;
        // 加入缓存
        if (view instanceof SkinCompatSupportable) {
            mSkinHelpers.add(new WeakReference<>((SkinCompatSupportable) view));
        }
        return view;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = createView(null, name, context, attrs);
        if (view == null) return null;
        // 加入缓存，继承这个接口的主要是 view 和 activity 这些
        if (view instanceof SkinCompatSupportable) {
            mSkinHelpers.add(new WeakReference<>((SkinCompatSupportable) view));
        }
        return view;
    }

    public View createView(View parent, final String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        // view 生成逻辑被包装成了 SkinCompatViewInflater
        if (mSkinCompatViewInflater == null) {
            mSkinCompatViewInflater = new SkinCompatViewInflater();
        }
        List<SkinWrapper> wrapperList = SkinCompatManager.getInstance().getWrappers();
        for (SkinWrapper wrapper : wrapperList) {
            Context wrappedContext = wrapper.wrapContext(mContext, parent, attrs);
            if (wrappedContext != null) {
                context = wrappedContext;
            }
        }
        // 
        return mSkinCompatViewInflater.createView(parent, name, context, attrs);
    }
    // ...
}
```

3. ##### SkinCompatViewInflater 获取 view 的逻辑

```java
public final View createView(View parent, final String name, @NonNull Context context, @NonNull AttributeSet attrs) {
    // 通过 inflator 创建 view
    View view = createViewFromHackInflater(context, name, attrs);
    if (view == null) {
        view = createViewFromInflater(context, name, attrs);
    }
    // 根据 view 标签创建 view
    if (view == null) {
        view = createViewFromTag(context, name, attrs);
    }
    // 处理 xml 中设置的点击事件
    if (view != null) {
        checkOnClickListener(view, attrs);
    }
    return view;
}

private View createViewFromHackInflater(Context context, String name, AttributeSet attrs) {
    View view = null;
    for (SkinLayoutInflater inflater : SkinCompatManager.getInstance().getHookInflaters()) {
        view = inflater.createView(context, name, attrs);
        if (view == null) {
            continue;
        } else {
            break;
        }
    }
    return view;
}

private View createViewFromInflater(Context context, String name, AttributeSet attrs) {
    View view = null;
    for (SkinLayoutInflater inflater : SkinCompatManager.getInstance().getInflaters()) {
        view = inflater.createView(context, name, attrs);
        if (view == null) {
            continue;
        } else {
            break;
        }
    }
    return view;
}

public View createViewFromTag(Context context, String name, AttributeSet attrs) {
    // <view class="xxxx"> 形式的 tag，和 <xxxx> 一样
    if ("view".equals(name)) {
        name = attrs.getAttributeValue(null, "class");
    }
    try {
        // 构造参数缓存
        mConstructorArgs[0] = context;
        mConstructorArgs[1] = attrs;
        if (-1 == name.indexOf('.')) {
            for (int i = 0; i < sClassPrefixList.length; i++) {
                // 通过构造方法创建 view
                final View view = createView(context, name, sClassPrefixList[i]);
                if (view != null) {
                    return view;
                }
            }
            return null;
        } else {
            return createView(context, name, null);
        }
    } catch (Exception e) {
        return null;
    } finally {
        mConstructorArgs[0] = null;
        mConstructorArgs[1] = null;
    }
}
```

> 这里用来创建 view 的 inflator 是通过 `SkinCompatManager.getInstance().getInflaters()` 获取的。这样设计的目的在于暴露接口给调用者，用来自定义控件的 inflator 逻辑。比如，针对三方控件和自定义控件的逻辑等。

该库自带的一个实现

```java
public class SkinAppCompatViewInflater implements SkinLayoutInflater, SkinWrapper {
   @Override
    public View createView(Context context, String name, AttributeSet attrs) {
        View view = createViewFromFV(context, name, attrs);

        if (view == null) {
            view = createViewFromV7(context, name, attrs);
        }
        return view;
    }

    private View createViewFromFV(Context context, String name, AttributeSet attrs) {
        View view = null;
        if (name.contains(".")) {
            return null;
        }
        switch (name) {
            case "View":
                view = new SkinCompatView(context, attrs);
                break;
            case "LinearLayout":
                view = new SkinCompatLinearLayout(context, attrs);
                break;
            // ... 其他控件的实现逻辑
        }
    }
    // ...
}
```

> 可以看出实现的效果是根据要创建的标签的名称返回对应的包装类。比如，View 返回 SkinCompatView 的实例。也就是，根据映射关系，将不支持换肤的布局控件在 inflate 的时候统一更换成支持换肤的。

4. ##### 总结

* 功能强大，支持大部分基础控件、动态设置资源、多种加载策略、定制化、矢量图、AndroidX
* 自定义 view 加载逻辑，根据要创建的 view 类型使用对应的支持换肤的控件替换
* 当皮肤加载完毕之后会通知监听的控件进行换肤操作
* 相当于对 view 全部做了 hook 替换
* 如果运行时发现错误不容易排查
* 同一个 LayoutInflater 只能设置一次 Factory，容易和同类库产生冲突

<br>

## 四、Databinding + LiveData

> Databinding + LiveData 轻松实现无重启换肤

- [ ] #### [ThemeDemo](https://github.com/czy1121/ThemeDemo)

- 无重启动态换肤(不需要recreate())
- 无额外依赖(Databinding + LiveData 本身几乎开发必备)
- 低侵入性
- 无需制作皮肤包
- AppCompat 和 Material 组件默认支持(少量属性需要额外支持或适配)
- 自定义 View / 第三方 View 适配过程简单(写个绑定适配器就行了)
- 不需要使用 LayoutInflater.Factory

<br>

## 五、通过自定义控件 + 全局广播实现换肤

> 这种方案跟前面 Hook LayoutInflator 的自动替换 view 的方案差不多。不过，这种方案不需要做 hook，而是对应用内常用的控件全部做一遍自定义。自定义控件内部监听换肤的事件。当自定义控件接收到换肤事件的时候，自定义控件内部触发换肤逻辑。不过这种换肤的方案相对于上述通过 Hook LayoutInflator 的方案而言，可控性更好一些。

<br>

## 六、TG 换肤

> TG 的换肤只支持夜间和日间主题之间的切换，所以，相对上面几种方案 ，TG 的换肤就简单得多。
>
> TG 在做页面布局的时候做了一件很疯狂的事情——没有使用任何 xml 布局，所有布局都是通过 java 代码实现的。
>
> 为了支持对主题的自定义 TG 把项目内几乎所有的颜色分别定义了一个名称，对以文本形式记录到一个文件中，数量非常多，然后将其放到 assets 下面，应用内通过读取这个资源文件来获取各个控件的颜色。

<br>

