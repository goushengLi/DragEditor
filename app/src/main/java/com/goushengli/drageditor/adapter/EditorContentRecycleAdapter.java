package com.goushengli.drageditor.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.dao.EditorContent;
import com.goushengli.drageditor.helper.ItemTouchHelperAdapter;
import com.goushengli.drageditor.helper.OnStartDragListener;
import com.goushengli.drageditor.holder.EditorImageViewHolder;
import com.goushengli.drageditor.holder.EditorTextViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class EditorContentRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {
    private LayoutInflater mLayoutInflater;

    private List<EditorContent> mDataList;

    private final OnStartDragListener mDragStartListener;

    private final Context mContext;

    public EditorContentRecycleAdapter(Context context, List<EditorContent> dataList, OnStartDragListener dragStartListener) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mDataList = dataList;
        mDragStartListener = dragStartListener;
    }

    private enum ITEM_TYPE {
        IMAGE_CONTENT_ITEM,
        TEXT_CONTENT_ITEM
    }

    @Override
    public int getItemViewType(int position) {

        switch (mDataList.get(position).getType()) {
            case EditorContent.TEXT_CONTENT:
                return ITEM_TYPE.TEXT_CONTENT_ITEM.ordinal();
            case EditorContent.IMAGE_CONTENT:
                return ITEM_TYPE.IMAGE_CONTENT_ITEM.ordinal();
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.IMAGE_CONTENT_ITEM.ordinal()) {
            return new EditorImageViewHolder(mLayoutInflater.inflate(R.layout.editor_item_rv_image, null));
        } else {
            return new EditorTextViewHolder(mLayoutInflater.inflate(R.layout.editor_item_rv_text, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EditorImageViewHolder) {
            ((EditorImageViewHolder) holder).mIVHandle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mDragStartListener.onStartDrag(holder);
                    }
                    return false;
                }
            });
            ((EditorImageViewHolder) holder).mIVImage.setImageBitmap(mDataList.get(position).getImageContent());
        } else {
            ((EditorTextViewHolder) holder).mETText.setText(mDataList.get(position).getTextContent());
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mDataList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
    }
}
