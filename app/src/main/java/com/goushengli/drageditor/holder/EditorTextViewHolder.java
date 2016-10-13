package com.goushengli.drageditor.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.goushengli.drageditor.R;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class EditorTextViewHolder extends RecyclerView.ViewHolder {

    public EditText mETText;

    public EditorTextViewHolder(View itemView) {
        super(itemView);
        mETText = (EditText) itemView.findViewById(R.id.editor_item_text_et);
    }

}
