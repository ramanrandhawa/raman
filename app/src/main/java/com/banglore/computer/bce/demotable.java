package com.banglore.computer.bce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class demotable extends AppCompatActivity {

    WebView web;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demotable);
        web=(WebView)findViewById(R.id.demo_web);

        web.loadUrl("file:///android_asset/ques.html");
        web.getSettings().setJavaScriptEnabled(true);
         web.setWebViewClient(new WebViewClient(){

        @Override
        public void onPageFinished(WebView view, String url) {
          web.loadUrl("javascript:($(document).ready(function(){$('#tbl').append('<tr><td>'"+counter+"'</tr>')})");




        }
    });

    }
}
