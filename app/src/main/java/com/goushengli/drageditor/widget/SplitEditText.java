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

    private StringBuilder mLineContentBulider;

    private int mTextHeight, mViewWidth, mPaddingLeft, mPaddingRight, mContentWidth;

    private List<LinePar> mLineParList;

    public SplitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCursorVisible(false);
        mContext = context;
        mPaint = getPaint();
        mLineContentBulider = new StringBuilder();
        mLineParList = new ArrayList<>();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mViewWidth = getWidth();
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mContentWidth = mViewWidth - (mPaddingLeft + mPaddingRight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String inputStr = getText().toString().trim();
        int tempLineWidth = 0;
        int tempLineCount = 0;

        for (int i = 0; i < inputStr.length(); i++) {
            String character = String.valueOf(inputStr.charAt(i));
            float characterWidth = getWidthOfString(character, mPaint);
            tempLineWidth += characterWidth;
            if (tempLineWidth > mContentWidth) {
                tempLineCount++;
                String lineContent = mLineContentBulider.toString();

                mLineContentBulider.delete(0, mLineContentBulider.length());
                tempLineWidth = 0;

                addLineParToList(lineContent, tempLineCount, mLineParList);
                mLineContentBulider.append(character);
            } else {
                mLineContentBulider.append(character);
            }

        }

        drawText(mLineParList, canvas);

    }

    private void drawText(List<LinePar> mLineParList, Canvas canvas) {
        Log.d("TAG", "mLineParList " + mLineParList);
        for (int i = 0; i < mLineParList.size(); i++) {
            canvas.drawText(mLineParList.get(i).getLineContent(), mPaddingLeft * 1f, 0, mPaint);
        }

    }


    private void addLineParToList(String lineContent, int lineCount, List<LinePar> lineParList) {
        LinePar child = new LinePar();
        child.setLineContent(lineContent);
        child.setLineCount(lineCount);
        lineParList.add(child);
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
