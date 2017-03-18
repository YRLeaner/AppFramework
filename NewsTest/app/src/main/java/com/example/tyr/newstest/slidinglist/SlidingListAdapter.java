package com.example.tyr.newstest.slidinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyr.newstest.R;

import java.util.List;

/**
 * Created by tyr on 2017/2/20.
 */
public class SlidingListAdapter  extends BaseAdapter{
    private List<String> mDatas;
    private LayoutInflater mInflater;
    private Context context;

    public SlidingListAdapter(Context context,List<String> mDatas) {
        this.mDatas = mDatas;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String s = mDatas.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.sliding_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mImgeView =  (ImageView)convertView.findViewById(R.id.sliding_img);
            viewHolder.mtitle = (TextView)convertView.findViewById(R.id.sliding_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.mtitle.setText(s);
        viewHolder.mImgeView.setImageResource(R.drawable.csdn1);
        return convertView;
    }

    private final class ViewHolder{
        ImageView mImgeView;
        TextView mtitle;
    }
}
