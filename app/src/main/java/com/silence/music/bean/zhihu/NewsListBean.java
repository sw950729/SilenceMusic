package com.silence.music.bean.zhihu;

import java.util.List;

/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class NewsListBean {
    /**
     * body : HTML 格式的新闻
     * image-source : 图片的内容提供方。为了避免被起诉非法使用图片，在显示图片时最好附上其版权信息。
     * title : 新闻标题
     * image : 获得的图片同 最新消息 获得的图片分辨率不同。这里获得的是在文章浏览界面中使用的大图。
     * share_url : 供在线查看内容与分享至 SNS 用的 URL
     * js : 供手机端的 WebView(UIWebView) 使用
     * recommenders : 这篇文章的推荐者
     * ga_prefix : 供 Google Analytics 使用
     * section : 栏目的信息
     * thumbnail : 栏目的缩略图
     * id : 该栏目的 id
     * name : 该栏目的名称
     * type : 新闻的类型
     * id : 新闻的 id
     * css : 供手机端的 WebView(UIWebView) 使用
     * 可知，知乎日报的文章浏览界面利用 WebView(UIWebView) 实现
     */
    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

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

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
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

    public List<String>  getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
