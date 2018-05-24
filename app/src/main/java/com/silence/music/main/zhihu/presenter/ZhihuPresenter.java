package com.silence.music.main.zhihu.presenter;

import com.silence.music.base.BasePresenter;
import com.silence.music.main.zhihu.contract.IZhihuContract;
import com.silence.music.network.RxNetWork;

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
        RxNetWork.getZhihuHttp().getNews()
                .compose(bindLife())
                .subscribe(bean -> {
                    if (isViewAttached()) {
                        return;
                    }
                    view.showNews(bean);
                }, throwable -> {
                    if (isViewAttached()) {
                        return;
                    }
                    view.showToast("服务器连接异常！");
                });
    }
}
