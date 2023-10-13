package com.achpay.wallet.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.achpay.wallet.R;

/**
 * Created by PS on 2016/8/23.
 */
public class ScrollTextView extends View {

    private static final String TAG = ScrollTextView.class.getSimpleName();
    private float mTextSize=15;//字体大小

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 控件宽高
     */
    private int mViewHeight;
    private int mViewWidth;

    /**
     * 偏移的距离
     */
    private int mMoveLen = 0;

    /**
     * 停止
     */
    private boolean isStarting = false;

    /**
     * 每次偏移的距离 影响移动速度
     */
    private static int MOVELEN = 1;

    /**
     * 每次偏移的时间
     */
    private static final int MOVETIME = 5;

    /**
     * 文字颜色
     */
    private int mColorText = 0xFF9100;

    /**
     * 是否初始化完成
     */
    private boolean isInit = false;

    /**
     * 显示的内容
     */
    private String mText = "";

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScrollTextView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ScrollTextView_speed:
                    MOVELEN = a.getInteger(attr, 1);
                case R.styleable.ScrollTextView_contents:
                    mText = a.getString(attr);
            }

        }
        a.recycle();
        init();
    }

    public void setText(String text) {
        if (text == null) {
            return;
        }
        mText = text;
        init();
    }

    /**
     * @param speed 影响控件移动速度 > 0
     */
    public void setMoveSpeed(int speed) {
        if (speed < 0) {
            return;
        }
        MOVELEN = speed;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(mColorText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();
        isInit = true;
        invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        if (isInit) {
            mPaint.setTextSize(dip2px(mTextSize));
            mPaint.setAlpha(255);
            float x = (float) (mViewWidth - mMoveLen);
            float y = (float) (mViewHeight / 2.0);
            Paint.FontMetricsInt fmi = mPaint.getFontMetricsInt();
            float baseline = (float) (y - (fmi.bottom / 2.0 + fmi.top / 2.0));
            canvas.drawText(mText, x, baseline, mPaint);
            if (!mThread.isAlive()) {
                mThread.start();
            }
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                invalidate();
            }
        }
    };

    private Thread mThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (mMoveLen < mViewWidth * 1.5) {
                try {
                    Thread.sleep(MOVETIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mMoveLen += MOVELEN;
                handler.sendEmptyMessage(0);
                if (mMoveLen == mViewWidth * 3 / 2) {
                    mMoveLen = -mViewWidth / 2;
                }
            }
        }
    });

    /**
     * 停止滚动
     */
    public void stopScroll()
    {
        isStarting = false;
        invalidate();
    }

    private int dip2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}