[![](https://jitpack.io/v/liqinew/basefragment.svg)](https://jitpack.io/#liqinew/basefragment)
[![](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-%E6%9D%8E%E5%A5%87-orange.svg)](https://github.com/LiqiNew)
# BaseFragment
BaseFragment基类工具
#### Gradle远程依赖
**1：在项目根目录build.gradley**	<br>

```gradle
allprojects {
　　repositories {
  　　//依赖仓库
　　　maven { url 'https://jitpack.io' }
　　}
}
```

**2：依赖BaseFragment基类工具**<br>

```gradle
compile 'com.github.liqinew:basefragment:V.1.0.0'
```
### 代码使用方法
**继承com.liqi.fragment.BaseFragment**
```java
/**例子
 * Created by LiQi on 2017/12/23.
 */
public class TestFragment extends BaseFragment {
    /**
     * 赋值fragment布局ID
     * @return
     */
    @Override
    public int setLiayoutId() {
        return R.layout.xxxxx;
    }

    /**BaseFragment内部已经实现了OnClickListener。这里重写就可。
     *
     * <p>
     *     fragment视图中如果有点击事件的按钮，请在控件把此属性android:clickable="true"设置为true。
     *     控件属性android:clickable="true"设置为true之后，BaseFragment内部自动设置点击事件。
     *     控件点击事件会响应onClick(View v)回调方法。
     * </p>
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
    }
}
```
**BaseFragment内部方法**
```java
/**BaseFragment内部存储一个第三方对象
* 
* @param data 第三方对象
*/
BaseFragment.setData(Object data);

/**
* 取出第三方对象
* @param <V>
* @return
*/
BaseFragment.getData();

/**
* 查找指定ID控件
*
* @param id  控件ID
* @param <T>
* @return
*/
BaseFragment.$(int id);

/**
* fragment页面显示调用的方法。
* <p>
*     此方法让继承子类根据业务去操作。
* </p>
*/
BaseFragment.onShow();

/**
* 获取当前项目定义的Application对象
*
* @param <A> 继承Application的泛型
* @return
*/
BaseFragment.getApplicationNew();

/**
* 获取fragment依赖的activity
*
* @param <T>
* @return
*/
BaseFragment.getActivityNew();

/**
* 获取fragment依赖的父类Fragment
*
* @param <T>
* @return
*/
BaseFragment.getParentFragmentNew();
```
