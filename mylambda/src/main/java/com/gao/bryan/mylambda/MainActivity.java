package com.gao.bryan.mylambda;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    UserDate m1,m2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Userdate","Maincreat");
        Context context= this;
//        Runnable r1 = () -> Log.d("Lamdba1",context.getClass()+"");
//        Runnable r2 = new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Lamdba2",context.getClass()+"");
//            }
//        };
//        new Thread(r1).start();
//        new Thread(r2).start();

        textView = (TextView)findViewById(R.id.textview);
        textView.setOnClickListener( v -> Toast.makeText(getApplicationContext(), "Lambda", Toast.LENGTH_LONG).show());

//        List<String> list = new ArrayList<>();
//        for(int i=0; i<50;i++){
//            list.add(i+"");
//        }
//
//        list.forEach( s->Log.d("陣列",s+""));
////        for(int a :list){
////            Log.d("陣列",a+"");
////        }
        Myapplication myapplication = Myapplication.getInstence();


        m1 = UserDate.getInstence();
        m2 = myapplication.getmUserdate();

        m1.setName("123456");
        Log.d("123456",m2.getName());


    }
}
