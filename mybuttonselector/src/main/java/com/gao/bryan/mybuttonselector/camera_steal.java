package com.gao.bryan.mybuttonselector;

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

public class camera_steal extends AppCompatActivity {
    private Camera camera = null;
    private SurfaceView surfaceView;
    private SurfaceHolder.Callback  callback = new SurfaceHolder.Callback() {
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
            camera.setPreviewDisplay(surfaceView.getHolder());
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
        setContentView(R.layout.activity_camera_steal);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView2);
        surfaceView.getHolder().addCallback(callback);
    }
    public void onClick(View view){
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Log.d("拍","拍照");
                File path = (File) getIntent().getSerializableExtra("path");
                File namefile = new File(path,System.currentTimeMillis()+".jpg");
                Log.d("拍","拍照2");
                try {
                    FileOutputStream output = new FileOutputStream(namefile);
                    output.write(data);
                    Log.d("拍","拍照3");
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("拍","拍照4");
                }
                camera.startPreview();

            }
        });

    }

}
