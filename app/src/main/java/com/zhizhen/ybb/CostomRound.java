package com.zhizhen.ybb;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 作者：tc on 2017/5/11.
 * 邮箱：qw805880101@qq.com
 * 版本：v1.0
 */
public class CostomRound extends View {
    /**
     * 文本
     */
    private String mProgressText = "0%";
    /**
     * 文本的颜色
     */
    private int mProgressTextColor;
    /**
     * 文本的大小
     */
    private int mProgressTextSize;

    /**
     * 圆长度
     */
    private int mRoundLength;

    private String stateText = "";

    private int mWidth, mHeight;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CostomRound(Context context) {
        this(context, null);
    }

    public CostomRound(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CostomRound(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        System.out.println("CostomRound");
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomRoundView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomRoundView_progress_text:
                    mProgressText = a.getString(attr);
                    break;
                case R.styleable.CustomRoundView_progress_text_color:
                    // 默认颜色设置为黑色
                    mProgressTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomRoundView_progress_text_size:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mProgressTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 38, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomRoundView_round_length:
                    mRoundLength = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 0, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mBound = new Rect();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了

        mPaint.setColor(this.getResources().getColor(R.color.blue_27273d));
        canvas.drawCircle(mRoundLength / 2, mRoundLength / 2, mRoundLength / 2, mPaint);// 大圆

        mPaint.setColor(this.getResources().getColor(R.color.blue_383862));
        canvas.drawCircle(mRoundLength / 2, mRoundLength / 2, mRoundLength / 2 - 44, mPaint);// 小圆

        mPaint.setTextAlign(Align.CENTER);
        mPaint.setColor(mProgressTextColor);
        mPaint.setTextSize(mProgressTextSize);
        canvas.drawText(mProgressText, mRoundLength / 2, mRoundLength / 2 - 40, mPaint); //设置进度数字


        mPaint.setTextAlign(Align.CENTER);
        mPaint.setColor(this.getResources().getColor(R.color.white));
        mPaint.setTextSize(this.getResources().getDimension(R.dimen.size));
        canvas.drawText(stateText, mRoundLength / 2, mRoundLength / 2 + 127, mPaint); //设置状态字体


        Path path = new Path();
        canvas.save();
        path.reset();
        canvas.clipPath(path);
        path.addCircle(mWidth / 2, mHeight / 2, mWidth / 2 - 44, Direction.CCW);
        canvas.clipPath(path, Op.REPLACE);
        //每次刷新前需要reset路径
//        mPath_1.reset();
//        mPath_2.reset();
//        //两条波浪的移动速度让他不一样，避免动作过于重复
//        movePath = movePath + 5;
//        movePath2 = movePath2 + 4;
//
//        if (movePath >= mWidth) {
//            movePath = movePath - mWidth;
//        }
//        if (movePath2 >= mWidth) {
//            movePath2 = movePath2 - mWidth;
//        }
//        //第一条波浪
//        mPath_1.moveTo(-mWidth + movePath, mHeight / 3);
//        mPath_1.quadTo(-mWidth + mWidth / 4 + movePath, mHeight / 2 - 40, -mWidth + mWidth / 2 + movePath, mHeight / 2);
//        mPath_1.quadTo(-mWidth + mWidth * 3 / 4 + movePath, mHeight / 2 + 40, 0 + movePath, mHeight / 2);
//        mPath_1.quadTo(mWidth / 4 + movePath, mHeight / 2 - 40, mWidth / 2 + movePath, mHeight / 2);
//        mPath_1.quadTo(mWidth * 3 / 4 + movePath, mHeight / 2 + 40, mWidth + movePath, mHeight / 2);
//        mPath_1.lineTo(mWidth + movePath, mHeight);
//        mPath_1.lineTo(-mWidth + movePath, mHeight);
//        mPath_1.close();
        //第二条波浪
//        mPath_2.moveTo(mWidth + mWidth - movePath2, mHeight / 2);
//        mPath_2.quadTo(2 * mWidth - movePath2 - mWidth / 4, mHeight / 2 - 40, 2 * mWidth - movePath2 - mWidth / 2, mHeight / 2);
//        mPath_2.quadTo(2 * mWidth - mWidth * 3 / 4 - movePath2, mHeight / 2 + 40, mWidth - movePath2, mHeight / 2);
//        mPath_2.quadTo(mWidth * 3 / 4 - movePath2, mHeight / 2 - 40, mWidth / 2 - movePath2, mHeight / 2);
//        mPath_2.quadTo(mWidth / 4 - movePath2, mHeight / 2 + 40, -movePath2, mHeight / 2);
//        mPath_2.lineTo(0, mHeight);
//        mPath_2.lineTo(mWidth, mHeight);
//        mPath_2.close();

//        canvas.drawPath(mPath_1, paint_1);
//        canvas.drawPath(mPath_2, paint_2);

          createShader();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }


    /**
     * 设置连接进度
     *
     * @param num
     */
    public void setText(String num) {
        mProgressText = num;
        mPaint.getTextBounds(mProgressText, 0, mProgressText.length(), mBound);
        this.invalidate();
    }

    /**
     * 设置连接状态文字
     *
     * @param state
     */
    public void setTextState(String state) {
        stateText = state;
        this.invalidate();
    }

    public void createShader() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Draw default waves into the bitmap
        // y=Asin(ωx+φ)+h
        float waveX1 = 0;
        final float wave2Shift = getWidth() / 4;
        final float endX = getWidth();
        final float endY = getHeight();

        Paint wavePaint = new Paint();
        wavePaint.setStrokeWidth(2);
        wavePaint.setAntiAlias(true);

        while (waveX1 < endX) {
            double wx = waveX1 * 11;
            int startY = (int) (100 + 5 * Math.sin(wx));

            // draw bottom wave with the alpha 40
            canvas.drawLine(waveX1, startY, waveX1, endY, wavePaint);
            // draw top wave with the alpha 60
            float waveX2 = (waveX1 + wave2Shift) % endX;
            canvas.drawLine(waveX2, startY, waveX2, endY, wavePaint);

            waveX1++;
        }
    }


}
