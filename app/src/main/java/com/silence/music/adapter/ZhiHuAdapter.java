package com.silence.music.adapter;

import android.content.Context;

import com.angel.music.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.bean.ZhiHuDailyHeader;

/**
 * @autor :Silence
 * @date :2018/5/24
 **/
public class ZhiHuAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TITLE = 0;
    public static final int DAILY = TITLE + 1;

    public ZhiHuAdapter(Context context) {
        super(null);
        addItemType(TITLE, R.layout.item_zhihu_title);
        addItemType(DAILY, R.layout.item_zhihu_title);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case TITLE:
                setTitle(helper, item);
                break;
            case DAILY:
                setDaily(helper, item);
                break;
            default:
        }
    }

    private void setDaily(BaseViewHolder helper, MultiItemEntity item) {
        helper.setVisible(R.id.layout_parent, false);
    }

    private void setTitle(BaseViewHolder helper, MultiItemEntity item) {
        if (item instanceof ZhiHuDailyHeader) {
            ZhiHuDailyHeader dailyHeader = (ZhiHuDailyHeader) item;
            if (dailyHeader.isShow()) {
                helper.setText(R.id.tv_title, R.string.daily);
            }
        }
    }
}
