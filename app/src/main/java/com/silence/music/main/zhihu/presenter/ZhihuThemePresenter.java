package com.silence.music.main.zhihu.presenter;

import com.moudle.base.BasePresenter;
import com.silence.music.main.zhihu.contract.IZhihuThemeContract;
import com.silence.music.network.RxNetWork;

public class ZhihuThemePresenter extends BasePresenter<IZhihuThemeContract.IZhihuThemeView> implements IZhihuThemeContract.IZhihuThemePresenter {

    private IZhihuThemeContract.IZhihuThemeView view;

    public ZhihuThemePresenter(IZhihuThemeContract.IZhihuThemeView view) {
        this.view = view;
    }


    @Override
    public void getThemeDetail(String id) {
        RxNetWork.getZhihuHttp()
                .getThemeDetail(id)
                .compose(bindLife())
                .subscribe(themeDetailBean -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    view.showThemeDetail(themeDetailBean);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    view.showNetError();
                    view.showToast("服务器连接异常！");
                });
    }

    @Override
    public void getSectionDetail(String id) {
        RxNetWork.getZhihuHttp()
                .getSectionDetail(id)
                .compose(bindLife())
                .subscribe(sectionDetailBean -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    view.showSectionDetail(sectionDetailBean);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    view.showNetError();
                    view.showToast("服务器连接异常！");
                });
    }

}
