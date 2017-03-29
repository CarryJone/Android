package com.gao.bryan.myviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager ;
    private List<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }
    public void findview(){
        pager = (ViewPager) findViewById(R.id.pager);
        list = new ArrayList<>();
        LayoutInflater layout = getLayoutInflater();
        list.add(layout.inflate(R.layout.layout_one,null,false));
        list.add(layout.inflate(R.layout.layout_two,null,false));
        list.add(layout.inflate(R.layout.layout_there,null,false));
        SimplePagerAdapter adapter = new SimplePagerAdapter();
        pager.setAdapter(adapter);

    }

    public class SimplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

    }

}
