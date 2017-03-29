package com.gao.bryan.myfragmenttest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView t1,t2,t3,t4;
    private MyFragmeny f1,f2,f3,f4;
    private FrameLayout flayout;
    private FragmentManager fm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindviews();
    }

    private void bindviews() {
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);

        flayout = (FrameLayout) findViewById(R.id.flayout);
        fm = getFragmentManager();

        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);


    }
    public void setSelected(){
        t1.setSelected(false);
        t2.setSelected(false);
        t3.setSelected(false);
        t4.setSelected(false);
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(f1 != null)fragmentTransaction.hide(f1);
        if(f2 != null)fragmentTransaction.hide(f2);
        if(f3 != null)fragmentTransaction.hide(f3);
        if(f4 != null)fragmentTransaction.hide(f4);
    }

    private void TextviewColor(){
        t1.setTextColor(Color.BLACK);
        t2.setTextColor(Color.BLACK);
        t3.setTextColor(Color.BLACK);
        t4.setTextColor(Color.BLACK);

    }
    @Override
    public void onClick(View v) {
        FragmentTransaction ftction = fm.beginTransaction();
        hideAllFragment(ftction);
        TextviewColor();
        TextView v1 = (TextView) v;
        v1.setTextColor(Color.RED);
        switch (v.getId()){
            case R.id.textView:
                setSelected();
                t1.setSelected(true);
                if(f1==null){
                    try {
                        f1 = new MyFragmeny("第4個Fragment");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ftction.add(R.id.flayout,f1);
                }
                else ftction.show(f1);
                break;
            case R.id.textView2:
                setSelected();
                t2.setSelected(true);
                if(f2==null) {
                    try {
                        f2 = new MyFragmeny("第3個Fragment");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ftction.add(R.id.flayout, f2);
                }
                else ftction.show(f2);
                break;
            case R.id.textView3:
                setSelected();
                t3.setSelected(true);
                if(f3==null) {
                    try {
                        f3 = new MyFragmeny("第2個Fragment");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ftction.add(R.id.flayout, f3);
                }
                else ftction.show(f3);
                break;
            case R.id.textView4:
                setSelected();
                t4.setSelected(true);
                if(f4==null) {
                    try {
                        f4 = new MyFragmeny("第1個Fragment");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ftction.add(R.id.flayout, f4);
                }
                else ftction.show(f4);
                break;
        }
        ftction.commit();
    }


}
