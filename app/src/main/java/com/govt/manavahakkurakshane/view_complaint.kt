package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView

class view_complaint : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_complaint)

        findViewById<ImageView>(R.id.btnback).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnview).setOnClickListener {
            startActivity(Intent(this, view_complaint_one::class.java))
        }
    }
}