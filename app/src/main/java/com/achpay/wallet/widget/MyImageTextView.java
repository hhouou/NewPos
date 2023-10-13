package com.achpay.wallet.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.achpay.wallet.R;

/**
 * Created by hy on 2018/2/24.
 */

public class MyImageTextView extends LinearLayout {

    private ImageView mImageView = null;
    private TextView mTextView = null;
    private int imageId, pressImageId;
    private int  textColorId, textTopId, pressTextColorId;
    private String textId;
    private float textSize=11f;

    public MyImageTextView(Context context) {
        this(context, null);
    }

    public MyImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(LinearLayout.VERTICAL);//设置垂直排序
        this.setGravity(Gravity.CENTER);//设置居中
        if (mImageView == null) {
            mImageView = new ImageView(context);
        }
        if (mTextView == null) {
            mTextView = new TextView(context);
        }
        if (attrs == null)
            return;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.imageText, 0, 0);

        textId= a.getString(R.styleable.imageText_text);
        imageId=a.getResourceId(R.styleable.imageText_image,0);
        pressImageId=a.getResourceId(R.styleable.imageText_pressImage,0);
        textColorId=a.getResourceId(R.styleable.imageText_textColor,0);
        textTopId=a.getResourceId(R.styleable.imageText_textTop,0);
        pressTextColorId=a.getResourceId(R.styleable.imageText_pressTextColor,0);


        init();

    }

    /**
     * 初始化状态
     */
    private void init() {
        this.setText(textId);
        mTextView.setGravity(Gravity.CENTER);//字体居中
        setTextSize(textSize);
        this.setTextColor(textColorId);
        this.setTextPaddingTop(textTopId);
        this.setImgResource(imageId);
        addView(mImageView);//将图片加入
        addView(mTextView);//将文字加入
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            //按下
            case MotionEvent.ACTION_DOWN:
                if (pressImageId != 0)
                    this.setImgResource(pressImageId);
                if (pressTextColorId != 0)
                    this.setTextColor(pressTextColorId);
                break;
            //移动
            case MotionEvent.ACTION_MOVE:
                break;
            //抬起
            case MotionEvent.ACTION_UP:
                if (imageId != 0)
                    this.setImgResource(imageId);
                if (textColorId != 0)
                    this.setTextColor(textColorId);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置默认的图片
     *
     * @param resourceID 图片id
     */
    public void setImgResourceDefault(int resourceID) {
        imageId = resourceID;
        setImgResource(resourceID);
    }

    /**
     * 设置按下的图片
     *
     * @param resourceID 图片id
     */
    public void setImgResourcePress(int resourceID) {
        pressImageId = resourceID;
    }


    /**
     * 设置显示的图片
     *
     * @param resourceID 图片ID
     */
    private void setImgResource(int resourceID) {
        if (resourceID == 0) {
            this.mImageView.setImageResource(0);
        } else {
            this.mImageView.setImageResource(resourceID);
        }
    }


    /**
     * 设置显示的文字
     *
     * @param text
     */
    public void setText(String text) {
        this.mTextView.setText(text);
    }

    /**
     * 设置字体颜色(默认为黑色)
     *
     * @param color
     */
    private void setTextColor(int color) {
        if (color == 0) {
            this.mTextView.setTextColor(Color.BLACK);
        } else {
            this.mTextView.setTextColor(getResources().getColor(color));
        }
    }

    /**
     * 设置默认的颜色
     *
     * @param color 颜色ID
     */
    public void setTextDefaultColor(int color) {
        textColorId = color;
        setTextColor(color);
    }

    /**
     * 设置按下的颜色
     *
     * @param color 颜色ID
     */
    public void setTextPressColor(int color) {
        pressImageId = color;
    }

    /**
     * 设置字体大小
     *
     * @param size
     */
    public void setTextSize(float size) {
        this.mTextView.setTextSize(size);
    }


    /**
     * 设置文字与上面的距离
     * @param top
     */
    public void setTextPaddingTop(int top) {
        if (top != 0)
            this.mTextView.setPadding(0, getResources().getDimensionPixelOffset(top), 0, 0);
    }


}
