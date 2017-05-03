package com.gao.bryan.mygridviewadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    GridView gv01;
    List<Map<String,Integer>> list;
    int num;
    Gridviewadapter adapter;
    Context mcontext;
    ImageView a;
    final int[] ar1 = {R.drawable.b01,R.drawable.b02,R.drawable.b03,R.drawable.b04,R.drawable.b05,R.drawable.b06,R.drawable.b07,R.drawable.b08,R.drawable.b09,R.drawable.b10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext = this;
        findview();
    }

    private void findview() {
        gv01 = (GridView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        for(int i=0;i<ar1.length;i++) {
            Log.d("findview",ar1.length+"");
            Map<String,Integer> map = new HashMap<>();
            map.put("image",ar1[i]);
            map.put("num",i);
            list.add(map);
            list.add(map);
        }
        adapter = new Gridviewadapter(list,mcontext);
        gv01.setAdapter(adapter);
        Collections.shuffle(list);//洗牌
        adapter.notifyDataSetChanged();//更新排序畫面
        gv01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                a = (ImageView) view;
                if(position ==  num ){
                    adapter.notifyDataSetChanged();//更新排序畫面
                }
                num = position;
            }
        });
    }

}
