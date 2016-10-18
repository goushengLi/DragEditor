package com.goushengli.drageditor.dao;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class LinePar {
    String lineContent;
    int lineCount;
    boolean finishLine;

    public boolean isFinishLine() {
        return finishLine;
    }

    public void setFinishLine(boolean finishLine) {
        this.finishLine = finishLine;
    }

    public String getLineContent() {
        return lineContent;
    }

    public void setLineContent(String lineContent) {
        this.lineContent = lineContent;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    @Override
    public String toString() {
        return "LinePar{" +
                "lineContent='" + lineContent + '\'' +
                ", lineCount=" + lineCount +
                '}';
    }
}
