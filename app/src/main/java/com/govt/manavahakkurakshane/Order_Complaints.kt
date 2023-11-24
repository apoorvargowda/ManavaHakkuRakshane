package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Order_Complaints : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_complaints)

        findViewById<ImageView>(R.id.btnback).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.btnview).setOnClickListener {
            startActivity(Intent(this, order_complaint_one::class.java))
        }
    }
}