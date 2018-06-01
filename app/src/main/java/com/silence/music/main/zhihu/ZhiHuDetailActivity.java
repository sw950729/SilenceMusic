package com.silence.music.main.zhihu;

import android.view.Window;

import com.angel.music.R;
import com.silence.music.base.BaseActivity;
import com.silence.music.base.BasePresenter;
import com.silence.music.main.zhihu.contract.IZhihuDetailContract;
import com.silence.music.main.zhihu.presenter.ZhihuDetailPresenter;

/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class ZhiHuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements IZhihuDetailContract.IZhihuDetailView {

    private String type;
    private String id;

    @Override
    public void initView() {
        if (getIntent() != null) {
            type = getIntent().getStringExtra("type");
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    public void httpData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    public ZhihuDetailPresenter bindPresenter() {
        return new ZhihuDetailPresenter(this);
    }
}
