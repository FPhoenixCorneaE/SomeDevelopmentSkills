
最近做登录，需要用到一张验证码图片，所以请求接口直接返回一张图片。返回类型是文件型。


我用的网络框架是 retrofit + okhttp3 + rxjava + rxandroid。

请求接口如下：

Presenter 中的方法：


```java
@Override
public void getImageVerifyCode() {
    subscribe(iModel.getImageVerifyCode(
        ConvertUtils.dp2px(50f),
        ConvertUtils.dp2px(20f), 
        20, 
        4, 
        new RSCallback<ResponseBody>() {
                @Override
                public void onSuccess(ResponseBody data) {
                    if (data != null) {
                        InputStream inputStream = data.byteStream(); // 得到图片的流
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        iView.setImageVerifyCode(bitmap);
                    }
                }

                @Override
                public void onFailure(String msg) {
                }
            }));
}
```


Presenter 父类：

```java
/**
* 基于Rx的Presenter封装,控制订阅的生命周期在Activity、Fragment的生命周期之内
*/
public class RxPresenter<V extends BaseView> implements BasePresenter {

private CompositeSubscription mCompositeSubscription;
public V iView;

public RxPresenter(V view) {
    iView = view;
    iView.setPresenter(this);
}

/**
 * 取消订阅
 */
public void unSubscribe() {
    iView = null;
    if (mCompositeSubscription != null) {
        mCompositeSubscription.unsubscribe();
    }
}

/**
 * 添加订阅
 *
 * @param subscription 订阅
 */
public void subscribe(Subscription subscription) {
    if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
    }
    mCompositeSubscription.add(subscription);
}
}
```


Model 中的方法：

```java
@Override
public Subscription getImageVerifyCode(int width, int height, int textSize, int length, final RSCallback<ResponseBody> rsCallback) {
    return LoginAPI.getDefault().getImageVerifyCode(width, height, textSize, length, new Subscriber<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            LogUtils.e(e);
            rsCallback.onFailure(e);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            rsCallback.onSuccess(responseBody);
        }
    });
}
```


API 中的方法：

```java
/**
 * 获取图片验证码
 *
 * @param width    图片宽度
 * @param height   图片高度
 * @param textSize 验证码字体大小
 * @param length   验证码长度
 */
@SuppressWarnings("unchecked")
public Subscription getImageVerifyCode(int width, int height, int textSize, int length, Subscriber<ResponseBody> subscriber) {
    Observable observable = RetrofitManager.getRetrofit().create(Login.class).getImageVerifyCode(width, height, textSize, length);
    return RxUtils.setSubscribe(observable, subscriber);
}
```

​	

Retrofit 类中的抽象方法：

```java
/**
 * 获取图片验证码
 *
 * @param width    图片宽度
 * @param height   图片高度
 * @param textSize 验证码字体大小
 * @param length   验证码长度
 */
@GET("/Image/gvcode")
Observable<ResponseBody> getImageVerifyCode(@Query("w") int width, @Query("h") int height,
                                            @Query("fsize") int textSize, @Query("length") int length);
```

​				

Rx 工具类方法：	

```java
/**
 * 设置订阅
 */
@SuppressWarnings("unchecked")
public static <T> Subscription setSubscribe(Observable<T> observable, Subscriber<? super T> subscriber) {
    return observable.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            //回调到主线程
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber);
}
```

​	

Callback 接口：

```java
public abstract class RSCallback<D> {

public abstract void onSuccess(D data);

public abstract void onFailure(String msg);

public void onFailure(Throwable e) {
    String msg;
    if (e instanceof HttpException || e instanceof UnknownHostException) {
        onFailure(msg = ResourceUtils.getString(R.string.warm_prompt_error_server));
    } else if (!NetworkUtils.isConnected() || e instanceof SocketTimeoutException) {
        onFailure(msg = ResourceUtils.getString(R.string.warm_prompt_network_error));
    } else {
        onFailure(msg = e.getLocalizedMessage());
    }
    ToastUtils.showShort(msg);
    LogUtils.e(msg);
}
}
```


​	

本来一开始是想直接用 Bitmap 或者 InputStream 作为返回值的，但是报了一个异常 MalformedJsonException，具体描述是：
**com.google.gson.JsonSyntaxException: com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 7 path $**

在这里 https://stackoverflow.com/questions/27485346/malformedjsonexception-with-retrofit-api 找到了如下解决方案：

```java
Gson gson = new GsonBuilder()
    .setLenient()
    .create();

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("http://whatever.com")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build(); 
```
但还是报了如下错误：**Failed to invoke public java.io.InputStream() with no args**。但是没找到解决方案。

后来换了一个思路，将返回值改为 Response 或者 ResponseBody 就没问题了。