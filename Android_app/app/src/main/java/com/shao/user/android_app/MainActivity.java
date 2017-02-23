package com.shao.user.android_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText e01,e02;
    private Context context;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e01 = (EditText) findViewById(R.id.editText2);
        e02 = (EditText) findViewById(R.id.editText);
        context = this;

        sp = getSharedPreferences("my_login",context.MODE_PRIVATE);
        readPref();
    }

    @Override
    protected void onPause() {
        super.onPause();
        restorePref();

    }
    //儲存資料
    public void restorePref(){
        String usn = e01.getText().toString();
        String pwd = e02.getText().toString();
        String time = new Date(System.currentTimeMillis()).toString();
        //取得偏好設定編輯模式
        SharedPreferences.Editor edit = sp.edit();

        edit.putString("username",usn);
        edit.putString("password",pwd);
        edit.putString("createtime",time);
        edit.apply();


    }







    //讀取資料
    public void readPref(){
        String usn = sp.getString("username","");
        String pwd = sp.getString("password","");
        String time = sp.getString("createtime","");
        e01.setText(usn);
        e02.setText(String.valueOf(pwd));

        if(time.equals("")){
            this.setTitle("無偏好資料");
        }else{
            this.setTitle("上次紀錄時間:"+time);
        }

    }
}
