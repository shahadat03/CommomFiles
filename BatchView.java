package com.happihub.bd.Utility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class BatchView extends TextView {
    public BatchView(Context context) {
        super(context);
    }

    public BatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int r = Math.max(getMeasuredWidth(),getMeasuredHeight());
        setMeasuredDimension(r, r);

    }
}
