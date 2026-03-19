package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var btnGetStarted: Button
    private lateinit var tvLoginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyApplication)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnGetStarted = findViewById(R.id.btnGetStarted)
        tvLoginLink = findViewById(R.id.tvLoginLink)

        // Klik GET STARTED → ke SignUpActivity
        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()  // penting! agar WelcomeActivity hilang dari back stack
        }

        // Klik LOGIN → ke MainActivity
        tvLoginLink.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()  // juga hilangkan WelcomeActivity dari back stack
        }
    }
}