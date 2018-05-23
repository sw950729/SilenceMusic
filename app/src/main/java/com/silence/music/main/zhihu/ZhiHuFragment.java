package com.silence.music.main.zhihu;

import com.angel.music.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.music.base.BaseFragment;
import com.silence.music.bean.NewsBean;
import com.silence.music.main.zhihu.contract.IZhihuContract;
import com.silence.music.main.zhihu.presenter.ZhihuPresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor :Silence
 * @date :2018/5/23
 **/
public class ZhiHuFragment extends BaseFragment<ZhihuPresenter> implements IZhihuContract.IZhihuView, OnRefreshListener {

    private Banner banner;
    private SmartRefreshLayout refreshLayout;
    private List<String> imagesUrl = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    @Override
    public void initView() {
        initLoadingLayout(R.id.ll_content);
        banner = (Banner) mView.findViewById(R.id.banner);
        refreshLayout = (SmartRefreshLayout) mView.findViewById(R.id.refreshLayout);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
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
    public void showNews(NewsBean bean) {
        refreshLayout.finishRefresh();
        if (null != mLoadingViewHelper) {
            mLoadingViewHelper.showDataView();
        }
        imagesUrl.clear();
        title.clear();
        List<NewsBean.TopStoriesBean> topStoriesBean = bean.getTop_stories();
        for (int i = 0; i < topStoriesBean.size(); i++) {
            imagesUrl.add(topStoriesBean.get(i).getImage());
            title.add(topStoriesBean.get(i).getTitle());
        }
        banner.setImages(imagesUrl);
        banner.setBannerTitleList(title);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        presenter.getNewsData();
    }
}
