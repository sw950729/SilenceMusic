package com.silence.music.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.angel.music.R;
import com.silence.music.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangrui on 2018/1/8
 */
public class SimpleToolbar extends Toolbar implements View.OnClickListener {

    public static final int DEFAULT_CONTENT_LAYOUT = R.layout.common_titlebar;

    protected ViewGroup childView;
    private TextView tvTitle;
    private ImageView ivBack;
    private List<View> menuViews;
    private OnClickListener backIconOnClickListener;
    private MenuClickListener menuClickListener;

    public SimpleToolbar(Context context) {
        super(context);
        init(null);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setContentInsetsAbsolute(0, 0);
        setContentInsetsRelative(0, 0);
        setMinimumWidth(0);
        setMinimumHeight(0);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleToolbar);

        int contentLayout = typedArray.getResourceId(R.styleable.SimpleToolbar_customLayout, DEFAULT_CONTENT_LAYOUT);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        childView = (ViewGroup) inflater.inflate(contentLayout, this, false);
        addView(childView);
        boolean isCustomLayout = contentLayout != DEFAULT_CONTENT_LAYOUT;
        if (!isCustomLayout) {
            //默认layout调用
            tvTitle = childView.findViewById(R.id.tv_title);
            View btBack = childView.findViewById(R.id.llBack);
            ivBack = childView.findViewById(R.id.ivBack);
            btBack.setOnClickListener(this);

            boolean showContentLayout = typedArray.getBoolean(R.styleable.SimpleToolbar_showNavigationBar, true);
            if (!showContentLayout) {
                btBack.setVisibility(GONE);
            }

            //标题字体大小
            int titleTextSize = (int) typedArray.getDimension(R.styleable.SimpleToolbar_customTitleTextSize, -1);
            if (titleTextSize > 0) {
                titleTextSize = Utils.dip2px(getContext(), titleTextSize);
                setTitleTextSize(titleTextSize);
            }

            //标题字体颜色
            int titleTextColorRes = typedArray.getResourceId(R.styleable.SimpleToolbar_customTitleTextColor, -1);
            if (titleTextColorRes != -1) {
                int textColor = ResourcesCompat.getColor(getResources(), titleTextColorRes, getContext().getTheme());
                setTitleTextColor(textColor);
            } else {
                int titleTextColor = typedArray.getColor(R.styleable.SimpleToolbar_customTitleTextColor, -1);
                if (titleTextColor != -1) {
                    setTitleTextColor(titleTextColor);
                }
            }

            //标题内容
            final int titleRes = typedArray.getResourceId(R.styleable.SimpleToolbar_customTitle, -1);
            if (titleRes > -1) {
                //防止title被覆盖
                post(() -> setTitle(titleRes));
            } else {
                final String title = typedArray.getString(R.styleable.SimpleToolbar_customTitle);
                if (!TextUtils.isEmpty(title)) {
                    post(() -> setTitle(title));
                }
            }

            //三个菜单
            int menu1Resource = typedArray.getResourceId(R.styleable.SimpleToolbar_menu1, -1);
            if (menu1Resource != -1) {
                addMenu(menu1Resource);
            } else {
                String menuText = typedArray.getString(R.styleable.SimpleToolbar_menu1);
                if (null != menuText) {
                    addTextMenu(menuText);
                }
            }

            int menu2Resource = typedArray.getResourceId(R.styleable.SimpleToolbar_menu2, -1);
            if (menu2Resource != -1) {
                addMenu(menu2Resource);
            } else {
                String menuText = typedArray.getString(R.styleable.SimpleToolbar_menu2);
                if (null != menuText) {
                    addTextMenu(menuText);
                }
            }

            int menu3Resource = typedArray.getResourceId(R.styleable.SimpleToolbar_menu3, -1);
            if (menu3Resource != -1) {
                addMenu(menu3Resource);
            } else {
                String menuText = typedArray.getString(R.styleable.SimpleToolbar_menu3);
                if (null != menuText) {
                    addTextMenu(menuText);
                }
            }
        }

        typedArray.recycle();
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getResources().getString(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        if (null != tvTitle) {
            tvTitle.setText(title);
            tvTitle.setSingleLine();
            Configuration config = getResources().getConfiguration();
            if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
                tvTitle.setMaxEms(8);
            }
        }
    }

    @Override
    public void setTitleTextColor(@ColorInt int color) {
        if (null != tvTitle) {
            tvTitle.setTextColor(color);
        }
    }

    /**
     * 重写measure方法默认只measure自定义的填充布局
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;
        int childState = 0;

        final View child = childView;
        width += measureChildCollapseMargins(child, widthMeasureSpec, width, heightMeasureSpec, 0);
        height = Math.max(height, child.getMeasuredHeight());
        childState = View.combineMeasuredStates(childState, ViewCompat.getMeasuredState(child));

        width += getPaddingLeft() + getPaddingRight();
        height += getPaddingTop() + getPaddingBottom();

        final int measuredWidth = ViewCompat.resolveSizeAndState(Math.max(width, getSuggestedMinimumWidth()), widthMeasureSpec, childState & ViewCompat.MEASURED_STATE_MASK);
        final int measuredHeight = ViewCompat.resolveSizeAndState(Math.max(height, getSuggestedMinimumHeight()),
                heightMeasureSpec, childState << ViewCompat.MEASURED_HEIGHT_STATE_SHIFT);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int measureChildCollapseMargins(View child,
                                            int parentWidthMeasureSpec, int widthUsed,
                                            int parentHeightMeasureSpec, int heightUsed) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        final int leftDiff = lp.leftMargin;
        final int rightDiff = lp.rightMargin;
        final int leftMargin = Math.max(0, leftDiff);
        final int rightMargin = Math.max(0, rightDiff);
        final int hMargins = leftMargin + rightMargin;

        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                getPaddingLeft() + getPaddingRight() + hMargins + widthUsed, lp.width);
        final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
                getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        return child.getMeasuredWidth() + hMargins;
    }

    /**
     * 设置标题字体大小
     *
     * @param sizePx 单位px
     */
    public void setTitleTextSize(float sizePx) {
        if (null != tvTitle) {
            tvTitle.setTextSize(sizePx);
        }
    }

    public float getTitltTextSize() {
        if (null != tvTitle) {
            return tvTitle.getTextSize();
        }
        return -1;
    }

    public void addMenu(int icon) {
        Drawable menuDrawable = null;
        try {
            menuDrawable = ContextCompat.getDrawable(getContext(), icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == menuDrawable) {
            String menuText = getResources().getString(icon);
            addTextMenu(menuText);
            return;
        }

        if (null == menuViews) {
            menuViews = new ArrayList<>();
        }
        int dip20 = Utils.dip2px(getContext(), 20);
        int dip48 = Utils.dip2px(getContext(), 48);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip48, ViewGroup.LayoutParams.MATCH_PARENT);
        if (menuViews.size() == 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        } else {
            layoutParams.addRule(RelativeLayout.LEFT_OF, menuViews.size());
        }
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        AppCompatImageView imageView = new AppCompatImageView(getContext());
        imageView.setLayoutParams(layoutParams);

        int padding = (int) (1.0f * (dip48 - dip20) / 2);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageDrawable(menuDrawable);

        childView.addView(imageView);
        menuViews.add(imageView);
        imageView.setId(menuViews.size());
        imageView.setOnClickListener(this);
    }

    public void addTextMenu(String text) {
        if (null == menuViews) {
            menuViews = new ArrayList<>();
        }
        int dip15 = Utils.dip2px(getContext(), 15);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (menuViews.size() == 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        } else {
            layoutParams.addRule(RelativeLayout.LEFT_OF, menuViews.size());
        }
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        AppCompatTextView textView = new AppCompatTextView(getContext());
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(dip15, 0, dip15, 0);
        textView.setTextSize(15);
        textView.setTextColor(Color.WHITE);
        textView.setText(text);

        childView.addView(textView);
        menuViews.add(textView);
        textView.setId(menuViews.size());
        textView.setOnClickListener(this);
    }

    public void setMenuText(int position, String text) {
        if (menuViews == null || menuViews.size() <= position) {
            return;
        }
        View view = menuViews.get(position);
        if (view instanceof TextView) {
            TextView menueView = (TextView) view;
            menueView.setText(text);
        }
    }

    public void setMenuVisible(int position, int visible) {
        if (getMenuSize() > position) {
            menuViews.get(position).setVisibility(visible);
        }
    }

    public void setMenuDrawable(int position, Drawable drawable) {
        if (!Utils.isListNotEmpty(menuViews) || menuViews.size() <= position) {
            return;
        }
        View view = menuViews.get(position);
        if (view instanceof ImageView) {
            ImageView menueView = (ImageView) view;
            menueView.setImageDrawable(drawable);
        }
    }

    public void setMenuResource(int position, int res) {
        if (!Utils.isListNotEmpty(menuViews) || menuViews.size() <= position) {
            return;
        }
        View view = menuViews.get(position);
        if (view instanceof ImageView) {
            ImageView menueView = (ImageView) view;
            menueView.setImageResource(res);
        }
    }

    public void removeAllMenus() {
        if (null != menuViews && menuViews.size() > 0) {
            for (View menuView : menuViews) {
                childView.removeView(menuView);
            }
        }
    }

    public int getMenuSize() {
        return menuViews != null ? menuViews.size() : 0;
    }

    @Override
    public void setNavigationIcon(@Nullable Drawable icon) {
        if (null != ivBack) {
            ivBack.setImageDrawable(icon);
        }
    }

    public void setBackIconVisible(boolean visible) {
        ivBack.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setBackIconOnClickListener(OnClickListener backIconOnClickListener) {
        this.backIconOnClickListener = backIconOnClickListener;
    }

    public void setMenuClickListener(MenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    public List<View> getMenuViews() {
        return menuViews;
    }

    @Override
    public void setNavigationIcon(@DrawableRes int resId) {
        setNavigationIcon(ResourcesCompat.getDrawable(getResources(), resId, getContext().getTheme()));
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        //小于10默认为menu的点击事件
        if ((v.getId() > 0) && (v.getId() < 10)) {
            if (null != menuClickListener) {
                menuClickListener.onMenuClick(v, v.getId());
            }
            return;
        }
        switch (v.getId()) {
            case R.id.llBack:
                if (this.backIconOnClickListener != null) {
                    this.backIconOnClickListener.onClick(v);
                }
                break;
            default:
                break;
        }
    }

    public interface MenuClickListener {
        /**
         * menu被点击
         *
         * @param view
         * @param menuPosition menu的位置，从右往左，从1开始
         */
        void onMenuClick(View view, int menuPosition);
    }
}
