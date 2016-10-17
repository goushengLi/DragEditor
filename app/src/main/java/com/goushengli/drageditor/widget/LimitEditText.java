package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.goushengli.drageditor.MainActivity;
import com.goushengli.drageditor.util.DensityUtil;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_DEL;


/**
 * 限制字体输入的EditText
 * Created by goushengLi on 2016/10/10.
 */

public class LimitEditText extends EditText {

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("TAG", "what the fuck");
    }

    public LimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        final int padding = getPaddingLeft();
        final int textRectWidth = DensityUtil.dip2px(context, 330 - 16);//471
        Log.d("TAG", "textRectWidth = " + textRectWidth);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //在输入完成的时候获取当前文字的长度
                int textLength = (int) getPaint().measureText(editable.toString());
                Log.d("TAG", "textLength = " + textLength);
                //如何判断什么时候需要加入换行符
                int remainder = textLength % textRectWidth;
                Log.d("TAG", "remainder = " + remainder);

            }

        });

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == ACTION_DOWN)
                    switch (keyEvent.getKeyCode()) {
                        case KEYCODE_DEL://这里可以监听到删除按键
                            Log.d("TAG", "KEYCODE_DEL");
                            break;
                    }
                return false;
            }
        });
    }
}
