package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class victim : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var btnBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_victim)

        btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            startActivity(Intent(this, Incident::class.java))
        }
        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }
    }
}