package com.zenfer.demo.widget.titlebar;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by substring on 2016/8/1.
 * Email：zhangxuan@feitaikeji.com
 */
public class AlphaImageView extends AppCompatImageView {

    private static final int DEFAULT_ALPHA = 255;
    private static final int DISABLED_ALPHA = 90;
    private static final int PR_ALPHA = 180;

    public AlphaImageView(Context context) {
        super(context);
    }

    public AlphaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        changeImageStateDrawable(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean rt = super.onTouchEvent(event);
        if (isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    changeImageStateDrawable(true);
                    postDelayed(mStateWatchdog, 200);
                    break;
                case MotionEvent.ACTION_MOVE:
                    removeCallbacks(mStateWatchdog);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    removeCallbacks(mStateWatchdog);
                    /**
                     * post the change requestWithLoading to avoid the down and up drawable
                     * state changing happen at the same frame
                     */
                    post(new Runnable() {

                        @Override
                        public void run() {
                            changeImageStateDrawable(false);
                        }

                    });
                    break;
                default:
            }
        }
        return rt;
    }

    private Runnable mStateWatchdog = new Runnable() {
        @Override
        public void run() {
            changeImageStateDrawable(false);
        }
    };

    private void changeImageStateDrawable(boolean isPressed) {
        if (!isEnabled()) {
            setAlpha(DISABLED_ALPHA);
        } else {
            if (isPressed) {
                setAlpha(PR_ALPHA);
            } else {
                setAlpha(DEFAULT_ALPHA);
            }
        }
    }
}
