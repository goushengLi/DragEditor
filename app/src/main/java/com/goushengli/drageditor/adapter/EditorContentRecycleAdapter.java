package com.goushengli.drageditor.adapter;

import android.content.Context;
import android.support.annotation.BoolRes;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.callback.IBeganToDrag;
import com.goushengli.drageditor.dao.EditorContent;
import com.goushengli.drageditor.helper.ItemTouchHelperAdapter;
import com.goushengli.drageditor.helper.OnStartDragListener;
import com.goushengli.drageditor.holder.EditorImageViewHolder;
import com.goushengli.drageditor.holder.EditorTextViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RecycleView适配器
 * Created by Administrator on 2016/10/11 0011.
 */

public class EditorContentRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter, IBeganToDrag {
    private LayoutInflater mLayoutInflater;

    private List<EditorContent> mDataList;
    private List<EditorContent> mTemporaryList;

    private final OnStartDragListener mDragStartListener;

    private final Context mContext;

    public EditorContentRecycleAdapter(Context context, List<EditorContent> dataList, OnStartDragListener dragStartListener) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mDataList = dataList;
        mTemporaryList = new ArrayList<>();
        mDragStartListener = dragStartListener;
    }


    @Override
    public void onImageItemDrag(int position) {
        splitAndResetData(position);
    }

    private void splitAndResetData(int position) {
        //1.遍历mDataList,查找出属于文本内容的item
        for (int i = 0; i < mDataList.size(); i++) {
            EditorContent editorContent = mDataList.get(i);
            if (editorContent.getType() == EditorContent.TEXT_CONTENT) {
                //2.找到对应的EditText并且按行切割,并将切割出来的子项文本分别按序号放进mTemporaryList中
                for (int j = 0; j < editorContent.getLineContentList().size(); j++) {
                    EditorContent tempObject = new EditorContent();
                    tempObject.setType(EditorContent.TEXT_CONTENT);
                    tempObject.setTextContent(editorContent.getLineContentList().get(j));
                    mTemporaryList.add(tempObject);
                }
            } else {
                mTemporaryList.add(editorContent);
            }
        }

        //3.根据获得到的mTemporaryList来更新RecycleView
        mDataList.clear();
        mDataList.addAll(mTemporaryList);
        mTemporaryList.clear();
        /**
         * 只有放在第一位的时候没有错位,这个是因为分割之后,数据List发生了变化,所以item的index
         * 也就变化了,而image放在第一位,那么它的index是不会变化的,那么就不存在错位的情况了
         */
        try {
            /**
             * 这里只需要刷新屏幕里面看得到的item,因为在屏幕上显示的item
             * 无法根据mDataList的变化而动态适配,所以需要我们手动来刷新
             */
            notifyItemChanged(position - 3);
            notifyItemChanged(position - 2);
            notifyItemChanged(position - 1);
            notifyItemChanged(position + 1);
            notifyItemChanged(position + 2);
            notifyItemChanged(position + 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onImageItemRelease() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mDataList.size(); i++) {
            EditorContent editorContent = mDataList.get(i);
            if (editorContent.getType() == EditorContent.TEXT_CONTENT) {
                stringBuilder.append(editorContent.getTextContent());
                if (i == 0) {
                    EditorContent tempObject = new EditorContent();
                    tempObject.setType(EditorContent.TEXT_CONTENT);
                    tempObject.setTextContent(stringBuilder.toString());
                    mTemporaryList.add(tempObject);
                } else {
                    EditorContent child = mTemporaryList.get(mTemporaryList.size() - 1);

                    if (child.getType() == EditorContent.IMAGE_CONTENT) {
                        EditorContent tempObject = new EditorContent();
                        tempObject.setType(EditorContent.TEXT_CONTENT);
                        tempObject.setTextContent(stringBuilder.toString());
                        mTemporaryList.add(tempObject);
                    } else {
                        child.setTextContent(stringBuilder.toString());
                    }
                }

            } else {
                stringBuilder.delete(0, stringBuilder.length());
                mTemporaryList.add(editorContent);
            }
        }

        mDataList.clear();
        mDataList.addAll(mTemporaryList);
        mTemporaryList.clear();

        notifyDataSetChanged();

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
            return new EditorImageViewHolder(mLayoutInflater.inflate(R.layout.editor_item_rv_image, null), this);
        } else {
            return new EditorTextViewHolder(mLayoutInflater.inflate(R.layout.editor_item_rv_text, null), mDataList);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof EditorImageViewHolder) {
            /**
             * 我们在touch的时候拆解数据,重新布局,然后在long press中启动拖拽
             */
            ((EditorImageViewHolder) holder).mIVHandle.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            Log.d("TAG", "ACTION_DOWN");
                            splitAndResetData(position);
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d("TAG", "ACTION_UP");
                            break;
                    }
                    return false;
                }
            });

            ((EditorImageViewHolder) holder).mIVHandle.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mDragStartListener.onStartDrag(holder);
                    return false;
                }
            });
            ((EditorImageViewHolder) holder).setPosition(position);
            ((EditorImageViewHolder) holder).mIVImage.setImageBitmap(mDataList.get(position).getImageContent());
        } else {
            ((EditorTextViewHolder) holder).mETText.setText(mDataList.get(position).getTextContent());
            ((EditorTextViewHolder) holder).setPosition(position);
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
