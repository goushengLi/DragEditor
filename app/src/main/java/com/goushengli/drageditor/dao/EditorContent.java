package com.goushengli.drageditor.dao;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class EditorContent {
    public static final int IMAGE_CONTENT = 0;
    public static final int TEXT_CONTENT = 1;

    private int type;
    private String textContent;
    private Bitmap imageContent;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Bitmap getImageContent() {
        return imageContent;
    }

    public void setImageContent(Bitmap imageContent) {
        this.imageContent = imageContent;
    }
}
