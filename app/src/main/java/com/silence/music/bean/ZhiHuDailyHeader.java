package com.silence.music.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.adapter.ZhiHuAdapter;

/**
 * @autor :Silence
 * @date :2018/5/24
 **/
public class ZhiHuDailyHeader implements MultiItemEntity {
    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public ZhiHuDailyHeader(boolean isShow) {
        this.isShow = isShow;
    }

    @Override
    public int getItemType() {
        return ZhiHuAdapter.TITLE;
    }
}
