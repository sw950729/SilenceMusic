package com.moudle.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.basemoudle.R;


/**
 * @author Silence
 * @date 2018/4/23
 */
public class SWPasswordText extends SWEditText {

    private int pic_open = R.drawable.close;
    private int pic_close = R.drawable.open;
    //按钮宽度dp
    private int width;
    //按钮的bitmap
    private Bitmap pic_invisiable;
    private Bitmap pic_visiable;
    //间隔
    private int Interval;
    //内容是否可见
    private boolean isVisible = false;

    public SWPasswordText(final Context context) {
        super(context);
    }

    public SWPasswordText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SWPasswordText);
        pic_open = array.getResourceId(R.styleable.SWPasswordText_pic_open, R.drawable.close);
        pic_close = array.getResourceId(R.styleable.SWPasswordText_pic_close, R.drawable.open);
        setTransformationMethod(PasswordTransformationMethod.getInstance());
        width = getWidth_clear();
        Interval = getInterval();
        addRight(width + Interval);
        pic_invisiable = createBitmap(pic_open, context);
        pic_visiable = createBitmap(pic_close, context);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int right = getWidth() + getScrollX() - Interval;
        int left = right - width;
        int top = (getHeight() - width) / 2;
        int bottom = top + width;
        @SuppressLint("DrawAllocation") Rect rect = new Rect(left, top, right, bottom);
        if (isVisible) {
            canvas.drawBitmap(pic_visiable, null, rect, null);
        } else {
            canvas.drawBitmap(pic_invisiable, null, rect, null);
        }
    }

    @Override
    protected void drawClear(int translationX, Canvas canvas) {
        float scale = 1f - (float) (translationX) / (float) (getWidth_clear() + Interval);
        int right = (int) (getWidth() + getScrollX() - Interval - width - Interval - getWidth_clear() * (1f - scale) / 2f);
        int left = (int) (getWidth() + getScrollX() - Interval - width - Interval - getWidth_clear() * (scale + (1f - scale) / 2f));
        int top = (int) ((getHeight() - getWidth_clear() * scale) / 2);
        int bottom = (int) (top + getWidth_clear() * scale);
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(getBitmap_clear(), null, rect, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            boolean touchable = (getWidth() - width - Interval < event.getX()) && (event.getX() < getWidth() - Interval);
            if (touchable) {
                isVisible = !isVisible;
                if (isVisible) {
                    //设置EditText文本为可见的
                    setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void setPic_open(int pic_open) {
        this.pic_open = pic_open;
        invalidate();
    }

    public void setPic_close(int pic_close) {
        this.pic_close = pic_close;
        invalidate();
    }
}