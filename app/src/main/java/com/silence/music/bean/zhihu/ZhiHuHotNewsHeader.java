package com.silence.music.bean.zhihu;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.adapter.ZhiHuAdapter;

/**
 * @autor :Silence
 * @date :2018/5/24
 **/
public class ZhiHuHotNewsHeader implements MultiItemEntity {
    private boolean isShow;
    private int type;

    public boolean isShow() {
        return isShow;
    }

    public ZhiHuHotNewsHeader(boolean isShow, int type) {
        this.isShow = isShow;
        this.type = type;
    }

    @Override
    public int getItemType() {
//        return ZhiHuAdapter.HOTNEWS_TITLE;
        return type;
    }
}
