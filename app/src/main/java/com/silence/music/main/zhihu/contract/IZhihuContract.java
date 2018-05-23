package com.silence.music.main.zhihu.contract;

import com.silence.music.base.IView;
import com.silence.music.bean.NewsBean;
import com.silence.music.bean.NewsListBean;

/**
 * @autor :Silence
 * @date :2018/5/23
 **/
public class IZhihuContract {

    public interface IZhihuView extends IView {
        void showNews(NewsBean bean);

    }

    public interface IZhihuPresenter {
        void getNewsData();

    }
}
