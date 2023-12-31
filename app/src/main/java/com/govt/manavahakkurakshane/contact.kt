package com.govt.manavahakkurakshane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView

class contact : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var contact_detail: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }

        contact_detail = findViewById(R.id.d_contact)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        contact_detail.webViewClient = WebViewClient()
        // this will load the url of the website
        contact_detail.loadUrl("https://www.linkedin.com/login")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        contact_detail.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        contact_detail.settings.setSupportZoom(true)
    }
}