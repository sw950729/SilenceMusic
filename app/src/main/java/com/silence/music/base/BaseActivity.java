package com.silence.music.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.silence.music.base.ibase.IBasePresenter;
import com.silence.music.base.ibase.IView;
import com.silence.music.utils.ActivityStack;
import com.silence.music.utils.L;
import com.silence.music.utils.statusbar.StatusBarUtil;

/**
 * @author tinlone
 */

public abstract class BaseActivity<T extends IBasePresenter, V> extends AppCompatActivity implements IView<V> {

    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        StatusBarUtil.setStatusWhite(this);
        if (null != bindPresenter()) {
            mPresenter = bindPresenter();
        }
        initData(savedInstanceState);
        ActivityStack.getInstace().addActivity(this);
        // 始终竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 供子类初始化数据，相当于在onCreate()中的初始化代码
     *
     * @param savedInstanceState 0.0
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 绑定layoutID
     * @return layoutID
     */
    protected abstract int bindLayoutId();

    /**
     * 绑定Presenter
     */
    protected T bindPresenter() {
        return null;
    }

    /**
     * 界面跳转
     *
     * @param clazz
     */
    protected void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz
     * @param bundle
     */
    protected void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 将空视图或者无网络的视图显示在哪个布局上
     *
     * @return
     */
    protected View bindLoadingView() {
        return null;
    }

    @Override
    protected void onDestroy() {
        closeInputMethod();
        ActivityStack.getInstace().removeActivity(this);
        if (mPresenter != null) {
            L.i("onDestroy");
            mPresenter.detachView();
        }
        super.onDestroy();
    }


    /**
     * 收起软键盘
     */
    protected void closeInputMethod() {
        // 用于判断虚拟软键盘是否是显示的
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示加载弹框
     */
    @Override
    public void loading() {

    }

    /**
     * 隐藏加载弹框
     */
    @Override
    public void hiding() {

    }

    /**
     * 加载数据失败
     *
     * @param errorMessage
     * @param tag
     */
    @Override
    public void showDataError(String errorMessage, int tag) {
        hiding();
    }

    /**
     * 加载数据成功
     *
     * @param data
     */
    @Override
    public void showDataSuccess(V data) {
        hiding();
    }

    /**
     * 加载失败的View，显示无网络的视图
     */
    @Override
    public void showNetErrorView() {
        hiding();
    }

    /**
     * 加载不到数据的View
     */
    @Override
    public void showEmptyView(String msg, int img) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
