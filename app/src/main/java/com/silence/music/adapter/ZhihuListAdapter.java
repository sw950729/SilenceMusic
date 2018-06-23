package com.silence.music.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.angel.music.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.moudle.utils.GlideUtils;
import com.moudle.utils.Utils;
import com.silence.music.bean.zhihu.SectionListBean;
import com.silence.music.bean.zhihu.ThemeListBean;
import com.silence.music.main.zhihu.ZhiHuDetailActivity;

import java.util.List;

public class ZhihuListAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public ZhihuListAdapter(int layoutResId, @Nullable List<Object> data) {
        super(R.layout.item_zhihu_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        if (item instanceof ThemeListBean.StoriesBean) {
            ThemeListBean.StoriesBean storiesBean = (ThemeListBean.StoriesBean) item;
            helper.setText(R.id.tv_title, storiesBean.getTitle());
            if (Utils.isListNotEmpty(storiesBean.getImages())) {
                GlideUtils.LoadGlideBitmap(mContext, storiesBean.getImages().get(0), helper.getView(R.id.iv_introduction));
            }
            helper.getView(R.id.cardview).setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ZhiHuDetailActivity.class);
                intent.putExtra("id", storiesBean.getId() + "");
                mContext.startActivity(intent);
            });
        } else if (item instanceof SectionListBean.StoriesBean) {
            SectionListBean.StoriesBean storiesBean = (SectionListBean.StoriesBean) item;
            helper.setText(R.id.tv_title, storiesBean.getTitle());
            if (Utils.isListNotEmpty(storiesBean.getImages())) {
                GlideUtils.LoadGlideBitmap(mContext, storiesBean.getImages().get(0), helper.getView(R.id.iv_introduction));
            }
            helper.getView(R.id.cardview).setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ZhiHuDetailActivity.class);
                intent.putExtra("id", storiesBean.getId() + "");
                mContext.startActivity(intent);
            });
        }
    }
}
