package com.silence.music.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.angel.music.R;
import com.silence.music.interfaces.OnSwitchStateChangedListener;

/**
 * @author Silence
 * @date 2018/4/23
 */
public class SWToggleButton extends View {


    //开启颜色
    private int onColor = Color.parseColor("#4ebb7f");
    // 关闭颜色
    private int offColor = Color.parseColor("#ffffff");
    //边框颜色
    private int borderColor = Color.parseColor("#dadbda");
    private Paint paint;
    private Path path;
    //视图本身宽高
    private float width;
    private float height;
    //按钮
    private float left, top, bottom, right;
    private float cx, cy;
    private boolean isChoose = false;
    private float isAnimation = 0;
    //圆
    private float radius;
    private float borderwidth = 0;
    private float circle_width;
    private float circle_left, circle_right;
    private float circle_cx;
    private OnSwitchStateChangedListener listener;

    public SWToggleButton(Context context) {
        super(context);
    }

    public SWToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        inital(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = (int) (widthSize * 0.65f);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        left = top = 0;
        right = width;
        bottom = height * 0.8f;
        cx = (right + left) / 2;
        cy = (bottom + top) / 2;
        RectF rectF = new RectF(left, top, bottom, bottom);
        path.arcTo(rectF, 90, 180);
        rectF.left = right - bottom;
        rectF.right = right;
        path.arcTo(rectF, 270, 180);
        path.close();
        circle_left = 0;
        circle_right = bottom;
        circle_width = circle_right - circle_left;
        float circle_height = (bottom - top) / 2;
        radius = circle_height * 0.9f;
        borderwidth = (int) (2 * (circle_height - radius));
        circle_cx = width - circle_height;
    }

    private void inital(Context context, AttributeSet attrs) {
        paint = new Paint();
        path = new Path();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SWToggleButton);
        onColor = array.getColor(R.styleable.SWToggleButton_opencolor, onColor);
        offColor = array.getColor(R.styleable.SWToggleButton_closecolor, offColor);
        borderColor = array.getColor(R.styleable.SWToggleButton_bordercolor, borderColor);
        array.recycle();

    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        if (isChoose) {
            paint.setColor(onColor);
        } else {
            paint.setColor(offColor);
        }
        canvas.drawPath(path, paint);
        isAnimation = isAnimation - 0.1f > 0 ? isAnimation - 0.1f : 0;
        //缩放大小参数随isAnimation变化而变化
        final float scale = 0.98f * (isChoose ? isAnimation : 1 - isAnimation);
        //保存canvas状态
        canvas.save();
        canvas.scale(scale, scale, circle_cx, cy);
        paint.setColor(offColor);
        canvas.drawPath(path, paint);
        canvas.restore();
        paint.reset();
        float bTranslateX = width - circle_width;
        final float translate = bTranslateX * (isChoose ? 1 - isAnimation : isAnimation);
        canvas.translate(translate, 0);
        if (isAnimation > 0) {
            invalidate();
        }
        canvas.save();
        paint.setStyle(Style.FILL);
        paint.setColor(offColor);
        canvas.drawCircle(circle_width / 2, circle_width / 2, radius, paint); // 按钮白底
        paint.setStyle(Style.STROKE);
        paint.setColor(borderColor);
        paint.setStrokeWidth(borderwidth);
        canvas.drawCircle(circle_width / 2, circle_width / 2, radius, paint); // 按钮灰边
        canvas.restore();
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_CANCEL:
                return true;
            case MotionEvent.ACTION_UP:
                isAnimation = 1;
                isChoose = !isChoose;
                listener.onStateChanged(isChoose);
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);

    }

    public void setOnColor(int onColor) {
        this.onColor = onColor;
    }

    public void setOffColor(int offColor) {
        this.offColor = offColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setOnSwitchStateChangedListener(OnSwitchStateChangedListener listener) {
        this.listener = listener;
    }
}