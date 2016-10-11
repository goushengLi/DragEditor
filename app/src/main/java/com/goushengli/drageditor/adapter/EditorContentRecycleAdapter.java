package com.goushengli.drageditor.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.helper.ItemTouchHelperAdapter;
import com.goushengli.drageditor.helper.OnStartDragListener;
import com.goushengli.drageditor.holder.EditorContentViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class EditorContentRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {
    private LayoutInflater mLayoutInflater;

    private List<String> mDataList;

    private final OnStartDragListener mDragStartListener;

    private final Context mContext;

    public EditorContentRecycleAdapter(Context context, List<String> dataList, OnStartDragListener dragStartListener) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mDataList = dataList;
        mDragStartListener = dragStartListener;
    }

    @Override
    public EditorContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditorContentViewHolder(mLayoutInflater.inflate(R.layout.editor_fm_content_rv_item, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((EditorContentViewHolder) holder).textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

        ((EditorContentViewHolder) holder).textView.setText(mDataList.get(position));
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
