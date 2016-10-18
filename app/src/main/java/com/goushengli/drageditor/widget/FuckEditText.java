package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 按行切割EditText
 * Created by Administrator on 2016/10/17 0017.
 */
public class FuckEditText extends EditText {

    private Context mContext = null;

    private int mTextHeight, mTextWidth, mFontHeight;

    private float mTextSize;

    private String mText = "";

    public FuckEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mTextWidth = this.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextHeight = getHeight();
        mText = getText().toString().trim();
        if (mText == null || mText.equals("") == true) {
            return;
        }
        mTextSize = this.getTextSize();
        mFontHeight = (int) mTextSize;

    }
}
