package com.silence.music.main.zhihu.contract;

import com.silence.music.base.IView;
import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;

/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class IZhihuContract {

    public interface IZhihuView extends IView {
        void showData();

        void showNews(NewsBean newsBean);

        void showThemes(ThemesBean themesBean);

        void showHotNews(HotNewsBean hotNewsBean);

        void showSection(SectionBean sectionBean);
    }

    public interface IZhihuPresenter {
        void getNewsData();
    }
}
