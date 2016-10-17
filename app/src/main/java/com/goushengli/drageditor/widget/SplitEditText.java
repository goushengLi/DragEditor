package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by goushengLi on 2016/10/17.
 */

public class SplitEditText extends EditText {
    private Context mContext;

    private int mViewWidth, mTextHeight, mPaddingLeft, mPaddingRight, mContentWidth;

    private Paint mPaint;

    public SplitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCursorVisible(false);
        mContext = context;
        //获取View的画笔
        mPaint = getPaint();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        //获得左右内边距
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        //获取EditText的宽度
        mViewWidth = getWidth();
        //计算出EditText实际的内容宽度
        mContentWidth = mViewWidth - mPaddingLeft - mPaddingRight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取当前EditText的文字内容(包括目前输入的内容)
        String inputStr = getText().toString().trim();
        //循环遍历,通过将一个字符的宽度累加起来,没累加一个字符串长度,就判断是否大于当前mTextWidth-(mPaddingLeft+mPaddingRight)
        //如果大于当前的宽度,则绘制到下一行,并且将之前的文字添加到List中
        int tempLineWidth = 0;
        int tempLineCount = 0;
        for (int chIndex = 0; chIndex < inputStr.length(); chIndex++) {
            char ch = inputStr.charAt(chIndex);
            String str = String.valueOf(ch);
            float strWidth = 0;
            if (!str.isEmpty())
                strWidth = getWidthOfString(str, mPaint);

            tempLineWidth += Math.ceil(strWidth);
            //如果当前的输入的内容累加已经大于一行的宽度了,那么就需要行数加一
            if (tempLineWidth >= mContentWidth) {
                tempLineCount = tempLineCount + 1;


                continue;
            }else {
                continue;
            }


        }


    }

    public static int getWidthOfString(String str, Paint paint) {
        if (str != null && !str.equals("") && paint != null) {
            int strLength = str.length();
            int result = 0;
            float[] widths = new float[strLength];
            paint.getTextWidths(str, widths);
            for (int i = 0; i < strLength; i++) {
                result += widths[i];
            }
            return result;
        }
        return 0;
    }
}
