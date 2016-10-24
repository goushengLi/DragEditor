package com.goushengli.drageditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.goushengli.drageditor.util.DensityUtil;
import com.goushengli.drageditor.widget.SplitByLineEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int screenWidth;
    private SplitByLineEditText main_line_et;

    private List<String> lineList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenWidth = DensityUtil.getScreenWidthPx(getWindowManager());
        main_line_et = (SplitByLineEditText) findViewById(R.id.mainline_et);

        //初始化图片内容Fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager
//                .beginTransaction()
//                .add(R.id.main_editor_content_fl, EditorMainFragment.newInstance())
//                .commit();

    }

    public void onClick(View view) {
        Toast.makeText(MainActivity.this, main_line_et.obtainLineContent() + "", Toast.LENGTH_LONG).show();
    }
}
