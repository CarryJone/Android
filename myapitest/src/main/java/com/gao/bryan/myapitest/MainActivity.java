package com.gao.bryan.myapitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView.setText("123456789");
        new gatapi().start();
        Log.d("mytest", "getapi");
    }

    class gatapi extends Thread {

        String data = "";
        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                Log.d("mytest", "getapi3");
                String name = data;
                textView.setText(name);

            }
        };

        public void run() {
            try {
                JSONObject json = new JSONObject();
                json.put("data", "Bryan");
                textView.setText(json.toString());
                data = post("http://localhost:8084/MyWebApi/test.jsp", json.toString());
                Log.d("mytest", "getapi2");


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            runOnUiThread(r);
        }
    }
}

