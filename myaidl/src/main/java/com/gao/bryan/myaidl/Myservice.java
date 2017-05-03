package com.gao.bryan.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by bryan on 2017/4/13.
 */

public class Myservice extends Service {

    MyTestAIDL.Stub stub = new MyTestAIDL.Stub() {
        @Override
        public void controlService(int num) throws RemoteException {
                Log.d("MyAidlText","num: "+num);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

}
