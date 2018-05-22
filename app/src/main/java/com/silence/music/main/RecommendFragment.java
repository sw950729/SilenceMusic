package com.silence.music.main;

import android.support.v7.widget.RecyclerView;

import com.angel.music.R;
import com.silence.music.base.BaseFragment;
import com.silence.music.base.BasePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor :Silence
 * @date :2018/5/17
 **/
public class RecommendFragment extends BaseFragment {

    private Banner banner;
    private RecyclerView recycler;
    private List<Integer> images = new ArrayList<>();

    @Override
    public void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
        recycler = (RecyclerView) mView.findViewById(R.id.recycler);
        for (int i = 0; i < 5; i++) {
            images.add(R.mipmap.magic_bg);
        }
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images);
    }

    @Override
    public void httpData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommed;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
