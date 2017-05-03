package com.gao.bryan.myokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    private static String tag = "mydebug";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        imageView = (ImageView) findViewById(R.id.imageView);
        RxjavaTest();
    }
    public void RxjavaTest(){
        //觀察者1
        Observer<String> o = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(tag,"item :"+s);
            }

            @Override
            public void onCompleted() {
                Log.d(tag,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag,"onError :"+e);
            }
        };
        //被觀察者
        //(方法一)2
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
        //(方法二)2
//        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        //(方法三)2
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);
        //註冊監聽3
        observable.subscribe(o);


        //練習1 印出陣列東西
        String[] name = {"tim","bruce","curry","jone","bryan"};
        Observable.from(name).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(tag,"item :"+s);
            }});

//        Observable.just("Hello, world!")
//                .subscribe(s -> System.out.println(s));
    }

    public void onClick(View view){
        new GetHttp().start();
    }
    class GetHttp extends Thread{
        String date;
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    String name = new JSONObject(date).getString("MethodName");
                    textView.setText(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }};
        @Override
        public void run() {
            try {
                date = run("http://210.69.108.246/TPH_VIDEO/PhoneService.svc/qa?action=login&phone=hzntest&device_id=ffffffff-931f-347d-ffff-ffffcfb7ad0f");

            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread(r);
        }
    }

}
