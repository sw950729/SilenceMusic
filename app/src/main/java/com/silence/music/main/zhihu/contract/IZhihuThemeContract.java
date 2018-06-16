package com.silence.music.main.zhihu.contract;

import com.moudle.base.IView;
import com.silence.music.bean.zhihu.SectionDetailBean;
import com.silence.music.bean.zhihu.ThemeDetailBean;

public class IZhihuThemeContract {

    public  interface IZhihuThemeView extends IView{

        void showThemeDetail(ThemeDetailBean themeDetailBean);

        void showSectionDetail(SectionDetailBean sectionDetailBean);
    }

    public  interface IZhihuThemePresenter {

        void getThemeDetail(String id);

        void getSectionDetail(String id);
    }
}
