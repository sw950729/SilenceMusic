package com.silence.music.bean.zhihu;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.adapter.ZhiHuAdapter;

/**
 * @author :Silence
 * @date :2018/5/24
 **/
public class ZhiHuThemesHeader implements MultiItemEntity {
    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public ZhiHuThemesHeader(boolean isShow) {
        this.isShow = isShow;
    }

    @Override
    public int getItemType() {
        return ZhiHuAdapter.THEME_TITLE;
    }
}
