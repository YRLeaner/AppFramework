package com.example.tyr.newstest.GridList;

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
 * Created by tyr on 2017/2/21.
 */
public class GridListAdapter extends BaseAdapter {

    private List<GridItem> mDatas;
    private Context context;
    private LayoutInflater mInflater;

    public GridListAdapter(Context context,List<GridItem> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridItem gridItem = mDatas.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.gridlist_layout_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.grid_imageView =  (ImageView)convertView.findViewById(R.id.grid_img);
            viewHolder.grid_textView = (TextView)convertView.findViewById(R.id.grid_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.grid_textView.setText(gridItem.getTitle());
        viewHolder.grid_imageView.setImageResource(gridItem.getImg());
        return convertView;
    }

    private class ViewHolder{
        private ImageView grid_imageView;
        private TextView  grid_textView;
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
}
