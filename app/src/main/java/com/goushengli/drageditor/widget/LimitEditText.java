package com.goushengli.drageditor.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;


/**
 * 限制字体输入的EditText
 * Created by goushengLi on 2016/10/10.
 */

public class LimitEditText extends EditText {

    public LimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() % 17 == 0) {
                    editable.append('\n');
                }
            }
        });
    }
}
