package com.example.tyr.newstest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tyr.newstest.slidinglist.SlidingListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyr on 2017/2/20.
 */
public class LeftFragment extends Fragment {

    private DrawerLayout drawerLayout;

    //初始化listView
    private ListView listView;
    private List<String> mDatas;
    private SlidingListAdapter slidingListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sliding_left_layout,container,false);
        listView = (ListView)view.findViewById(R.id.sliding_listview);
      /*  ImageView img_bg = (ImageView)view.findViewById(R.id.img_bg);
        img_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hhahha",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });*/
        initDatas();
        return view;
    }

    private void initDatas(){
        mDatas = new ArrayList<String>();
        mDatas.add("one");
        mDatas.add("two");
        mDatas.add("three");
        mDatas.add("four");

        slidingListAdapter = new SlidingListAdapter(getActivity(),mDatas);
        listView.setAdapter(slidingListAdapter);

    }


    public void setDrawerLayout(DrawerLayout drawerLayout){
        this.drawerLayout = drawerLayout;
    }
}
