package com.silence.music.main.zhihu;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.angel.music.R;
import com.bumptech.glide.Glide;
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

    @Override
    public void initView() {
        initLoadingLayout(R.id.refreshLayout);
        refreshLayout = mView.findViewById(R.id.refreshLayout);
        recycler = mView.findViewById(R.id.recycler);
        banner = mView.findViewById(R.id.banner);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context)
                                .load(path)
                                .placeholder(R.mipmap.magic_bg)
                                .error(R.mipmap.magic_bg)
                                .into(imageView);
                    }
                })
                .isAutoPlay(true);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ZhiHuAdapter(mContext);
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
        banner.setImages(imagesUrl)
                .setBannerTitles(title)
                .start();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        presenter.getNewsData();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null)
            banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null)
            banner.stopAutoPlay();
    }
}
