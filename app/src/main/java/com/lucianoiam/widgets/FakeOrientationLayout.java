package com.lucianoiam.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

class FakeOrientationLayout extends FrameLayout {

    private boolean mRotated;

    public FakeOrientationLayout(final Context context) {
        super(context);
    }

    public FakeOrientationLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRotated(boolean rotated) {
        mRotated = rotated;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mRotated) {
            setRotation(-90.f);
            final int width = getWidth();
            final int height = getHeight();
            final int offset = Math.abs(width - height) / 2;
            if (width > height) {
                setTranslationX(-offset);
                setTranslationY(offset);
            } else {
                setTranslationX(offset);
                setTranslationY(-offset);
            }
        } else {
            setRotation(0);
            setTranslationX(0);
            setTranslationY(0);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRotated) {
            final int temp = widthMeasureSpec;
            //noinspection SuspiciousNameCombination
            widthMeasureSpec = heightMeasureSpec;
            heightMeasureSpec = temp;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}