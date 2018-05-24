package com.silence.music.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.angel.music.R;
import com.silence.music.MainActivity;

/**
 * Created by Angel on 2017/10/15.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
        new Handler().postDelayed(() -> startActivity(new Intent(this, MainActivity.class)), 3000);
    }
}
