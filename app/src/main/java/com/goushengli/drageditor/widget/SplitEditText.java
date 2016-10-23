package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.lang.UCharacter;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.style.TtsSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.goushengli.drageditor.dao.LinePar;
import com.goushengli.drageditor.util.DensityUtil;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * 按行切割EditText
 * Created by Administrator on 2016/10/18 0018.
 */

public class SplitEditText extends EditText {
    private float LINE_SPACE_INCREASE;

    private Paint mPaint;

    private StringBuilder mLineContentBuilder;

    private int mPaddingLeft, mPaddingTop, mPaddingBottom, mContentWidth;

    private List<LinePar> mLineParList;
    private float mLineSpace, mTextHeight, mSpaceExtra;

    public SplitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setTextColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= 16) {
            mSpaceExtra = getLineSpacingExtra();
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
//        mPaint.setColor(getPaint().getColor());
        mPaint.setTextSize(getPaint().getTextSize());
        mLineContentBuilder = new StringBuilder();
        mLineParList = new ArrayList<>();

        mLineSpace = mSpaceExtra;
        LINE_SPACE_INCREASE = mSpaceExtra;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mTextHeight = getTextSize();
        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();
        mContentWidth = getWidth() - (mPaddingLeft + getPaddingRight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
            if (character.equals("\n")) {
                lineCount++;
                lineWidth = 0;
                mLineParList.get(mLineParList.size() - 1).setFinishLine(true);
                mLineContentBuilder.delete(0, mLineContentBuilder.length());
                lineWidth += characterWidth;
                appendCharToLine(lineCount, "");
                continue;
            }

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

        if (lineParList.size() == 0) {

            createLinParAndAddToList(lineContent, lineCount, lineParList);

        } else {
            LinePar linePar = lineParList.get(lineParList.size() - 1);
            if (linePar.isFinishLine())
                createLinParAndAddToList(lineContent, lineCount, lineParList);
            else
                linePar.setLineContent(lineContent);
        }

    }

    private void createLinParAndAddToList(String lineContent, int lineCount, List<LinePar> lineParList) {
        LinePar linePar = new LinePar();
        linePar.setLineContent(lineContent);
        linePar.setLineCount(lineCount);
        lineParList.add(linePar);
    }

    private void drawText(List<LinePar> mLineParList, Canvas canvas) {
        float lineSpace = mLineSpace;
        for (int i = 0; i < mLineParList.size(); i++) {
            LinePar child = mLineParList.get(i);
            canvas.drawText(child.getLineContent(), mPaddingLeft, mTextHeight * (child.getLineCount() + 1) + lineSpace, mPaint);
            lineSpace += LINE_SPACE_INCREASE * 2;
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
