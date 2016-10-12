package com.goushengli.drageditor.fragment;

import android.graphics.BitmapFactory;
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
import com.goushengli.drageditor.dao.EditorContent;
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
        List<EditorContent> dataList = new ArrayList<>();

        EditorContent textContent = new EditorContent();
        textContent.setType(EditorContent.TEXT_CONTENT);
        textContent.setTextContent("我是一个文本,哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");

        EditorContent imageContent = new EditorContent();
        imageContent.setType(EditorContent.IMAGE_CONTENT);
        imageContent.setImageContent(BitmapFactory.decodeResource(getResources(), R.drawable.kobe));

        dataList.add(textContent);
        dataList.add(imageContent);

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
