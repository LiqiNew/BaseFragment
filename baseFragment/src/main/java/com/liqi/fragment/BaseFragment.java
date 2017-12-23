package com.liqi.fragment;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Fragment基类
 *
 * @author Liqi
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
    private View view;
    private Object mData;

    public <V extends Object > V getData() {
        return (V) mData;
    }

    public void setData(Object data) {
        mData = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(setLiayoutId(), container, false);
            pollFindWidget(this.view);
        }
        return view;
    }

    /**
     * 轮询找到View布局里面的控件
     *
     * @param view
     */
    private void pollFindWidget(View view) {
        if (!(view instanceof ViewGroup)) {
            judgeWdgetId(view, view.getId());
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                for (int j = 0; j < childCount; j++) {
                    View childAt = viewGroup.getChildAt(j);
                    int id = childAt.getId();
                    // 布局轮询获取控件
                    if (childAt instanceof ViewGroup) {
                        judgeWdgetId(childAt, id);
                        pollFindWidget(childAt);
                    } else {
                        judgeWdgetId(childAt, id);
                    }
                }
            } else {
                judgeWdgetId(view, view.getId());
            }
        }
    }

    /**
     * 设置点击事件
     *
     * @param childAt 控件
     * @param id      控件ID
     */
    private void judgeWdgetId(View childAt, int id) {
        if (id > 0) {
            // 那些控件不能设置点击事件，把它排除掉
            if (!(childAt instanceof ListView)
                    && !(childAt instanceof GridView)
                    && !(childAt instanceof ViewPager)
                    && !(childAt instanceof HorizontalScrollView)
                    && !(childAt instanceof ScrollView)
                    && !(childAt instanceof WebView))
                if (childAt.isClickable()) {
                    childAt.setOnClickListener(this);
                }
        }
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != view) {
            ViewGroup group = (ViewGroup) view.getParent();
            if (null != group) {
                group.removeView(view);
            }
        }
    }

    /**
     * 查找指定ID控件
     *
     * @param id  控件ID
     * @param <T>
     * @return
     */
    protected <T extends View> T $(int id) {
        return (T) view.findViewById(id);
    }


    /**
     * 赋值fragment布局ID
     *
     * @return
     */
    public abstract
    @LayoutRes
    int setLiayoutId();

    /**
     * fragment页面显示调用的方法?
     */
    public void onShow() {

    }

    ;

    /**
     * 此页面控件赋值的点击事件，如果需要点击事件，就的重载此方法?
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 获取当前项目定义的Application对象
     *
     * @param <A> 继承Application的泛型
     * @return
     */
    protected <A extends Application> A getApplicationNew() {
        return (A) getActivityNew().getApplication();
    }

    /**
     * 获取fragment依赖的activity
     *
     * @param <T>
     * @return
     */
    public <T extends Activity> T getActivityNew() {
        return (T) getActivity();
    }

    /**
     * 获取fragment依赖的父类Fragment
     *
     * @param <T>
     * @return
     */
    public <T extends Fragment> T getParentFragmentNew() {
        return (T) getParentFragment();
    }
}
