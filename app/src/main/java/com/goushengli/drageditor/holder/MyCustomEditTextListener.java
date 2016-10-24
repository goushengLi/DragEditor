package com.goushengli.drageditor.holder;

import android.text.Editable;
import android.text.TextWatcher;

import com.goushengli.drageditor.dao.EditorContent;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class MyCustomEditTextListener implements TextWatcher {
    private int mPosition;
    private List<EditorContent> mDataList;

    public MyCustomEditTextListener(List<EditorContent> dataList) {
        this.mDataList = dataList;
    }

    public void updatePosition(int position) {
        this.mPosition = position;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mDataList.get(mPosition).getType() == EditorContent.TEXT_CONTENT) {
            mDataList.get(mPosition).getLineContentList().clear();
        }
    }
}
