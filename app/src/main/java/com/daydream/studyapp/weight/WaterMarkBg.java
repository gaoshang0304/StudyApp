package com.daydream.studyapp.weight;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by dayDream on 2018/5/11.
 */

public class WaterMarkBg extends Drawable {

    private final String mText;
    private final int mBgColor;
    private final int mTextColor;
    private final int mTextSize;
    private Paint mPaint = new Paint();

    public WaterMarkBg(String text, int bgColor, int textColor, int textSize) {
        mText = text;
        mBgColor = bgColor;
        mTextColor = textColor;
        mTextSize = textSize;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().right;
        int height = getBounds().bottom;

        //画布颜色，即背景色
        canvas.drawColor(mBgColor);
        //文字的颜色
        mPaint.setColor(mTextColor);
        mPaint.setAntiAlias(true); //抗锯齿
        mPaint.setTextSize(mTextSize);
        canvas.save();
        //旋转的角度
        canvas.rotate(-20);
        //测量到文字的宽
        float textWidth = mPaint.measureText(mText);
        int index = 0;
        //height / num ; num 为水印的行数
        for (int positionY = height / 11; positionY <= height; positionY += height / 11) {
            float fromX = -width + (index++ % 2) * textWidth;
            for (float positionX = fromX; positionX < width; positionX += textWidth * 2) {
                canvas.drawText(mText, positionX, positionY, mPaint);
            }
        }
        canvas.restore();
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
