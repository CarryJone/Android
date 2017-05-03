package com.gao.bryan.myaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyTestAIDL myTestAIDL;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myTestAIDL = MyTestAIDL.Stub.asInterface(service);
            Log.d("MyAidlText","onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myTestAIDL = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b01://綁定
                Intent intent = new Intent(this,Myservice.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.b02://控制
                if (myTestAIDL != null) {
                    try {
                        myTestAIDL.controlService(3);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.b03://傳值

                break;
        }
    }
}
