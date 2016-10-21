package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */

public class DetectionLengthEditText extends EditText {

    private Paint mPaint;

    private int mContentWidth;

    private List<String> mLineStringList;

    private StringBuilder mStringBuilder;

    public DetectionLengthEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = getPaint();
        mLineStringList = new ArrayList<>();
        mStringBuilder = new StringBuilder();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mContentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLineStringList.clear();
        mStringBuilder.delete(0, mStringBuilder.length());
        String inputContent = getText().toString();
        int lineWidth = 0;
        for (int i = 0; i < inputContent.length(); i++) {
            String character = String.valueOf(inputContent.charAt(i));
            lineWidth += getWidthOfString(character, mPaint);
            if (lineWidth > mContentWidth) {
                mLineStringList.add(mStringBuilder.toString());
                lineWidth = 0;
                mStringBuilder.delete(0, mStringBuilder.length());
                mStringBuilder.append(character);
            } else {
                mStringBuilder.append(character);
                if (mLineStringList.size() > 0) {
                    int lastIndex = mLineStringList.size() - 1;
                    mLineStringList.remove(lastIndex);
                    mLineStringList.add(lastIndex, mStringBuilder.toString());
                } else {
                    mLineStringList.add(mStringBuilder.toString());
                }
            }

        }

        Log.d("TAG", "mLineStringList = " + mLineStringList);
    }

    private int getWidthOfString(String str, Paint paint) {
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
