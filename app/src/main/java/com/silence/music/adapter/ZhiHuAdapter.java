package com.silence.music.adapter;

import android.util.Log;

import com.angel.music.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;
import com.silence.music.bean.zhihu.ZhiHuDailyHeader;
import com.silence.music.utils.GlideUtils;

/**
 * @autor :Silence
 * @date :2018/5/24
 **/
public class ZhiHuAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TITLE = 0;
    public static final int DAILY = TITLE + 1;
    public static final int THEME = DAILY + 1;
    public static final int HOT_NEWS = THEME + 1;
    public static final int SECTION = HOT_NEWS + 1;

    public ZhiHuAdapter() {
        super(null);
        addItemType(TITLE, R.layout.item_zhihu_title);
        addItemType(DAILY, R.layout.item_zhihu_list);
        addItemType(THEME, R.layout.item_zhihu_list);
        addItemType(HOT_NEWS, R.layout.item_zhihu_list);
        addItemType(SECTION, R.layout.item_zhihu_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case TITLE:
                setTitle(helper, item);
                break;
            case DAILY:
                setDaily(helper, (NewsBean.StoriesBean) item);
                break;
            case THEME:
                setTheme(helper, (ThemesBean.OthersBean) item);
                break;
            case HOT_NEWS:
                setHotNews(helper, (HotNewsBean.RecentBean) item);
                break;
            case SECTION:
                setSection(helper, (SectionBean.DataBean) item);
                break;
            default:
        }
    }

    private void setSection(BaseViewHolder helper, SectionBean.DataBean item) {
        helper.setText(R.id.tv_title, item.getName());
        GlideUtils.LoadGlideBitmap(mContext, item.getThumbnail(), helper.getView(R.id.iv_introduction));
    }

    private void setHotNews(BaseViewHolder helper, HotNewsBean.RecentBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        GlideUtils.LoadGlideBitmap(mContext, item.getThumbnail(), helper.getView(R.id.iv_introduction));

    }

    private void setTheme(BaseViewHolder helper, ThemesBean.OthersBean item) {
        helper.setText(R.id.tv_title, item.getName());
        GlideUtils.LoadGlideBitmap(mContext, item.getThumbnail(), helper.getView(R.id.iv_introduction));
    }

    private void setDaily(BaseViewHolder helper, NewsBean.StoriesBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        GlideUtils.LoadGlideBitmap(mContext, item.getImages().get(0), helper.getView(R.id.iv_introduction));
    }

    private void setTitle(BaseViewHolder helper, MultiItemEntity item) {
        Log.i(TAG, "setTitle: 11111111");
        if (item instanceof ZhiHuDailyHeader) {
            ZhiHuDailyHeader dailyHeader = (ZhiHuDailyHeader) item;
            if (dailyHeader.isShow()) {
                Log.i(TAG, "setTitle: 22222");
                helper.setText(R.id.tv_title, R.string.daily);
            }
        }
    }
}
