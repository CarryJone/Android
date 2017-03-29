package com.gao.bryan.mybuttonselector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private File currentImageFile = null;
    private File file = null;
    private final String TAG = "MYcamera";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

    }
    public void fileset(){
        //獲取路徑 資料夾名稱
        file = new File(Environment.getExternalStorageDirectory(),"MYgame");
        if(file.exists()){
            //如果父文件夾不存會直接建立
            file.mkdirs();
        }
        Log.d(TAG,file.toString());
        //圖片名稱   File (路徑,檔名)
        currentImageFile = new File(file,System.currentTimeMillis()+".jpg");
        if(!currentImageFile.exists()){
            try {
                currentImageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG,currentImageFile.toString());

    }
    //內建相機
    public void onClick(View view){
        fileset();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
        startActivityForResult(intent,101);
    }
    //自訂相機
    public void onClick2(View view){
        fileset();
        Intent intent = new Intent(this,Camera_activity.class);
        intent.putExtra("path",file);
        startActivityForResult(intent,102);
    }
    public void onClick3(View view){
        fileset();
        Intent intent = new Intent(this,camera_steal.class);
        intent.putExtra("path",file);
        startActivityForResult(intent,103);
    }


    //拍完照片轉跳回來
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 101)
            imageView.setImageURI(Uri.fromFile(currentImageFile));
            if (requestCode == 102) {
                imageView.setImageURI(Uri.fromFile(currentImageFile));
                }
            }
}
