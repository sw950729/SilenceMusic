package com.silence.music.main;

import com.angel.music.R;
import com.silence.music.base.BaseFragment;
import com.silence.music.base.BasePresenter;

/**
 * @author Silence
 * @date  2018/5/16
 */
public class LocalFragment extends BaseFragment {

    @Override
    public void initView() {
        initLoadingLayout(R.id.text);
    }

    @Override
    public void httpData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_local;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
