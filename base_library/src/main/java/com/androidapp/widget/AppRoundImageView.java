package com.androidapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

public class AppRoundImageView extends AppCompatImageView {

    private static final int BORDER_WIDTH = 12;
    private float width, height;

    public AppRoundImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public AppRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public AppRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width >= BORDER_WIDTH && height > BORDER_WIDTH) {
            Path path = new Path();
            //四个圆角
            path.moveTo(BORDER_WIDTH, 0);
            path.lineTo(width - BORDER_WIDTH, 0);
            path.quadTo(width, 0, width, BORDER_WIDTH);
            path.lineTo(width, height - BORDER_WIDTH);
            path.quadTo(width, height, width - BORDER_WIDTH, height);
            path.lineTo(BORDER_WIDTH, height);
            path.quadTo(0, height, 0, height - BORDER_WIDTH);
            path.lineTo(0, BORDER_WIDTH);
            path.quadTo(0, 0, BORDER_WIDTH, 0);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
