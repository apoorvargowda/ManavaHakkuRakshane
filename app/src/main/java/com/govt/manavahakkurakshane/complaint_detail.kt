package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class complaint_detail : AppCompatActivity() {

    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_detail)

        btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            startActivity(Intent(this, victim::class.java))
        }
    }
}