package com.silence.music.main.zhihu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.angel.music.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.moudle.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.music.adapter.ZhiHuAdapter;
import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;
import com.silence.music.bean.zhihu.ZhiHuDailyHeader;
import com.silence.music.bean.zhihu.ZhiHuHotNewsHeader;
import com.silence.music.bean.zhihu.ZhiHuSectionHeader;
import com.silence.music.bean.zhihu.ZhiHuThemesHeader;
import com.silence.music.main.zhihu.contract.IZhihuContract;
import com.silence.music.main.zhihu.presenter.ZhihuPresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class ZhiHuFragment extends BaseFragment<ZhihuPresenter> implements IZhihuContract.IZhihuView, OnRefreshListener {

    private Banner banner;
    private SmartRefreshLayout refreshLayout;
    private ZhiHuAdapter adapter;
    private List<String> imagesUrl = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    @Override
    public void initView() {
        initLoadingLayout(R.id.refreshLayout);
        refreshLayout = mView.findViewById(R.id.refreshLayout);
        RecyclerView recycler = mView.findViewById(R.id.recycler);
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.banner_layout, (ViewGroup) refreshLayout.getParent(), false);
        banner = headerView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context)
                                .load(path)
                                .into(imageView);
                    }
                });
        adapter = new ZhiHuAdapter();
        adapter.addHeaderView(headerView);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        recycler.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void httpData() {
        presenter.getNewsData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected ZhihuPresenter bindPresenter() {
        return new ZhihuPresenter(this);
    }

    @Override
    public void showData() {
        refreshLayout.finishRefresh();
        if (null != mLoadingViewHelper) {
            mLoadingViewHelper.showDataView();
        }
    }

    @Override
    public void showNews(NewsBean newsBean) {
        imagesUrl.clear();
        title.clear();
        List<NewsBean.TopStoriesBean> topStoriesBean = newsBean.getTop_stories();
        for (int i = 0; i < topStoriesBean.size(); i++) {
            imagesUrl.add(topStoriesBean.get(i).getImage());
            title.add(topStoriesBean.get(i).getTitle());
        }
        List<MultiItemEntity> data = new ArrayList<>();
        boolean isShow = newsBean.getStories() != null || newsBean.getStories().size() != 0;
        data.add(new ZhiHuDailyHeader(isShow));
        data.addAll(newsBean.getStories());
        adapter.addData(data);
        banner.update(imagesUrl, title);
        banner.setOnBannerListener(position -> {
            Intent intent=new Intent(mContext,ZhiHuDetailActivity.class);
            intent.putExtra("type","hot");
            intent.putExtra("id",newsBean.getTop_stories().get(position).getId()+"");
            startActivity(intent);
        });
    }

    @Override
    public void showThemes(ThemesBean themesBean) {
        List<MultiItemEntity> data = new ArrayList<>();
        boolean isShow = themesBean.getOthers() != null || themesBean.getOthers().size() != 0;
        data.add( new ZhiHuThemesHeader(isShow));
        data.addAll(themesBean.getOthers());
        adapter.addData(data);
    }

    @Override
    public void showHotNews(HotNewsBean hotNewsBean) {
        List<MultiItemEntity> data = new ArrayList<>();
        boolean isShow = hotNewsBean.getRecent() != null || hotNewsBean.getRecent().size() != 0;
        data.add( new ZhiHuHotNewsHeader(isShow));
        data.addAll(hotNewsBean.getRecent());
        adapter.addData(data);
    }

    @Override
    public void showSection(SectionBean sectionBean) {
        List<MultiItemEntity> data = new ArrayList<>();
        boolean isShow = sectionBean.getData() != null || sectionBean.getData().size() != 0;
        data.add(new ZhiHuSectionHeader(isShow));
        data.addAll(sectionBean.getData());
        adapter.addData(data);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        presenter.getNewsData();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }
}
