package com.zenfer.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.zenfer.demo.R;
import com.zenfer.demo.util.DoubleClickUtil;
import com.zenfer.demo.util.LogUtil;
import com.zenfer.demo.util.ResourceUtil;
import com.zenfer.demo.widget.titlebar.AlphaImageView;
import com.zenfer.demo.widget.titlebar.AlphaTextView;
import com.zenfer.demo.widget.titlebar.MaterialRippleLayout;

import javax.annotation.Nonnull;

/**
 * Created by zhangwulin on 2016/9/7.
 * Email:zhangwulin@feitaikeji.com
 */
public class TitleBar extends LinearLayout {

    private MaterialRippleLayout mBackButton;
    private ImageView mBackIconView;
    private TextView mTitleView;
    private FrameLayout mBackgroundLayout;
    private Drawable mIconDrawable;
    private String mTitleValue;
    private TextView mBackContentText;
    private AlphaTextView mRightButton;
    private AlphaImageView mRightImageButton;
    private int mTitleTextSize;
    private int mTitleTextColor;
    private Drawable mBackground;
    private boolean mShowRightButton;
    private OnBackButtonClickListener mListener;
    private OnRightButtonClickListener mRightListener;
    private OnCloseButtonClickListener mCloseListener;
    private Context mContext;
    private ImageView mCloseIcon;
    private MaterialRippleLayout mCloseButton;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initView();
        setTitleView();
        mContext = context;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mIconDrawable = a.getDrawable(R.styleable.TitleBar_iconDrawable);
        mTitleValue = a.getString(R.styleable.TitleBar_titleValue);
        mTitleTextSize = a.getDimensionPixelSize(R.styleable.TitleBar_titleTextSize, ConvertUtils.dp2px(18));
        mTitleTextColor = a.getColor(R.styleable.TitleBar_titleContentColor, Color.WHITE);
        mBackground = a.getDrawable(R.styleable.TitleBar_titleBackground);
        mShowRightButton = a.getBoolean(R.styleable.TitleBar_showRightButton, false);
        a.recycle();
    }

    private void initView() {
        setOrientation(VERTICAL);
        View parentView = View.inflate(getContext(), R.layout.comm_action_bar_layout, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(parentView, params);
        mBackContentText = (TextView) parentView.findViewById(R.id.back_content);
        mCloseButton = (MaterialRippleLayout) parentView.findViewById(R.id.common_close);
        mBackButton = (MaterialRippleLayout) parentView.findViewById(R.id.common_back);
        mBackIconView = (ImageView) parentView.findViewById(R.id.common_back_icon);
        mTitleView = (TextView) parentView.findViewById(R.id.common_title);
        mCloseIcon = parentView.findViewById(R.id.common_close_icon);
        mBackgroundLayout = (FrameLayout) parentView.findViewById(R.id.title_bar_layout);
        mRightImageButton = (AlphaImageView) parentView.findViewById(R.id.right_image_button);
        mRightButton = (AlphaTextView) parentView.findViewById(R.id.right_button);
        mRightButton.setVisibility(mShowRightButton ? VISIBLE : GONE);
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!DoubleClickUtil.getInstance().enableClick()) {
                    return;
                }
                if (mRightListener != null) {
                    mRightListener.onRightButtonClick();
                }
            }
        });
        mBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.i("进入点击事件");
                if (!DoubleClickUtil.getInstance().enableClick()) {
                    return;
                }
                if (mListener != null) {
                    mListener.onBackButtonClick();
                }
            }
        });
        mRightImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DoubleClickUtil.getInstance().enableClick()) {
                    return;
                }
                if (mRightListener != null) {
                    mRightListener.onRightButtonClick();
                }
            }
        });
        mCloseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DoubleClickUtil.getInstance().enableClick()) {
                    return;
                }
                if (mCloseListener != null) {
                    mCloseListener.onCloseButtonClick();
                }
            }
        });
    }

    public void setBackContent(String content) {
        setBackContent(content, R.color.black);
    }

    public void setBackContent(String content, @ColorRes int textColorResId) {
        if (TextUtils.isEmpty(content)) {
            mBackContentText.setVisibility(GONE);
            return;
        }
        mBackContentText.setVisibility(VISIBLE);
        mBackContentText.setText(content);
        mBackContentText.setTextColor(ResourceUtil.getColor(textColorResId));
    }

    public void setCloseButtonVisible(boolean isShow) {
        if (isShow) {
            mCloseButton.setVisibility(VISIBLE);
            mBackButton.setPadding(0, 0, 0, 0);
            return;
        }
        mBackButton.setPadding(0, 0, ConvertUtils.dp2px(20), 0);
        mCloseButton.setVisibility(GONE);
    }

    private void setIconView() {
        mBackIconView.setImageDrawable(mIconDrawable);
    }

    public void setBackIconView(@Nonnull Drawable drawable) {
        mIconDrawable = drawable;
        setIconView();
    }

    public void setCloseIconView(int resId) {
        mCloseIcon.setImageDrawable(ResourceUtil.getDrawable(resId));
    }

    public void hideBackBtn() {
        mBackButton.setVisibility(INVISIBLE);
    }

    public void showBackBtn() {
        mBackButton.setVisibility(VISIBLE);
    }

    private void setTitleView() {
        setIconView();
        mBackgroundLayout.setBackgroundDrawable(mBackground);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        mTitleView.setText(mTitleValue);
    }

    public void setTitle(String content) {
        if (content == null) {
            content = "";
        }
        mTitleValue = content;
        setTitleView();
    }

    public void setTitleTextSize(int dimen) {
        if (dimen <= 0) {
            mTitleTextSize =ConvertUtils.dp2px(15);
        }
        mTitleTextSize = dimen;
        setTitleView();
    }

    public void setTitleTextColor(int color) {
        if (color <= 0) {
            mTitleTextColor = ResourceUtil.getColor(R.color.white);
        }
        mTitleTextColor = color;
        setTitleView();
    }

    public void setTitleBackground(int resId) {
        mBackground = ResourceUtil.getDrawable(resId);
        setTitleView();
    }

    public void setShowRightButton(boolean isShow) {
        mRightButton.setVisibility(isShow ? VISIBLE : GONE);
        mRightImageButton.setVisibility(GONE);
    }

    public void setShowRightImageButton(boolean isShow) {
        mRightButton.setVisibility(GONE);
        mRightImageButton.setVisibility(isShow ? VISIBLE : GONE);
    }

    public void setRightImageButtonDrawable(int resId) {
        if (mRightImageButton == null) {
            return;
        }
        mRightImageButton.setImageResource(resId);
    }

    public AlphaTextView getRightButton() {
        return mRightButton;
    }

    public AlphaImageView getRightImageButton() {
        return mRightImageButton;
    }

    public void setShowUnderLine(boolean showUnderLine) {
        if (showUnderLine) {
            mRightButton.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        } else {
            mRightButton.getPaint().setFlags(Paint.SUBPIXEL_TEXT_FLAG);
        }
        mRightButton.getPaint().setAntiAlias(true);
    }

    public void setRightButtonTextColor(int resId) {
        mRightButton.setTextColor(ResourceUtil.getColor(resId));
    }

    public void setRightButtonText(String text) {
        mRightButton.setVisibility(View.VISIBLE);
        mRightButton.setText(text);
    }

    public void setRightButtonTag(Object tag) {
        mRightButton.setTag(tag);
    }

    public void setRightImageButtonTag(Object tag) {
        mRightImageButton.setTag(tag);
    }

    public void setRightButtonText(CharSequence text) {
        mRightButton.setText(text);
    }

    public void setOnBackButtonClickListener(OnBackButtonClickListener listener) {
        mListener = listener;
    }

    public interface OnBackButtonClickListener {
        void onBackButtonClick();
    }

    public void setOnRightButtonClickListener(OnRightButtonClickListener listener) {
        mRightListener = listener;
    }

    public interface OnRightButtonClickListener {
        void onRightButtonClick();
    }

    public void setOnCloseButtonClickListener(OnCloseButtonClickListener listener) {
        mCloseListener = listener;
    }

    public interface OnCloseButtonClickListener {
        void onCloseButtonClick();
    }
}
