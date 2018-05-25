package com.silence.music.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.angel.music.R;
import com.silence.music.base.BaseFragment;
import com.silence.music.base.BasePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

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
        banner = mView.findViewById(R.id.banner);
        recycler = mView.findViewById(R.id.recycler);
        for (int i = 0; i < 5; i++) {
            images.add(R.mipmap.magic_bg);
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        imageView.setImageResource((Integer) path);
                    }
                }).setImages(images).start();
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
