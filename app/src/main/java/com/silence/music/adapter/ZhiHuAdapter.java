package com.silence.music.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.angel.music.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;
import com.silence.music.bean.zhihu.ZhiHuDailyHeader;
import com.silence.music.bean.zhihu.ZhiHuHotNewsHeader;
import com.silence.music.bean.zhihu.ZhiHuSectionHeader;
import com.silence.music.bean.zhihu.ZhiHuThemesHeader;
import com.silence.music.utils.GlideUtils;

import java.util.List;

/**
 * @autor :Silence
 * @date :2018/5/24
 **/
public class ZhiHuAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int DAILY_TITLE = 1;
    public static final int DAILY = DAILY_TITLE + 1;
    public static final int THEME_TITLE = DAILY_TITLE + 2;
    public static final int THEME = DAILY_TITLE + 3;
    public static final int HOTNEWS_TITLE = DAILY_TITLE + 4;
    public static final int HOT_NEWS = DAILY_TITLE + 5;
    public static final int SECTION_TITLE = DAILY_TITLE + 6;
    public static final int SECTION = DAILY_TITLE + 7;

    public ZhiHuAdapter() {
        super(null);
        addItemType(DAILY_TITLE, R.layout.item_zhihu_title);
        addItemType(DAILY, R.layout.item_zhihu_list);
        addItemType(THEME_TITLE, R.layout.item_zhihu_title);
        addItemType(THEME, R.layout.item_zhihu_list);
        addItemType(HOTNEWS_TITLE, R.layout.item_zhihu_title);
        addItemType(HOT_NEWS, R.layout.item_zhihu_list);
        addItemType(SECTION_TITLE, R.layout.item_zhihu_title);
        addItemType(SECTION, R.layout.item_zhihu_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case DAILY_TITLE:
                setTitle(helper, item);
                break;
            case DAILY:
                setDaily(helper, (NewsBean.StoriesBean) item);
                break;
            case THEME_TITLE:
                setTitle(helper, item);
                break;
            case THEME:
                setTheme(helper, (ThemesBean.OthersBean) item);
                break;
            case HOTNEWS_TITLE:
                setTitle(helper, item);
                break;
            case HOT_NEWS:
                setHotNews(helper, (HotNewsBean.RecentBean) item);
                break;
            case SECTION_TITLE:
                setTitle(helper, item);
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
        if (item instanceof ZhiHuDailyHeader) {
            ZhiHuDailyHeader dailyHeader = (ZhiHuDailyHeader) item;
            if (dailyHeader.isShow()) {
                helper.setText(R.id.tv_title, R.string.daily);
            }
        } else if (item instanceof ZhiHuThemesHeader) {
            ZhiHuThemesHeader dailyHeader = (ZhiHuThemesHeader) item;
            if (dailyHeader.isShow()) {
                helper.setText(R.id.tv_title, R.string.theme);
            }
        } else if (item instanceof ZhiHuHotNewsHeader) {
            ZhiHuHotNewsHeader dailyHeader = (ZhiHuHotNewsHeader) item;
            if (dailyHeader.isShow()) {
                helper.setText(R.id.tv_title, R.string.hot);
            }
        } else if (item instanceof ZhiHuSectionHeader) {
            ZhiHuSectionHeader dailyHeader = (ZhiHuSectionHeader) item;
            if (dailyHeader.isShow()) {
                helper.setText(R.id.tv_title, R.string.sections);
            }
        }
    }
}
