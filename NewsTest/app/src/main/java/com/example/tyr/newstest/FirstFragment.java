package com.example.tyr.newstest;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyr.newstest.GridList.GridItem;
import com.example.tyr.newstest.GridList.GridListAdapter;
import com.example.tyr.newstest.ViewPager.AutoScrollViewPager;
import com.example.tyr.newstest.ViewPager.BaseViewPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyr on 2017/2/20.
 */
public class FirstFragment extends Fragment {

    //轮播图
    private AutoScrollViewPager mViewPager;
    private String[] paths = {"https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=c493b482b47eca800d053ee7a1229712/8cb1cb1349540923abd671df9658d109b2de49d7.jpg",
            "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=45fbfa5555da81cb51e684cd6267d0a4/2f738bd4b31c8701491ea047237f9e2f0608ffe3.jpg",
            "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ae0e95c0fc1986185e47e8847aec2e69/0b46f21fbe096b63eb314ef108338744ebf8ac62.jpg",
            "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=1fad2b46952397ddc9799f046983b216/dc54564e9258d109c94bbb13d558ccbf6d814de2.jpg",
            "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ff0999f6d4160924c325a51be406359b/86d6277f9e2f070861ccd4a0ed24b899a801f241"};
    private View view;
    private Context context;

    //工具栏
    private GridView gridView;
    private List<GridItem> mgridDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,null);
        getView();
        initGridView();
        initViewPager();
        return view;
    }

    //初始化工具栏
    private void initGridView() {
        initGridData();
        gridView = (GridView)view.findViewById(R.id.fistfragment_grid);
        GridListAdapter gridListAdapter = new GridListAdapter(context,mgridDatas);
        gridView.setAdapter(gridListAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(),CsdnActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void initGridData(){
        mgridDatas = new ArrayList<GridItem>();
        mgridDatas.add(new GridItem("cnblog",R.drawable.cnblog1));
        mgridDatas.add(new GridItem("csdn",R.drawable.csdn1));
        mgridDatas.add(new GridItem("github", R.drawable.github1));
        mgridDatas.add(new GridItem("myweb",R.drawable.myweb1));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }


    //初始化轮播图
    private void initViewPager() {
        mViewPager = (AutoScrollViewPager)view.findViewById(R.id.viewpager);
        BaseViewPagerAdapter<String> adapter = new BaseViewPagerAdapter<String>(context,listener) {
            @Override
            public void loadImage(ImageView view, int position, String url) {
                Picasso.with(context).load(url).into(view);
            }
            @Override
            public void setSubTitle(TextView textView, int position, String s) {
                textView.setText(s);
            }
        };
        mViewPager.setAdapter(adapter);
        adapter.add(initData());
    }
    private List<String> initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0 ; i < paths.length ;i++){
            data.add(paths[i]);
        }
        return data;
    }
    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<String>() {
        @Override
        public void onItemClick(int position, String url) {
            Toast.makeText(context,"haha",Toast.LENGTH_SHORT).show();
        }

    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.onDestroy();
    }



}
