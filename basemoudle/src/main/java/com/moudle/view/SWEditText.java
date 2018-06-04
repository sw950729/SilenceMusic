package com.moudle.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.basemoudle.R;


/**
 * @author Silence
 * @date 2018/4/23
 */
public class SWEditText extends android.support.v7.widget.AppCompatEditText {

    private Context context;
    private Bitmap clear_Bitmap;
    //默认的清除图片
    private int clear_pic = R.drawable.clearfill;
    //动画时长
    private final int ANIMATOR_TIME = 200;
    //按钮左右间隔,单位dp
    private final int INTERVAL = 5;
    //清除按钮宽度,单位dp
    private final int WIDTH_CLEAR = 25;
    private int interval;
    private int width_clear;
    private int paddingRight;
    private boolean isVisible = false;
    //清除按钮出现动画
    private ValueAnimator animator_visible;
    //消失动画
    private ValueAnimator animator_gone;
    //右边添加其他按钮时使用(password)
    private int myright = 0;

    public SWEditText(Context context) {
        super(context);
        this.context = context;
    }

    public SWEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inital(attrs);
    }

    private void inital(AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SWEditText);
        interval = array.getInt(R.styleable.SWEditText_interval, INTERVAL);
        width_clear = array.getInt(R.styleable.SWEditText_width_clear, WIDTH_CLEAR);
        clear_pic = array.getResourceId(R.styleable.SWEditText_clear_pic, R.drawable.clearfill);
        clear_Bitmap = createBitmap(clear_pic, context);
        interval = dp2px(INTERVAL);
        width_clear = dp2px(WIDTH_CLEAR);
        paddingRight = interval * 2 + width_clear;
        animator_gone = ValueAnimator.ofFloat(1f, 0f).setDuration(ANIMATOR_TIME);
        animator_visible = ValueAnimator.ofInt(width_clear + interval, 0).setDuration(ANIMATOR_TIME);
        array.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG));
        if (animator_visible.isRunning()) {
            int x = (int) animator_visible.getAnimatedValue();
            drawClear(x, canvas);
            invalidate();
        } else if (isVisible) {
            drawClear(0, canvas);
        }

        if (animator_gone.isRunning()) {
            float scale = (float) animator_gone.getAnimatedValue();
            drawClearGone(scale, canvas);
            invalidate();
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (text.length() > 0) {
            if (!isVisible) {
                isVisible = true;
                startVisibleAnimator();
            }
        } else {
            if (isVisible) {
                isVisible = false;
                startGoneAnimator();
            }
        }
    }

    private void startVisibleAnimator() {
        endAnaimator();
        animator_visible.start();
        invalidate();
    }

    private void startGoneAnimator() {
        endAnaimator();
        animator_gone.start();
        invalidate();
    }

    private void endAnaimator() {
        animator_gone.end();
        animator_visible.end();
    }

    protected void drawClear(int translationX, Canvas canvas) {
        int right = getWidth() + getScrollX() - interval - myright + translationX;
        int left = right - width_clear;
        int top = (getHeight() - width_clear) / 2;
        int bottom = top + width_clear;
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(clear_Bitmap, null, rect, null);

    }

    protected void drawClearGone(float scale, Canvas canvas) {
        int right = (int) (getWidth() + getScrollX() - interval - myright - width_clear * (1f - scale) / 2f);
        int left = (int) (getWidth() + getScrollX() - interval - myright - width_clear * (scale + (1f - scale) / 2f));
        int top = (int) ((getHeight() - width_clear * scale) / 2);
        int bottom = (int) (top + width_clear * scale);
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(clear_Bitmap, null, rect, null);
    }

    public Bitmap createBitmap(int resources, Context context) {
        final Drawable drawable = ContextCompat.getDrawable(context, resources);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        return drawableToBitamp(wrappedDrawable);
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {

            boolean touchable = (getWidth() - interval - myright - width_clear < event.getX()) && (event.getX() < getWidth() - interval - myright);
            if (touchable) {
                setError(null);
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setPadding(getPaddingLeft(), getPaddingTop(), paddingRight + myright, getPaddingBottom());
    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void setInterval(int interval) {
        int px = dp2px(interval);
        if (this.interval != px) {
            this.interval = px;
            invalidate();
        }
    }

    public void setWidth_clear(int width_clear) {
        int px = dp2px(width_clear);
        if (this.width_clear != px) {
            this.width_clear = px;
            invalidate();
        }
    }

    public void setClear_pic(int clear_pic) {
        this.clear_pic = clear_pic;
        invalidate();
    }

    public int getInterval() {
        return interval;
    }

    public int getWidth_clear() {
        return width_clear;
    }

    public Bitmap getBitmap_clear() {
        return clear_Bitmap;
    }

    public void addRight(int right) {
        myright += right;
    }
}