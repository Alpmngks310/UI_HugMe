package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var scrollView: ScrollView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtSignup: TextView

    // Data login tetap yang bisa digunakan untuk testing/masuk aplikasi
    private val VALID_EMAIL = "admin@hugme.com"
    private val VALID_PASSWORD = "admin123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollView = findViewById(R.id.scrollView)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtSignup = findViewById(R.id.edtConfirmPassword)

        // Klik SignUp
        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Klik Login
        btnLogin.setOnClickListener {
            if (validateLogin()) {
                performLogin()
            }
        }
    }

    private fun validateLogin(): Boolean {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        var isValid = true

        // 1. Validasi Field Kosong
        if (email.isEmpty()) {
            edtEmail.error = "Email tidak boleh kosong"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 2. Validasi Format Email
            edtEmail.error = "Format email tidak valid"
            isValid = false
        }

        if (password.isEmpty()) {
            edtPassword.error = "Password tidak boleh kosong"
            isValid = false
        }

        return isValid
    }

    private fun performLogin() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        // 3. Validasi Login Gagal (Simulasi Checking Credentials)
        // Gunakan VALID_EMAIL dan VALID_PASSWORD untuk testing sukses
        if (email == VALID_EMAIL && password == VALID_PASSWORD) {
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
            
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("username", email.substringBefore("@"))
            startActivity(intent)
            finish()
        } else {
            // Error message / warning untuk testing
            Toast.makeText(this, "Login Gagal: Email atau Password salah", Toast.LENGTH_LONG).show()
            
            // Memberi visual warning pada field
            edtEmail.requestFocus()
            edtEmail.error = "Periksa kembali email Anda"
            edtPassword.error = "Periksa kembali password Anda"
        }
    }
}