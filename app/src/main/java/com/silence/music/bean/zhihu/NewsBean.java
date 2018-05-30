package com.silence.music.bean.zhihu;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.silence.music.adapter.ZhiHuAdapter;

import java.util.List;

/**
 * @autor :Silence
 * @date :2018/5/23
 **/
public class NewsBean {

    /**
     * date : 日期
     * stories : 当日新闻
     * title : 新闻标题
     * images : 图像地址（官方 API 使用数组形式。目前暂未有使用多张图片的情形出现，曾见无 images 属性的情况，请在使用中注意 ）
     * ga_prefix : 供 Google Analytics 使用
     * type : 作用未知
     * id : url 与 share_url 中最后的数字（应为内容的 id）
     * multipic : 消息是否包含多张图片（仅出现在包含多图的新闻中）
     * top_stories : 界面顶部 ViewPager 滚动显示的显示内容（子项格式同上）（请注意区分此处的 image 属性与 stories 中的 images 属性）
     */
    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean implements MultiItemEntity {

        private String title;
        private String ga_prefix;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int getItemType() {
            return ZhiHuAdapter.DAILY;
        }
    }

    public static class TopStoriesBean {

        private String title;
        private String image;
        private String ga_prefix;
        private int type;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
