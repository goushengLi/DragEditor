package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.goushengli.drageditor.dao.LinePar;

import java.util.ArrayList;
import java.util.List;

/**
 * 按行切割EditText
 * Created by Administrator on 2016/10/18 0018.
 */

public class SplitEditText extends EditText {

    private Paint mPaint;

    private StringBuilder mLineContentBuilder;

    private int mTextHeight, mPaddingLeft, mContentWidth;

    private List<LinePar> mLineParList;

    public SplitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = getPaint();
        mLineContentBuilder = new StringBuilder();
        mLineParList = new ArrayList<>();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mTextHeight = (int) getTextSize();
        mPaddingLeft = getPaddingLeft();
        mContentWidth = getWidth() - (mPaddingLeft + getPaddingRight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String inputContent = getText().toString();

        int lineWidth = 0;
        int lineCount = 0;

        initSourceData(mLineParList, mLineContentBuilder);

        splitInputToListByLength(inputContent, lineWidth, lineCount);

        drawText(mLineParList, canvas);

    }

    private void splitInputToListByLength(String inputContent, int lineWidth, int lineCount) {

        for (int i = 0; i < inputContent.length(); i++) {
            String character = String.valueOf(inputContent.charAt(i));
            float characterWidth = getWidthOfString(character, mPaint);
            lineWidth += characterWidth;
            if (lineWidth > mContentWidth) {
                lineCount++;
                lineWidth = 0;
                mLineParList.get(mLineParList.size() - 1).setFinishLine(true);
                mLineContentBuilder.delete(0, mLineContentBuilder.length());
                lineWidth += characterWidth;
                appendCharToLine(lineCount, character);
            } else {
                appendCharToLine(lineCount, character);
            }

        }
    }

    private void appendCharToLine(int lineCount, String character) {
        mLineContentBuilder.append(character);
        addLineParToList(mLineContentBuilder.toString(), lineCount, mLineParList);
    }

    private void initSourceData(List<LinePar> linePars, StringBuilder contentBuilder) {
        linePars.clear();
        contentBuilder.delete(0, contentBuilder.length());
    }

    private void addLineParToList(String lineContent, int lineCount, List<LinePar> lineParList) {

        if (lineParList.size() == 0) {//证明是第一行

            createLinParAndAddToList(lineContent, lineCount, lineParList);

        } else {
            LinePar linePar = lineParList.get(lineParList.size() - 1);
            if (linePar.isFinishLine())
                createLinParAndAddToList(lineContent, lineCount, lineParList);
            else
                linePar.setLineContent(lineContent);
        }

//        try {
//            if (lineParList.get(lineParList.size() - 1).isFinishLine()) {
//                LinePar child = new LinePar();
//                child.setLineContent(lineContent);
//                child.setLineCount(lineCount);
//                lineParList.add(child);
//            } else {
//                LinePar child = lineParList.get(lineParList.size() - 1);
//                child.setLineContent(lineContent);
//            }
//        } catch (Exception e) {
//            LinePar child = new LinePar();
//            child.setLineContent(lineContent);
//            child.setLineCount(lineCount);
//            lineParList.add(child);
//        }

    }

    private void createLinParAndAddToList(String lineContent, int lineCount, List<LinePar> lineParList) {
        LinePar linePar = new LinePar();
        linePar.setLineContent(lineContent);
        linePar.setLineCount(lineCount);
        lineParList.add(linePar);
    }

    private void drawText(List<LinePar> mLineParList, Canvas canvas) {
        for (int i = 0; i < mLineParList.size(); i++) {
            LinePar child = mLineParList.get(i);
            canvas.drawText(child.getLineContent(), mPaddingLeft * 1f, mTextHeight * (child.getLineCount() + 1), mPaint);
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
