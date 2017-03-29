package com.gao.bryan.mybuttonselector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Camera_activity extends AppCompatActivity {
    private SurfaceView surface;
    private Camera camera = null;
    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            startpreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            stopreeview();
        }
    };

    private void startpreview() {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(surface.getHolder());
            camera.setDisplayOrientation(90);   //让相机旋转90度
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopreeview() {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activity);
        bindviews();
    }

    private void bindviews() {
        surface = (SurfaceView) findViewById(R.id.surfaceView);
        surface.getHolder().addCallback(callback);
    }
    public void onClick(View view){
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                File file = (File) getIntent().getSerializableExtra("path");
                File namefile = new File(file,System.currentTimeMillis()+".jpg");
                if(file != null){
                    saveFile(data,namefile);
                    Intent i = new Intent();
                    setResult(102,i);
                    camera.startPreview();

                }else{
                    Log.d("Mycamera","儲存照片失敗");
                }
            }
        });

    }
    //保存文件的方法
    private String saveFile(byte[] bytes,File file){
        try {
            //壓縮圖片及轉方向
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            bmp = rotaingScaleBitmap(90, bmp);
            FileOutputStream fos = new FileOutputStream(file);
            //將檔案存入
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.write(bytes);

            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static Bitmap rotaingScaleBitmap(int angle, Bitmap bitmap) {
        int width = 480;
        int height = 360;
        // 旋轉圖片動作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        matrix.postScale((float) width / bitmap.getWidth(), (float) height / bitmap.getHeight());
        // 建立新的圖片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }
}
