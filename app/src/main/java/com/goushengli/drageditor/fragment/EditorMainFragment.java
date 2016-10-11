package com.goushengli.drageditor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.adapter.EditorContentRecycleAdapter;
import com.goushengli.drageditor.base.BaseFragment;
import com.goushengli.drageditor.helper.ItemTouchHelperCallback;
import com.goushengli.drageditor.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goushengLi on 2016/10/10.
 */
public class EditorMainFragment extends BaseFragment implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;

    private RecyclerView mRecycleView;

    public static EditorMainFragment newInstance() {
        EditorMainFragment editorMainFragment = new EditorMainFragment();
        return editorMainFragment;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editor_fm_content, null);
        mRecycleView = (RecyclerView) view.findViewById(R.id.editor_fm_content_recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void initData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(i + "");
        }
        EditorContentRecycleAdapter recycleAdapter = new EditorContentRecycleAdapter(getContext(), dataList, this);
        mRecycleView.setAdapter(recycleAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(recycleAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecycleView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
