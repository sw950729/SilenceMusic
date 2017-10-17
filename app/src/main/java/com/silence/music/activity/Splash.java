package com.silence.music.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.angel.music.R;
import com.silence.music.utils.statusbar.StatusBarUtil;

/**
 *
 * @author Angel
 * @date 2017/10/15
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.ac_splash);
        StatusBarUtil.setTranslucent(this,30);
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
