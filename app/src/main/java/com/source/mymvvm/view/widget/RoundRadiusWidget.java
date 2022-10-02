package com.source.mymvvm.view.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoundRadiusWidget extends RelativeLayout {

    private int mRadius = 0;

    public RoundRadiusWidget(@NonNull Context context) {
        this(context,null);
    }

    public RoundRadiusWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setRadius(int radius) {
        this.mRadius = radius;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        path.addRoundRect(rectF, mRadius, mRadius, Path.Direction.CW);

        canvas.clipPath(path);
        super.dispatchDraw(canvas);
    }
}
