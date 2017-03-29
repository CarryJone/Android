package com.gao.bryan.app_filedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    private EditText ename,emessage;
    FileHelper fileHelper ;
    Context mcontext;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindviews();

    }

    private void bindviews() {
        mcontext = this;
        ename = (EditText) findViewById(R.id.editText2);
        emessage = (EditText) findViewById(R.id.editText);
        fileHelper = new FileHelper(mcontext);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                try {
                    fileHelper.save(ename.getText().toString(), emessage.getText().toString());
                    Toast.makeText(mcontext,"資料寫入成功",Toast.LENGTH_SHORT).show();
                    Picasso.with(mcontext).load("http://i.imgur.com/DvpvklR.png").into(imageView);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(mcontext,"資料寫入失敗",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:
                ename.setText("");
                emessage.setText("");
                break;
            case R.id.button3:
                String mes = "";
                try{
                    mes = fileHelper.read(ename.getText().toString());
                }catch(Exception e){
                    e.printStackTrace();
            }
            Toast.makeText(mcontext,mes,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
