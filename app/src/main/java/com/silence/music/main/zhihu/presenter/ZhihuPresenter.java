package com.silence.music.main.zhihu.presenter;

import com.silence.music.base.BasePresenter;
import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;
import com.silence.music.main.zhihu.contract.IZhihuContract;
import com.silence.music.network.RxNetWork;

import rx.Observable;

/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class ZhihuPresenter extends BasePresenter<IZhihuContract.IZhihuView> implements IZhihuContract.IZhihuPresenter {

    private IZhihuContract.IZhihuView view;

    public ZhihuPresenter(IZhihuContract.IZhihuView view) {
        this.view = view;
    }

    private int count = 0;

    @Override
    public void getNewsData() {
        Observable<NewsBean> newsBeanObservable = RxNetWork.getZhihuHttp().getNews();
        Observable<ThemesBean> themesBeanObservable = RxNetWork.getZhihuHttp().getThemes();
        Observable<HotNewsBean> hotNewsBeanObservable = RxNetWork.getZhihuHttp().getHotNews();
        Observable<SectionBean> sectionBeanObservable = RxNetWork.getZhihuHttp().getSections();
        Observable.concatDelayError(newsBeanObservable, themesBeanObservable, hotNewsBeanObservable, sectionBeanObservable)
                .compose(bindLife()).subscribe(object -> {
            if (!isViewAttached()) {
                return;
            }
            count++;

            if (object instanceof NewsBean) {
                view.showNews((NewsBean) object);
            } else if (object instanceof ThemesBean) {
                view.showThemes((ThemesBean) object);
            } else if (object instanceof HotNewsBean) {
                view.showHotNews((HotNewsBean) object);
            } else if (object instanceof SectionBean) {
                view.showSection((SectionBean) object);
            }
            if (count == 4) {
                view.showData();
                count=0;
            }
        }, throwable -> {
            if (!isViewAttached()) {
                return;
            }
            view.showNetError();
            view.showToast("服务器连接异常！");
        });
    }

}
