package com.silence.music.main.zhihu.presenter;


import com.moudle.base.BasePresenter;
import com.silence.music.main.zhihu.contract.IZhihuDetailContract;
import com.silence.music.network.RxNetWork;


/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class ZhihuDetailPresenter extends BasePresenter<IZhihuDetailContract.IZhihuDetailView> implements IZhihuDetailContract.IZhihuDetailPresenter {

    private IZhihuDetailContract.IZhihuDetailView view;

    public ZhihuDetailPresenter(IZhihuDetailContract.IZhihuDetailView view) {
        this.view = view;
    }

    @Override
    public void getNewsDetail(String id) {
        RxNetWork.getZhihuHttp()
                .getNewsDetail(id)
                .compose(bindLife())
                .subscribe(newsListBean -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    view.showNewsDetail(newsListBean);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    view.showNetError();
                    view.showToast("服务器连接异常！");
                });
    }
}