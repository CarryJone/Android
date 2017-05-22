package com.gao.bryan.mycdcapi;


import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by bryan on 2017/5/19.
 */

public class ApiServer {

    private final static String API_SERVER_URL = "http://210.65.89.58/WebAPI_sandbox/";
    private static ApiServer server = new ApiServer();
    public static ApiServer getInstance(){return server;}
    private DotsApiServer mService;

    public ApiServer(){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_SERVER_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        mService = retrofit.create(DotsApiServer.class);

    }
    private interface DotsApiServer{
        @POST("setDaily.ashx?")
        Observable<ResultData<String>> setDailyResult(
                @QueryMap
                        Map<String, String> map);

    }
    public Observable<ResultData<String>> setDailyResult(final Map<String, String> map)
    {
        return mService.setDailyResult(map);
    }
}

