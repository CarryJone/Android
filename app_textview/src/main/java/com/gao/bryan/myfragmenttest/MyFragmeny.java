package com.gao.bryan.myfragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by bryan on 2017/3/27.
 */

public class MyFragmeny extends Fragment {

    private String message;
    private WebView webView;



    public MyFragmeny(String message) throws IOException {

        this.message = message;



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my,null);
        WebView webView = (WebView) v.findViewById(R.id.webview);
        TextView textView = (TextView) v.findViewById(R.id.textView5);
        if(message != null)
        textView.setText(message);
        ;
        webView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        if(message.indexOf("2")==-1 && message.indexOf("1")==-1 ) {
            webView.loadUrl("https://tw.yahoo.com/");

        }else {
            webView.loadUrl("https://www.google.com.tw/");
        }

        return v;
    }

}
