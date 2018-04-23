package com.silence.music.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.angel.music.R;

/**
 * @author Silence
 * @date 2018/4/23
 */
public class SWIconView extends View {

    private Context context;
    private int background_icon;
    private Drawable drawable;

    public SWIconView(Context context) {
        super(context);
        this.context = context;
    }

    public SWIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inital(attrs);
    }

    private void inital(AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable") TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.View);
        background_icon = typedArray.getResourceId(R.styleable.View_background_icon, 0);
        typedArray.recycle();
    }

    public void setBackground_icon(int background_icon) {
        this.background_icon = background_icon;
        iconDestroy();
        setDrawable();
    }

    private void setDrawable() {
        if (background_icon != 0) {
            drawable = context.getResources().getDrawable(background_icon);
            setBack(drawable);
        }
    }

    private void iconDestroy() {
        if (drawable != null) {
            setBack(null);
            drawable.setCallback(null);
            drawable = null;
        }
    }

    private void setBack(Drawable drawable) {
        this.setBackground(drawable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = widthMeasureSpec - MeasureSpec.getMode(widthMeasureSpec);
        int height = heightMeasureSpec - MeasureSpec.getMode(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.setDrawable();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.iconDestroy();
    }
}