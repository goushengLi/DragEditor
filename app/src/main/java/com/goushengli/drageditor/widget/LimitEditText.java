package com.goushengli.drageditor.widget;

import android.content.Context;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.goushengli.drageditor.util.DensityUtil;


/**
 * 限制字体输入的EditText
 * Created by goushengLi on 2016/10/10.
 */

public class LimitEditText extends EditText {

    public LimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        final Paint paint = new Paint();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                Log.d("TAG", "paint.measureText(editable.toString()) = " + paint.measureText(editable.toString()));
            }

        });
    }
}
