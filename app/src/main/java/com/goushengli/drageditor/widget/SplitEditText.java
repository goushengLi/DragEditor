package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.goushengli.drageditor.dao.LinePar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class SplitEditText extends EditText {

    private Context mContext;

    private Paint mPaint;

    private StringBuilder mLineContentBuilder;

    private int mTextHeight, mViewWidth, mPaddingLeft, mPaddingRight, mContentWidth, tempLineWidth, tempLineCount;

    private List<LinePar> mLineParList;

    public SplitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCursorVisible(false);
        mContext = context;
        mPaint = getPaint();
        mLineContentBuilder = new StringBuilder();
        mLineParList = new ArrayList<>();
        tempLineWidth = 0;
        tempLineCount = 0;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mViewWidth = getWidth();
        mTextHeight = (int) getTextSize();
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mContentWidth = mViewWidth - (mPaddingLeft + mPaddingRight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String inputStr = getText().toString().trim();

        for (int i = 0; i < inputStr.length(); i++) {
            String character = String.valueOf(inputStr.charAt(i));
            float characterWidth = getWidthOfString(character, mPaint);
            tempLineWidth += characterWidth;
            if (tempLineWidth > mContentWidth) {
                tempLineCount++;
                String lineContent = mLineContentBuilder.toString();

                mLineContentBuilder.delete(0, mLineContentBuilder.length());
                tempLineWidth = 0;

                addLineParToList(lineContent, tempLineCount, mLineParList);

                mLineContentBuilder.append(character);

            } else {
                mLineContentBuilder.append(character);
                addLineParToList(mLineContentBuilder.toString(), tempLineCount, mLineParList);
            }

        }

        drawText(mLineParList, canvas);

    }

    private void drawText(List<LinePar> mLineParList, Canvas canvas) {
        for (int i = 0; i < mLineParList.size(); i++) {
            LinePar child = mLineParList.get(i);
            canvas.drawText(child.getLineContent(), mPaddingLeft * 1f, mTextHeight * (child.getLineCount()), mPaint);
        }

    }


    private void addLineParToList(String lineContent, int lineCount, List<LinePar> lineParList) {
        try {
            if (lineParList.get(lineParList.size()).isFinishLine()) {
                LinePar child = new LinePar();
                child.setLineContent(lineContent);
                child.setLineCount(lineCount);
                lineParList.add(child);
            } else {
                lineParList.get(lineParList.size()).setLineContent(lineContent);
            }
        } catch (Exception e) {
            LinePar child = new LinePar();
            child.setLineContent(lineContent);
            child.setLineCount(lineCount);
            lineParList.add(child);
        }

    }

    public int getWidthOfString(String str, Paint paint) {

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
