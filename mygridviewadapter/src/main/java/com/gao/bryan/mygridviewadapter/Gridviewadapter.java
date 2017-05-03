package com.gao.bryan.mygridviewadapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

/**
 * Created by bryan on 2017/3/31.
 */

public class Gridviewadapter extends BaseAdapter {
    List<Map<String,Integer>> list;
    Context mcontext;
    Handler handler;

    public Gridviewadapter(List<Map<String, Integer>> list, Context mcontext) {
        this.list = list;
        this.mcontext = mcontext;
        Log.d("listsize",list.size()+"");
    }

    @Override
    public int getCount() {
        return list.size();
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
        handler = new Handler();
        View v = LayoutInflater.from(mcontext).inflate(R.layout.layout_adapter,null);
        final ImageView imageview = (ImageView) v.findViewById(R.id.imageView);
        Map<String,Integer> map = list.get(position);
        final int image = map.get("image");
        imageview.setImageResource(R.drawable.ipod);
        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    imageview.setImageResource(image);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageview.setImageResource(R.drawable.ipod);
                    }
                },800);


                return false;
            }

        });



        return v;
    }
}
