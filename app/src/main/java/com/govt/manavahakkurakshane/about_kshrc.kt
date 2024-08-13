package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.govt.manavahakkurakshane.common.PreferenceHelper

class about_kshrc : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var abt_kshrc: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_kshrc)
        findViewById<TextView>(R.id.heading).text = getString(R.string.about_kshrc)
        findViewById<ImageView>(R.id.logout).setOnClickListener {
            PreferenceHelper.defaultPrefs(this).edit().clear().apply()
            finish()
            val i = Intent(this, Login::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        findViewById<ImageView>(R.id.home).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }

        abt_kshrc = findViewById(R.id.abt_kshrc)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        abt_kshrc.webViewClient = WebViewClient()
        // this will load the url of the website
        abt_kshrc.loadUrl("")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        abt_kshrc.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        abt_kshrc.settings.setSupportZoom(true)
    }
}