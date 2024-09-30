package com.example.kotlin_multi_screen_app_basic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //creating a key
    //using companion object in Kotlin which works like static keyword in Java
    companion object{
        const val KEY = "com.example.kotlin_multi_screen_app_basic.MainActivity.KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editT1 = findViewById<EditText>(R.id.eTT1)
        val editT2 = findViewById<EditText>(R.id.eTT2)
        val editT3 = findViewById<EditText>(R.id.eTT3)
        val editT4 = findViewById<EditText>(R.id.eTT4)
        val btnOrder = findViewById<Button>(R.id.buttonOrder)

        btnOrder.setOnClickListener{

            val ordersList = "I ordered\n1. "+editT1.text.toString() + "\n2. " + editT2.text.toString() + "\n3. " + editT3.text.toString() + "\n4. " + editT4.text.toString()

            intent =  Intent(this, Show_Order::class.java)

            intent.putExtra(KEY, ordersList)

            startActivity(intent)

        }



    }
}