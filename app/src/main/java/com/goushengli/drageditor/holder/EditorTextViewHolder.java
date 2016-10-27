package com.goushengli.drageditor.holder;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.dao.EditorContent;
import com.goushengli.drageditor.widget.SplitByLineEditText;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class EditorTextViewHolder extends RecyclerView.ViewHolder {
    private int mPosition;

    public List<EditorContent> mDataList;

    public SplitByLineEditText mETText;

    public EditorTextViewHolder(View itemView, List<EditorContent> dataList) {
        super(itemView);
        this.mDataList = dataList;
        mETText = (SplitByLineEditText) itemView.findViewById(R.id.editor_item_text_et);
        mETText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mDataList.get(mPosition).setLineContentList(mETText.obtainLineContent());
            }
        });
    }

    public void setPosition(int position) {
        Log.d("TAG", "position = " + position);
        this.mPosition = position;
        obtainLineContent();
    }

    public void obtainLineContent() {

        mETText.post(new Runnable() {
            @Override
            public void run() {
                mDataList.get(mPosition).setLineContentList(mETText.obtainLineContent());
            }
        });
    }


}
