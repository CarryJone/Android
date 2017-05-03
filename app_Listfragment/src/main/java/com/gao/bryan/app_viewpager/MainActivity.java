package com.gao.bryan.app_viewpager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private FrameLayout frameLayout;
    private FragmentManager fm ;
    private List<Date> mdate;
    private Context context;
    private long exitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindview();
        OneFragment one = new OneFragment(mdate,fm);
        FragmentTransaction ftcion = fm.beginTransaction();
        ftcion.replace(R.id.myfragment,one);
        ftcion.commit();
        textView.setText("新聞列表");

    }

    private void bindview() {
        mdate = new ArrayList<>();
        for(int i = 0;i<120;i++){
            mdate.add(new Date("新聞列表"+i,i+"新聞內容"));
        }
        textView = (TextView) findViewById(R.id.textView3);
        frameLayout = (FrameLayout) findViewById(R.id.myfragment);
        fm = getFragmentManager();
        context = this;
    }

    @Override
    public void onBackPressed() {


        if(fm.getBackStackEntryCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else{
                super.onBackPressed();
            }
        }else{
            fm.popBackStack();
            textView.setText("新聞列表");
        }


    }
}
