package com.silence.music.main.zhihu.contract;

import com.silence.music.base.IView;
import com.silence.music.bean.zhihu.NewsListBean;

/**
 * @author :Silence
 * @date :2018/6/1
 **/
public class IZhihuDetailContract {

    public interface IZhihuDetailView extends IView {
        void showNewsDetail(NewsListBean newsListBean);
    }

    public interface IZhihuDetailPresenter {
        void getNewsDetail(String id);
    }
}
