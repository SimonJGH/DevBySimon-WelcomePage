package com.simon.welcomepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.R.attr.animation;

public class WelcomeActivity extends FragmentActivity implements
        OnPageChangeListener {

    private Context mContext;
    private ViewPager wel_vp_welcome;
    private int[] wel_layout_id = new int[]{R.layout.welcome_layout_item1,
            R.layout.welcome_layout_item2, R.layout.welcome_layout_item3};
    private LinearLayout wel_ll_indicator;
    private ImageView[] indicators;
    private Button wel_bt_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        mContext = WelcomeActivity.this;

        wel_vp_welcome = (ViewPager) findViewById(R.id.wel_vp_welcome);
        wel_bt_start = (Button) findViewById(R.id.wel_bt_start);
        wel_ll_indicator = (LinearLayout) findViewById(R.id.wel_ll_indicator);
        indicators = new ImageView[wel_layout_id.length];
        for (int i = 0; i < wel_layout_id.length; i++) {
            indicators[i] = new ImageView(mContext);
            // 设置默认小圆点为灰色
            indicators[i].setImageResource(R.mipmap.indicators_default);
            // 设置第一个小圆点为红色
            if (i == 0) {
                indicators[i].setImageResource(R.mipmap.indicators_now);
            }
            // 把小圆点放到容器中wel_ll_indicator
            wel_ll_indicator.addView(indicators[i]);
        }

        wel_vp_welcome.setOffscreenPageLimit(wel_layout_id.length);
        wel_vp_welcome
                .setAdapter(new WelVPAdapter(getSupportFragmentManager()));
        wel_vp_welcome.setPageTransformer(true, new WelPageTransformer());
        wel_vp_welcome.setOnPageChangeListener(this);

    }

    // 进入软件
    public void clickButton(View view) {
        SharedPreferences shared = new SharedConfig(this).GetConfig();
        Editor editor = shared.edit();
        editor.putBoolean("isFirst", false);
        editor.commit();

        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        this.finish();
    }

    /**
     * 欢迎页适配器
     *
     * @author Simon
     */
    class WelVPAdapter extends FragmentPagerAdapter {

        public WelVPAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            WelcomeFragment wFragment = new WelcomeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("wel_layout_id", wel_layout_id[position]);
            bundle.putInt("position", position);
            wFragment.setArguments(bundle);

            return wFragment;
        }

        @Override
        public int getCount() {
            return wel_layout_id.length;
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        // 最后一张图片时显示进入按钮
        if (position == wel_layout_id.length - 1) {
            Animation animation = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.start_visiable);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    wel_bt_start.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            wel_bt_start.startAnimation(animation);
        } else {
            int visibility = wel_bt_start.getVisibility();
            if (visibility == View.VISIBLE) {
                Animation animation = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.start_invisiable);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        wel_bt_start.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                wel_bt_start.startAnimation(animation);
            }
        }
        for (int i = 0; i < wel_layout_id.length; i++) {
            if (i == position) {
                indicators[i].setImageResource(R.mipmap.indicators_now);
            } else {
                indicators[i].setImageResource(R.mipmap.indicators_default);
            }
        }
    }

}
