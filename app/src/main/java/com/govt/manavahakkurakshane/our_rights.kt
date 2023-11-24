package com.govt.manavahakkurakshane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView

class our_rights : AppCompatActivity() {
    private lateinit var ourrights: WebView
    private lateinit var btnBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_our_rights)

        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }

        ourrights = findViewById(R.id.ourright)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        ourrights.webViewClient = WebViewClient()
        // this will load the url of the website
        ourrights.loadUrl("https://www.geeksforgeeks.org/")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        ourrights.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        ourrights.settings.setSupportZoom(true)
    }


}