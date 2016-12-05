package com.leihan.ibroadcast;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 有加载进度指示的ImageView
 *
 * @author lei.han
 * @time 2016/12/5 下午10:47
 */
public class ProImageView extends ImageView {

    private int progress;
    private boolean showProgress;
    private Paint paint;

    public ProImageView(Context context) {
        this(context, null);
    }

    public ProImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!showProgress) {
            return;
        }
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.parseColor("#70000000"));
        canvas.drawRect(0, 0, getWidth(), getHeight() - getHeight() * progress / 100, paint);

        paint.setColor(Color.parseColor("#00000000"));
        canvas.drawRect(0, getHeight() - getHeight() * progress / 100, getWidth(), getHeight(), paint);

        paint.setTextSize(30);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        Rect rect = new Rect();
        paint.getTextBounds("100%", 0, "100%".length(), rect);
        canvas.drawText(progress + "%", getWidth() / 2 - rect.width() / 2, getHeight() / 2, paint);

        if (progress == 100) {
            showProgress = false;
            invalidate();
        }
    }

    /**
     * 设置上传进度
     *
     * @param progress 进度值（0～100）
     */
    public void setProgress(int progress) {
        showProgress = true;
        if (progress < 0) {
            this.progress = 0;
        } else if (progress > 100) {
            this.progress = 100;
        } else {
            this.progress = progress;
        }
        postInvalidate();
    }

}

