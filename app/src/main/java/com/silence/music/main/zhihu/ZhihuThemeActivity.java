package com.silence.music.main.zhihu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.angel.music.R;
import com.moudle.base.BaseActivity;
import com.moudle.utils.StatusBarUtil;
import com.moudle.view.SimpleToolbar;
import com.silence.music.adapter.ZhihuListAdapter;
import com.silence.music.bean.zhihu.SectionListBean;
import com.silence.music.bean.zhihu.ThemeListBean;
import com.silence.music.main.zhihu.contract.IZhihuThemeContract;
import com.silence.music.main.zhihu.presenter.ZhihuThemePresenter;

import java.util.ArrayList;
import java.util.List;

public class ZhihuThemeActivity extends BaseActivity<ZhihuThemePresenter> implements IZhihuThemeContract.IZhihuThemeView {

    private SimpleToolbar toolbar;
    private RecyclerView recycler;
    private String type;
    private String id;
    private ZhihuListAdapter adapter;
    private List<Object> data = new ArrayList<>();

    @Override
    public void initView() {
        if (getIntent() != null) {
            type = getIntent().getStringExtra("type");
            id = getIntent().getStringExtra("id");
        }
        toolbar = findViewById(R.id.toolbar);
        recycler = findViewById(R.id.recycler);
        StatusBarUtil.transparentStatusBarWithToolbar(this, toolbar);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ZhihuListAdapter(0, data);
        recycler.setAdapter(adapter);
        if ("section".equals(type)) {
            toolbar.setTitle("专栏列表");
        } else if ("themes".equals(type)) {
            toolbar.setTitle("主题列表");
        }
        toolbar.setBackIconOnClickListener(v -> onBackPressed());
    }

    @Override
    public void httpData() {
        if ("section".equals(type)) {
            presenter.getSectionDetail(id);
        } else if ("themes".equals(type)) {
            presenter.getThemeDetail(id);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activivty_theme;
    }

    @Override
    public ZhihuThemePresenter bindPresenter() {
        return new ZhihuThemePresenter(this);
    }

    @Override
    public void showThemeDetail(ThemeListBean themeListBean) {
        data.addAll(themeListBean.getStories());
        adapter.setNewData(data);
    }

    @Override
    public void showSectionDetail(SectionListBean sectionListBean) {
        data.addAll(sectionListBean.getStories());
        adapter.setNewData(data);
    }
}
