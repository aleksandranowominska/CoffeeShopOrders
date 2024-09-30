package com.example.kotlin_multi_screen_app_basic

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Show_Order : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show_order)

        val takeOrders = intent.getStringExtra(MainActivity.KEY)

        val tV = findViewById<TextView>(R.id.textView)

        tV.text = takeOrders
    }
}