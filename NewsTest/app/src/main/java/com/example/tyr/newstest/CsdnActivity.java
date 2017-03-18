package com.example.tyr.newstest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.tyr.newstest.Constractor.CnblogInternet.XMLRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyr on 2017/2/21.
 */
public class CsdnActivity extends AppCompatActivity implements View.OnClickListener{

    //顶部导航栏
    private Toolbar toolbar;
    private ImageButton left_img;
    private ImageButton right_img;

    //顶部菜单栏
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    private ImageButton mWeixinImg;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;
    private ImageButton mSettingImg;


    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mViews ;



    //侧滑栏
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private LeftFragment fg_left_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cnblogactivity_main);
        initView();
    }

    private void initView() {
        initActionBar();
        initView2();
        initEvents();
        initSlidingView();
    }


    //初始化顶部导航栏
    private void initEvents() {
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                resetImg();
                switch (currentItem) {
                    case 0:
                        //mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        //mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        //mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        //mSettingImg.setImageResource(R.drawable.tab_settings_pressed);
                        break;
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }
    private void initView2() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        // tabs
        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);
        // ImageButton
        mWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
        mSettingImg = (ImageButton) findViewById(R.id.id_tab_settings_img);

        mViews = new ArrayList<Fragment>();
        Fragment mTabb01 = new CnblogFragment();
        Fragment mTabb02 = new ThirdFragment();
        Fragment mTabb03 = new FourthFragment();
        Fragment mTabb04 = new SecondFragment();
        mViews.add(mTabb01);
        mViews.add(mTabb02);
        mViews.add(mTabb03);
        mViews.add(mTabb04);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mViews.get(position);
            }

            @Override
            public int getCount() {
                return mViews.size();
            }
        };
        mViewPager.setAdapter(mAdapter);

    }
    private void resetImg() {
        /*mWeixinImg.setImageResource(R.drawable.tab_weixin_normal);
        mFrdImg.setImageResource(R.drawable.tab_find_frd_normal);
        mAddressImg.setImageResource(R.drawable.tab_address_normal);
        mSettingImg.setImageResource(R.drawable.tab_settings_normal);*/
    }
    private void setSelect(int i){
        resetImg();
        switch (i){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        mViewPager.setCurrentItem(i);
    }
    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()){
            case R.id.id_tab_weixin:
                setSelect(0);
                break;
            case R.id.id_tab_frd:
                setSelect(1);
                break;
            case R.id.id_tab_address:
                setSelect(2);
                break;
            case R.id.id_tab_settings:
                setSelect(3);
                break;
        }
    }

    //初始化顶部导航栏
    private void initActionBar() {
        toolbar = (Toolbar)findViewById(R.id.topbar1);
        left_img = (ImageButton)findViewById(R.id.top_img_btn1);
        right_img = (ImageButton)findViewById(R.id.top_img_btn21);

        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CsdnActivity.this, "22222", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //侧滑栏
    private void initSlidingView(){
        fragmentManager = getSupportFragmentManager();
        fg_left_menu = (LeftFragment)fragmentManager.findFragmentById(R.id.fg_left_menu1);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout1);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        fg_left_menu.setDrawerLayout(drawerLayout);
    }
    private void toggle(){
        int drawerLockMode = drawerLayout.getDrawerLockMode(GravityCompat.START);
        if (drawerLayout.isDrawerVisible(GravityCompat.START)
                &&(drawerLockMode!=DrawerLayout.LOCK_MODE_LOCKED_OPEN)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else if (drawerLockMode!=DrawerLayout.LOCK_MODE_LOCKED_CLOSED){
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
