package com.goushengli.drageditor.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.helper.ItemTouchHelperViewHolder;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class EditorTextViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    public EditText mETText;

    public EditorTextViewHolder(View itemView) {
        super(itemView);
        mETText = (EditText) itemView.findViewById(R.id.editor_item_text_et);
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}
