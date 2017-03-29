package com.gao.bryan.app_viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bryan on 2017/3/27.
 */

public class Myadapter extends BaseAdapter {
    private List<Date> mdate;
    private Context mcontext;

    public Myadapter(List<Date> mdate, Context mcontext) {
        this.mdate = mdate;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return mdate.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.textlist,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mdate.get(position).getTitle());
        return convertView;
    }
    public class ViewHolder{
        TextView textView;
    }
}
