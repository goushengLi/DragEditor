package com.goushengli.drageditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.goushengli.drageditor.util.DensityUtil;

public class MainActivity extends AppCompatActivity {
    public static int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenWidth = DensityUtil.getScreenWidthPx(getWindowManager());
        //初始化图片内容Fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager
//                .beginTransaction()
//                .add(R.id.main_editor_content_fl, EditorMainFragment.newInstance())
//                .commit();

    }
}
