package com.silence.music.main.zhihu.contract;

import com.moudle.base.IView;
import com.silence.music.bean.zhihu.SectionDetailBean;
import com.silence.music.bean.zhihu.ThemeDetailBean;
import com.silence.music.bean.zhihu.NewsListBean;

/**
 * @author :Silence
 * @date :2018/6/1
 **/
public class IZhihuDetailContract {

    public interface IZhihuDetailView extends IView {
        void showNewsDetail(NewsListBean newsListBean);

        void showThemeDetail(ThemeDetailBean themeDetailBean);

        void showSectionDetail(SectionDetailBean sectionDetailBean);

    }

    public interface IZhihuDetailPresenter {
        void getNewsDetail(String id);

        void getThemeDetail(String id);

        void getSectionDetail(String id);
    }
}
