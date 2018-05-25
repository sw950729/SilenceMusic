package com.silence.music.main.zhihu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angel.music.R;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.music.adapter.ZhiHuAdapter;
import com.silence.music.base.BaseFragment;
import com.silence.music.bean.NewsBean;
import com.silence.music.bean.ZhiHuDailyHeader;
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
    private RecyclerView recycler;
    private ZhiHuAdapter adapter;
    private List<String> imagesUrl = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    @Override
    public void initView() {
        initLoadingLayout(R.id.refreshLayout);
        refreshLayout = (SmartRefreshLayout) mView.findViewById(R.id.refreshLayout);
        recycler = (RecyclerView) mView.findViewById(R.id.recycler);
        View headerview = LayoutInflater.from(mContext).inflate(R.layout.banner, (ViewGroup) refreshLayout.getParent(), false);
        banner = (Banner) headerview.findViewById(R.id.banner);
        adapter = new ZhiHuAdapter(mContext);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.addHeaderView(headerview);
        recycler.setAdapter(adapter);
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
        List<MultiItemEntity> data = new ArrayList<>();
        boolean isShow = bean.getStories() != null || bean.getStories().size() != 0;
        data.add(new ZhiHuDailyHeader(isShow));
        data.addAll(bean.getStories());
        adapter.setNewData(data);
        banner.setImages(imagesUrl);
        banner.setBannerTitleList(title);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        presenter.getNewsData();
    }
}
