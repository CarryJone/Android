package com.gao.bryan.mycdcapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gao.bryan.mycdcapi.md5.KeyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity" ;
    SimpleDateFormat sdf;
    Date dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sdf = new SimpleDateFormat("yyyy/MM/dd");

    }
    public void onClick(View view){
        dt = new Date();
        String userAcc = "fiona.liao@hzn.com.tw";
        String remoteAcc = "20141125";
        String date = sdf.format(dt);
        String answerString = "111";
        String key = KeyUtil.getMD5("DOTS"+userAcc+remoteAcc+date+answerString);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("acc1", userAcc);
        map.put("acc2", remoteAcc);
        map.put("ACDate", date);
        map.put("key", key);
        map.put("sid10", "");
        map.put("sid1","1");
        map.put("sid2","1");
        map.put("sid3","1");
        ApiServer.getInstance().setDailyResult(map).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResultData<String>>()
        {
            @Override
            public void onCompleted()
            {
                Log.d(TAG, "sendAffectResult onCompleted");
            }

            @Override
            public void onError(Throwable e)
            {
                Log.e(TAG, "sendAffectResult onError");
                Log.e(TAG, "sendAffectResult onError Throwable : " + e.getMessage());

            }

            @Override
            public void onNext(ResultData<String> data)
            {
                Log.d(TAG, "sendAffectResult onNext");
                Log.d(TAG, "sendAffectResult ResultData : " + data.toString());
                /** 提示使用者訊息 **/
                if (data.getResult().equals("1"))
                {
                    /** 回報完後, 清除資料欄位 **/

                }

            }
        });
    }
}
