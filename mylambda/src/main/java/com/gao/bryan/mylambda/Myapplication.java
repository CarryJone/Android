package com.gao.bryan.mylambda;

import android.app.Application;
import android.util.Log;

/**
 * Created by bryan on 2017/3/30.
 */

public class Myapplication extends Application {
    private static Myapplication myapplication;
    private UserDate mUserdate;

    @Override
    public void onCreate() {
        super.onCreate();
        myapplication = this;
        Log.d("Userdate","Myapplicationcreat");
        mUserdate = UserDate.getInstence();
    }
    public UserDate getmUserdate(){
        return mUserdate;
    }
    public static Myapplication getInstence(){return myapplication;}
}
