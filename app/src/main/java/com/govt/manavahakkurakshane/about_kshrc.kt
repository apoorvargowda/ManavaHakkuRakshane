package com.govt.manavahakkurakshane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView

class about_kshrc : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var abt_kshrc: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_kshrc)

        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }

        abt_kshrc = findViewById(R.id.abt_kshrc)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        abt_kshrc.webViewClient = WebViewClient()
        // this will load the url of the website
        abt_kshrc.loadUrl("https://www.geeksforgeeks.org/")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        abt_kshrc.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        abt_kshrc.settings.setSupportZoom(true)
    }
}