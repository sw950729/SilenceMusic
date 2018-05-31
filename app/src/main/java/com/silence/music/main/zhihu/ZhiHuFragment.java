package com.silence.music.main.zhihu;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.angel.music.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.music.adapter.ZhiHuAdapter;
import com.silence.music.base.BaseFragment;
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
 * @autor :Silence
 * @date :2018/5/23
 **/
public class ZhiHuFragment extends BaseFragment<ZhihuPresenter> implements IZhihuContract.IZhihuView, OnRefreshListener {

    private Banner banner;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recycler;
    private ZhiHuAdapter adapter;
    private List<String> imagesUrl = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private List<MultiItemEntity> dataThemes;
    private List<MultiItemEntity> dataNews;
    private List<MultiItemEntity> dataHotNews;
    private List<MultiItemEntity> dataSection;

    @Override
    public void initView() {
        initLoadingLayout(R.id.refreshLayout);
        refreshLayout = (SmartRefreshLayout) mView.findViewById(R.id.refreshLayout);
        recycler = (RecyclerView) mView.findViewById(R.id.recycler);
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.banner_layout, (ViewGroup) refreshLayout.getParent(), false);
        banner = (Banner) headerView.findViewById(R.id.banner);
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
        dataNews = new ArrayList<>();
        boolean isShow = newsBean.getStories() != null || newsBean.getStories().size() != 0;
        ZhiHuDailyHeader zhiHuDailyHeader = new ZhiHuDailyHeader(isShow, ZhiHuAdapter.DAILY_TITLE);
        dataNews.add(zhiHuDailyHeader);
        dataNews.addAll(newsBean.getStories());
        adapter.addData(dataNews);
        banner.update(imagesUrl, title);
    }

    @Override
    public void showThemes(ThemesBean themesBean) {
        dataThemes = new ArrayList<>();
        boolean isShow = themesBean.getOthers() != null || themesBean.getOthers().size() != 0;
        ZhiHuThemesHeader zhiHuThemesHeader = new ZhiHuThemesHeader(isShow, ZhiHuAdapter.THEME_TITLE);
        dataThemes.add(zhiHuThemesHeader);
        dataThemes.addAll(themesBean.getOthers());
        adapter.addData(dataThemes);
    }

    @Override
    public void showHotNews(HotNewsBean hotNewsBean) {
        dataHotNews = new ArrayList<>();
        boolean isShow = hotNewsBean.getRecent() != null || hotNewsBean.getRecent().size() != 0;
        ZhiHuHotNewsHeader zhiHuHotNewsHeader = new ZhiHuHotNewsHeader(isShow, ZhiHuAdapter.HOTNEWS_TITLE);
        dataHotNews.add(zhiHuHotNewsHeader);
        dataHotNews.addAll(hotNewsBean.getRecent());
        adapter.addData(dataHotNews);
    }

    @Override
    public void showSection(SectionBean sectionBean) {
        dataSection = new ArrayList<>();
        boolean isShow = sectionBean.getData() != null || sectionBean.getData().size() != 0;
        ZhiHuSectionHeader zhiHuSectionHeader = new ZhiHuSectionHeader(isShow, ZhiHuAdapter.SECTION_TITLE);
        dataSection.add(zhiHuSectionHeader);
        dataSection.addAll(sectionBean.getData());
        adapter.addData(dataSection);
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
