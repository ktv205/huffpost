package com.example.krishnateja.huffpostcodingchallenge.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import com.example.krishnateja.huffpostcodingchallenge.R;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class WebViewActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webview =(WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(getIntent().getStringExtra(Intent.EXTRA_TEXT));

    }
}
