package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var scrollView: ScrollView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollView = findViewById(R.id.scrollView)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtSignup = findViewById(R.id.edtConfirmPassword)

        // Scroll otomatis saat fokus
        edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                scrollView.post { scrollView.smoothScrollTo(0, edtEmail.top) }
            }
        }

        edtPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                scrollView.post { scrollView.smoothScrollTo(0, edtPassword.bottom) }
            }
        }

        // Klik SignUp
        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Klik Login
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            // Lanjutkan proses login
        }
    }
}