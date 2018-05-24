package com.silence.music.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.angel.music.R;

/**
 * @autor :Silence
 * @date :2018/5/23
 **/
public class RecommendLayout extends FrameLayout {

    private TextView tv_content;
    private ImageView iv_content;
    private String custom_text;
    private int src;

    public RecommendLayout(@NonNull Context context) {
        super(context);
    }

    public RecommendLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initial(context, attrs);
    }

    @SuppressLint("SetTextI18n")
    private void initial(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.base_recommend, this);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        iv_content = (ImageView) view.findViewById(R.id.iv_content);
        @SuppressLint("Recycle") TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.RecommendLayout);
        custom_text = array.getString(R.styleable.RecommendLayout_text);
        src = array.getResourceId(R.styleable.RecommendLayout_src, 0);
        tv_content.setText(custom_text + "");
        iv_content.setImageResource(src);
    }
}
