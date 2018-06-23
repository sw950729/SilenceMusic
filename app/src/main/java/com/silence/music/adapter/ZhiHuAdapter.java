package com.silence.music.adapter;

import android.content.Intent;

import com.angel.music.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.moudle.utils.GlideUtils;
import com.silence.music.bean.zhihu.HotNewsBean;
import com.silence.music.bean.zhihu.NewsBean;
import com.silence.music.bean.zhihu.SectionBean;
import com.silence.music.bean.zhihu.ThemesBean;
import com.silence.music.bean.zhihu.ZhiHuDailyHeader;
import com.silence.music.bean.zhihu.ZhiHuHotNewsHeader;
import com.silence.music.bean.zhihu.ZhiHuSectionHeader;
import com.silence.music.bean.zhihu.ZhiHuThemesHeader;
import com.silence.music.main.zhihu.ZhiHuDetailActivity;
import com.silence.music.main.zhihu.ZhihuThemeActivity;


/**
 * @author :Silence
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
                setImages(helper, item);
                break;
            case THEME_TITLE:
                setTitle(helper, item);
                break;
            case THEME:
                setImages(helper, item);
                break;
            case HOTNEWS_TITLE:
                setTitle(helper, item);
                break;
            case HOT_NEWS:
                setImages(helper, item);
                break;
            case SECTION_TITLE:
                setTitle(helper, item);
                break;
            case SECTION:
                setImages(helper, item);
                break;
            default:
        }
    }

    private void setImages(BaseViewHolder helper, MultiItemEntity item) {
        if (item instanceof SectionBean.DataBean) {
            SectionBean.DataBean dataBean = (SectionBean.DataBean) item;
            helper.setText(R.id.tv_title, dataBean.getName());
            GlideUtils.LoadGlideBitmap(mContext, dataBean.getThumbnail(), helper.getView(R.id.iv_introduction));
            helper.getView(R.id.cardview).setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ZhihuThemeActivity.class);
                intent.putExtra("type", "section");
                intent.putExtra("id", dataBean.getId()+"");
                mContext.startActivity(intent);
            });
        } else if (item instanceof HotNewsBean.RecentBean) {
            HotNewsBean.RecentBean recentBean = (HotNewsBean.RecentBean) item;
            helper.setText(R.id.tv_title, recentBean.getTitle());
            GlideUtils.LoadGlideBitmap(mContext, recentBean.getThumbnail(), helper.getView(R.id.iv_introduction));
            helper.getView(R.id.cardview).setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ZhiHuDetailActivity.class);
                intent.putExtra("id", recentBean.getNews_id()+"");
                mContext.startActivity(intent);
            });
        } else if (item instanceof ThemesBean.OthersBean) {
            ThemesBean.OthersBean othersBean = (ThemesBean.OthersBean) item;
            helper.setText(R.id.tv_title, othersBean.getName());
            GlideUtils.LoadGlideBitmap(mContext, othersBean.getThumbnail(), helper.getView(R.id.iv_introduction));
            helper.getView(R.id.cardview).setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ZhihuThemeActivity.class);
                intent.putExtra("type", "themes");
                intent.putExtra("id", othersBean.getId()+"");
                mContext.startActivity(intent);
            });
        } else if (item instanceof NewsBean.StoriesBean) {
            NewsBean.StoriesBean storiesBean = (NewsBean.StoriesBean) item;
            helper.setText(R.id.tv_title, storiesBean.getTitle());
            GlideUtils.LoadGlideBitmap(mContext, storiesBean.getImages().get(0), helper.getView(R.id.iv_introduction));
            helper.getView(R.id.cardview).setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ZhiHuDetailActivity.class);
                intent.putExtra("id", storiesBean.getId()+"");
                mContext.startActivity(intent);
            });
        }
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
