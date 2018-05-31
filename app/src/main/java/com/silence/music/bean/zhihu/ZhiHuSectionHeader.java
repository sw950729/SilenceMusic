package com.silence.music.bean.zhihu;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.adapter.ZhiHuAdapter;

/**
 * @autor :Silence
 * @date :2018/5/24
 **/
public class ZhiHuSectionHeader implements MultiItemEntity {
    private boolean isShow;
    private int type;
    public boolean isShow() {
        return isShow;
    }

    public ZhiHuSectionHeader(boolean isShow,int type) {
        this.isShow = isShow;
        this.type =type;
    }

    @Override
    public int getItemType() {
//        return ZhiHuAdapter.SECTION_TITLE;
        return type;
    }
}
