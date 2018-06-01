package com.silence.music.main.zhihu.presenter;

import com.silence.music.base.BasePresenter;
import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;
import com.silence.music.main.zhihu.contract.IZhihuContract;
import com.silence.music.main.zhihu.contract.IZhihuDetailContract;
import com.silence.music.network.RxNetWork;

import rx.Observable;

/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class ZhihuDetailPresenter extends BasePresenter<IZhihuDetailContract.IZhihuDetailView> implements IZhihuDetailContract.IZhihuDetailPresenter {

    private IZhihuDetailContract.IZhihuDetailView view;

    public ZhihuDetailPresenter(IZhihuDetailContract.IZhihuDetailView view) {
        this.view = view;
    }

}
