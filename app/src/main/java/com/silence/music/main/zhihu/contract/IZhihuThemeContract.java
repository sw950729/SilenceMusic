package com.silence.music.main.zhihu.contract;

import com.moudle.base.IView;
import com.silence.music.bean.zhihu.SectionListBean;
import com.silence.music.bean.zhihu.ThemeListBean;

public class IZhihuThemeContract {

    public  interface IZhihuThemeView extends IView{

        void showThemeDetail(ThemeListBean themeDetailBean);

        void showSectionDetail(SectionListBean sectionDetailBean);
    }

    public  interface IZhihuThemePresenter {

        void getThemeDetail(String id);

        void getSectionDetail(String id);
    }
}
