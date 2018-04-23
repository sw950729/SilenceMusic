package com.silence.music;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.angel.music.R;


/**
 * @author Silence
 * @date 2018/3/21.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fl_title_menu;
    private RadioGroup rg_home_viewpager_contorl;
    private DrawerLayout dl_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
    }

    private void initial() {
        fl_title_menu = (FrameLayout) findViewById(R.id.fl_title_menu);
        rg_home_viewpager_contorl = (RadioGroup) findViewById(R.id.rg_home_viewpager_contorl);
        dl_layout= (DrawerLayout) findViewById(R.id.dl_layout);
        fl_title_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_title_menu:
                dl_layout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (dl_layout.isDrawerOpen(GravityCompat.START)) {
            dl_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 按返回键不退出应用。
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (dl_layout.isDrawerOpen(GravityCompat.START)) {
                dl_layout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
