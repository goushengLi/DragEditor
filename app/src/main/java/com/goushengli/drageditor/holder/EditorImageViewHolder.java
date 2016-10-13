package com.goushengli.drageditor.holder;

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.goushengli.drageditor.R;
import com.goushengli.drageditor.fragment.EditorMainFragment;
import com.goushengli.drageditor.helper.ItemTouchHelperViewHolder;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class EditorImageViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private final int COMPRESS_ANIM_DURATION = 250;
    private int mImageItemOriginalHeight;

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
        //1.准备开始拖动,将图片item压缩至
        compressImageItm();
    }

    /**
     * 将图片item的高度压缩至IMAGE_ITEM_SMALL_SIZE
     */
    private void compressImageItm() {
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRLContent.getLayoutParams();
        //将图片item原本的高度存储起来
        mImageItemOriginalHeight = mRLContent.getHeight();
        ValueAnimator toSmallSizeAnim = ValueAnimator.ofInt(mImageItemOriginalHeight, EditorMainFragment.IMAGE_ITEM_SMALL_SIZE);
        toSmallSizeAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int height = (int) valueAnimator.getAnimatedValue();
                layoutParams.height = height;
                mRLContent.setLayoutParams(layoutParams);
            }
        });
        toSmallSizeAnim.setDuration(COMPRESS_ANIM_DURATION);
        toSmallSizeAnim.start();
    }

    @Override
    public void onItemRelease() {
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRLContent.getLayoutParams();
        ValueAnimator toOriginalHeightAnim = ValueAnimator.ofInt(EditorMainFragment.IMAGE_ITEM_SMALL_SIZE, mImageItemOriginalHeight);
        toOriginalHeightAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int height = (int) valueAnimator.getAnimatedValue();
                layoutParams.height = height;
                mRLContent.setLayoutParams(layoutParams);
            }
        });
        toOriginalHeightAnim.setDuration(COMPRESS_ANIM_DURATION);
        toOriginalHeightAnim.start();
    }


}
