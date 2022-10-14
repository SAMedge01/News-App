package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

public class webView2 extends AppCompatActivity {

    Toolbar toolbar;
   WebView webView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        toolbar=findViewById(R.id.toolbar);
        webView2=findViewById(R.id.webview);
        

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        webView2.setWebViewClient(new WebViewClient());
        webView2.loadUrl(url);

    }
}