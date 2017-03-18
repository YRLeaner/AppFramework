package com.example.tyr.newstest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tyr.newstest.Constractor.CnblogInternet.XMLRequest;
import com.example.tyr.newstest.ViewPager.AutoScrollViewPager;
import com.example.tyr.newstest.ViewPager.BaseViewPagerAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //顶部导航栏
    private Toolbar toolbar;
    private ImageView left_img;
    private ImageView right_img;

    //底部导航栏
    private FragmentTabHost tabHost;
    private List<Fragment> fragments;
    private ViewPager pager;

    //侧滑栏
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private LeftFragment fg_left_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initActionBar();
        initBelowBar();
        initSlidingView();
    }

    //初始化顶部导航栏
    private void initActionBar() {
        toolbar = (Toolbar)findViewById(R.id.topbar);
        left_img = (ImageView)findViewById(R.id.top_img_btn);
        right_img = (ImageView)findViewById(R.id.top_img_btn2);

        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "22222", Toast.LENGTH_SHORT).show();
            }
        });
    }




    //初始化底部导航栏
    /*
    * 这种方法不太好，增加了一倍的fragment使用量，不推荐
    * */
    private void initBelowBar(){
        initTabHost();
        initPager();
        bindTabAndPager();
    }
    private void initTabHost(){
        tabHost = (FragmentTabHost)findViewById(R.id.tab_host);

        tabHost.setup(this, getSupportFragmentManager(), R.id.pager);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.addTab(tabHost.newTabSpec("首页").setIndicator(createView(R.drawable.select_font, "首页")), FirstFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("发现").setIndicator(createView(R.drawable.select_font, "发现")), SecondFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("我的").setIndicator(createView(R.drawable.select_font, "我的")), ThirdFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("管理").setIndicator(createView(R.drawable.select_font, "管理")), FourthFragment.class, null);
    }
    private void initPager(){
        pager = (ViewPager)findViewById(R.id.pager);
        fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new FourthFragment());
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        pager.setAdapter(adapter);

    }
    private void bindTabAndPager(){


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position = tabHost.getCurrentTab();
                pager.setCurrentItem(position, true);
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private View createView(int icon,String tab){
        View view = getLayoutInflater().inflate(R.layout.tap_layout,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.icon);
        TextView title = (TextView) view.findViewById(R.id.title);
        imageView.setImageResource(icon);
        title.setText(tab);
        return view;
    }

    //初始化左边侧滑栏
    private void initSlidingView(){
        fragmentManager = getSupportFragmentManager();
        fg_left_menu = (LeftFragment)fragmentManager.findFragmentById(R.id.fg_left_menu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
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
