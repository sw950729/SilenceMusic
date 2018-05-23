package com.silence.music.main.zhihu;

import com.silence.music.base.BasePresenter;
import com.silence.music.network.RxNetWork;
import com.silence.music.utils.Utils;

/**
 * @autor :Silence
 * @date :2018/5/23
 **/
public class ZhihuPresenter extends BasePresenter<IZhihuContract.IZhihuView> implements IZhihuContract.IZhihuPresenter {

    private IZhihuContract.IZhihuView view;

    public ZhihuPresenter(IZhihuContract.IZhihuView view) {
        this.view = view;
    }

    @Override
    public void getNewsData() {
        RxNetWork.getObserveHttp().getNews()
                .compose(bindLife())
                .subscribe(bean -> view.showNews(bean), throwable -> Utils.showToast("服务器连接异常！"));
    }
}
