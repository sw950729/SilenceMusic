package com.silence.music;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.angel.music.R;
import com.silence.music.adapter.HomeFragmentPageAdapter;
import com.silence.music.base.BaseFragment;
import com.silence.music.main.LocalFragment;
import com.silence.music.main.RecommendFragment;
import com.silence.music.main.zhihu.ZhiHuFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Silence
 * @date 2018/3/21.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fl_title_menu;
    private RadioGroup rg_home_viewpager_contorl;
    private DrawerLayout dl_layout;
    private ViewPager vp_content;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
    }

    private void initial() {
        fl_title_menu = findViewById(R.id.fl_title_menu);
        rg_home_viewpager_contorl = findViewById(R.id.rg_home_viewpager_contorl);
        dl_layout = findViewById(R.id.dl_layout);
        vp_content = findViewById(R.id.vp_content);
        fl_title_menu.setOnClickListener(this);
        fragmentList.add(new LocalFragment());
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new ZhiHuFragment());
        vp_content.setAdapter(new HomeFragmentPageAdapter(getSupportFragmentManager(), fragmentList));
        vp_content.setCurrentItem(1);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rg_home_viewpager_contorl.check(R.id.rb_home_pager);
                        break;
                    case 1:
                        rg_home_viewpager_contorl.check(R.id.rb_music_pager);
                        break;
                    case 2:
                        rg_home_viewpager_contorl.check(R.id.rb_friend_pager);
                        break;
                    default:
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg_home_viewpager_contorl.setOnCheckedChangeListener(((radioGroup, checkedId) -> {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rb_home_pager:
                    rg_home_viewpager_contorl.check(R.id.rb_home_pager);
                    vp_content.setCurrentItem(0);
                    break;
                case R.id.rb_music_pager:
                    rg_home_viewpager_contorl.check(R.id.rb_music_pager);
                    vp_content.setCurrentItem(1);
                    break;
                case R.id.rb_friend_pager:
                    rg_home_viewpager_contorl.check(R.id.rb_friend_pager);
                    vp_content.setCurrentItem(2);
                    break;
                default:
            }
        }));
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
