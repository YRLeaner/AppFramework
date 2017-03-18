package com.example.tyr.newstest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyr.newstest.Entity.BlogInfo;

import java.util.List;

/**
 * Created by tyr on 2017/2/22.
 */
public class CnblogListAdapter extends BaseAdapter{

    private List<BlogInfo> list;
    private Context context;
    public CnblogListAdapter(Context context, List<BlogInfo> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BlogInfo blogInfo = list.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.cnblog_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image =  (ImageView)convertView.findViewById(R.id.cnblog_img);
            viewHolder.title = (TextView)convertView.findViewById(R.id.cnblog_title);
            viewHolder.authorname = (TextView)convertView.findViewById(R.id.cnblog_author_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(blogInfo.getTitle());
        viewHolder.authorname.setText(blogInfo.getAuthor().getName());
        viewHolder.image.setImageResource(R.drawable.csdn1);
        return convertView;
    }

    private class ViewHolder{
        ImageView image;
        TextView title;
        TextView authorname;
    }
}
