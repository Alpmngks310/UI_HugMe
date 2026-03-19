package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var txtGreeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txtGreeting = findViewById(R.id.txtGreeting)

        val username = intent.getStringExtra("username")

        txtGreeting.text = "Hi, $username 👋"
    }
}