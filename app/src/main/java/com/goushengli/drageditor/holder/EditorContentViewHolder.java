package com.goushengli.drageditor.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.helper.ItemTouchHelperViewHolder;


/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class EditorContentViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    public TextView textView;
    public Button mBTDrag;

    public EditorContentViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.editor_fm_content_tv);
        mBTDrag = (Button) itemView.findViewById(R.id.editor_fm_drag_bt);
    }

    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}
