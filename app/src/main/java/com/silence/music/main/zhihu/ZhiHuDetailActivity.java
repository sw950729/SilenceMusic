package com.silence.music.main.zhihu;


import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.angel.music.R;
import com.silence.music.base.BaseActivity;
import com.silence.music.bean.zhihu.NewsListBean;
import com.silence.music.main.zhihu.contract.IZhihuDetailContract;
import com.silence.music.main.zhihu.presenter.ZhihuDetailPresenter;
import com.silence.music.utils.GlideUtils;
import com.silence.music.utils.HtmlUtils;
import com.silence.music.view.SimpleToolbar;

/**
 * @author :Silence
 * @date :2018/5/23
 **/
public class ZhiHuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements IZhihuDetailContract.IZhihuDetailView, View.OnClickListener {

    private String type;
    private String id;
    private WebView webView;
    private SimpleToolbar toolbar;
    private ImageView iv_detail_img;
    private TextView tv_title;
    private LinearLayout llback;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        if (getIntent() != null) {
            type = getIntent().getStringExtra("type");
            id = getIntent().getStringExtra("id");
        }
        webView = findViewById(R.id.webview);
        toolbar = findViewById(R.id.toolbar);
        iv_detail_img = findViewById(R.id.iv_detail_img);
        tv_title = findViewById(R.id.iv_title);
        llback = findViewById(R.id.llBack);
        WebSettings settings = webView.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        llback.setOnClickListener(this);
    }

    @Override
    public void httpData() {
        if ("daily".equals(type) || "hot".equals(type)) {
            presenter.getNewsDetail(id);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    public ZhihuDetailPresenter bindPresenter() {
        return new ZhihuDetailPresenter(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showNewsDetail(NewsListBean newsListBean) {
        GlideUtils.LoadGlideBitmap(this, newsListBean.getImage(), iv_detail_img);
        toolbar.setTitle(newsListBean.getTitle() + "");
        tv_title.setText(newsListBean.getImage_source() + "");
        String htmlData = HtmlUtils.createHtmlData(newsListBean.getBody(), newsListBean.getCss(), newsListBean.getJs());
        webView.loadData(htmlData, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBack:
                onBackPressed();
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
