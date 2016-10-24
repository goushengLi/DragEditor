package com.goushengli.drageditor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * 按行切割的EditText
 * Created by Administrator on 2016/10/24 0024.
 */

public class SplitByLineEditText extends EditText {

    private List<String> mLineContentList;

    public SplitByLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLineContentList = new ArrayList<>();
    }

    public List<String> obtainLineContent() {
        mLineContentList.clear();
        String inputContent = getText().toString();
        int lineFirstCharIndex = 0;
        for (int i = 0; i < getLineCount(); i++) {
            int lineEndCharIndex = getCharNum(i);
            String lineContent = inputContent.substring(lineFirstCharIndex, lineEndCharIndex);
            mLineContentList.add(lineContent);
            lineFirstCharIndex = lineEndCharIndex;
        }
        return mLineContentList;
    }

    private int getCharNum(int lineNum) {
        return getLayout().getLineEnd(lineNum);
    }

}

