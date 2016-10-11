package com.goushengli.drageditor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.holder.EditorContentViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class EditorContentRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<String> mDataList;

    public EditorContentRecycleAdapter(Context context, List<String> dataList) {
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    @Override
    public EditorContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditorContentViewHolder(mLayoutInflater.inflate(R.layout.editor_fm_content_rv_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EditorContentViewHolder) {
            ((EditorContentViewHolder) holder).textView.setText(mDataList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
