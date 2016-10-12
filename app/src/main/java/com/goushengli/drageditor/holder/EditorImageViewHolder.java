package com.goushengli.drageditor.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.helper.ItemTouchHelperViewHolder;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class EditorImageViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    public RelativeLayout mRLContent;
    public ImageView mIVImage, mIVHandle;
    public EditText mETContent;

    public EditorImageViewHolder(View itemView) {
        super(itemView);
        mRLContent = (RelativeLayout) itemView.findViewById(R.id.editor_item_content_rl);
        mIVImage = (ImageView) itemView.findViewById(R.id.editor_item_image_iv);
        mIVHandle = (ImageView) itemView.findViewById(R.id.editor_item_handle_iv);
        mETContent = (EditText) itemView.findViewById(R.id.editor_item_image_content_et);
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}
