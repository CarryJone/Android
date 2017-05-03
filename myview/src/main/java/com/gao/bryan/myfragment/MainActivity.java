package com.gao.bryan.myfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Context context;
    EditText e01,e02;
    Myview view2;
    TextView textView;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindviews();

        jsontest();
    }

    private void bindviews() {
        context = this;
        e01 = (EditText) findViewById(R.id.editText);
        e02 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("555");
    }

    public void onClick(View view){
        int w = Integer.parseInt(e01.getText().toString());
        int h = Integer.parseInt(e02.getText().toString());
        view2 = (Myview) findViewById(R.id.myview);

    }
    public void jsontest(){
        String strjson = "{\"Employee\" :[{\"id\":\"01\",\"name\":\"Gopal Varma\",\"salary\":\"500000\"},{\"id\":\"02\",\"name\":\"Sairamkrishna\",\"salary\":\"500000\"},{\"id\":\"03\",\"name\":\"Sathish kallakuri\",\"salary\":\"600000\"}]}";

        String url = "http://gnehcic.azurewebsites.net/sample/SampleWebAPI.php";
        try {
            JSONObject jsonObject = new JSONObject(strjson);
            int id = jsonObject.getJSONArray("Employee").getJSONObject(0).getInt("id");
            String name = jsonObject.getJSONArray("Employee").getJSONObject(0).getString("name");
            int salary = jsonObject.getJSONArray("Employee").getJSONObject(0).getInt("salary");
            textView.append(id+name+salary);
            textView.append("123456");
            Log.d("json轉String",jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject json = new JSONObject();
            json.put("title", "Mr.");
            json.put("name", "gnehcic");
            String json2 = json.toString();
            new Webtest(json2,url).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Handler h = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        };
        if(data.equals("")||data == null) {
            h.postDelayed(r, 1000);
        }
    }
    class Webtest extends Thread{
        String json = null;
        String url = null;

        public Webtest(String json, String url){
            this.json = json;
            this.url = url;
        }
        public  final MediaType JSON
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
        @Override
        public void run() {
            try {
                data = post(url,json);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
