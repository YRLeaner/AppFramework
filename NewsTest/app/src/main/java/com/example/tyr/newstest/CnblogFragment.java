package com.example.tyr.newstest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tyr.newstest.Entity.Author;
import com.example.tyr.newstest.Entity.BlogInfo;
import com.example.tyr.newstest.HttpUtitls.HttpUtitl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyr on 2017/2/22.
 */
public class CnblogFragment extends Fragment {


    private List<BlogInfo> list;
    private RefreshList listView;
    private CnblogListAdapter madapter;

    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cnblog_fragment_main,null);
        getView();
        initView();
        initData();
        return view;
    }

    private void initView() {
        listView = (RefreshList)view.findViewById(R.id.cnblog_fragment_list);
        list = new ArrayList<BlogInfo>();

        list.add(new BlogInfo("123","haha",new Author("wom","......",R.drawable.csdn1,"12")));
        madapter = new CnblogListAdapter(getActivity(),list);
        listView.setAdapter(madapter);

        listView.setonRefreshListener(new RefreshList.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("asy","yes");
                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... params) {

                       /* HttpUtitl httpUtitl = new HttpUtitl(list);
                        httpUtitl.Cnbloginfo_volley_Get();*/
                        //list.add("刷新后的内容");

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        madapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }
                }.execute(null,null,null);
            }
        });
    }

    private void initData() {

    }
}
