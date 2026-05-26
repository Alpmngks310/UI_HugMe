package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var scrollView: ScrollView
    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var txtLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )

        scrollView = findViewById(R.id.scrollView)
        edtUsername = findViewById(R.id.edtUsername)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword)
        btnSignup = findViewById(R.id.btnSignup)
        txtLogin = findViewById(R.id.txtLogin)

        edtUsername.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                scrollView.post {
                    scrollView.smoothScrollTo(0, edtUsername.bottom)
                }
            }
        }

        edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                scrollView.post {
                    scrollView.smoothScrollTo(0, edtEmail.bottom)
                }
            }
        }

        edtPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                scrollView.post {
                    scrollView.smoothScrollTo(0, edtPassword.bottom)
                }
            }
        }

        edtConfirmPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                scrollView.post {
                    scrollView.smoothScrollTo(0, edtConfirmPassword.bottom + 300)
                }
            }
        }

        btnSignup.setOnClickListener {
            if (validateInput()) {
                // Success message sebelum pindah activity
                Toast.makeText(this, "Registrasi Berhasil! Melanjutkan...", Toast.LENGTH_SHORT).show()
                
                val intent = Intent(this, UserDataActivity::class.java)
                intent.putExtra("username", edtUsername.text.toString().trim())
                intent.putExtra("email", edtEmail.text.toString().trim())
                startActivity(intent)
            } else {
                // Warning message jika validasi gagal
                Toast.makeText(this, "Gagal: Harap periksa kembali form pendaftaran", Toast.LENGTH_SHORT).show()
            }
        }

        txtLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun validateInput(): Boolean {
        val username = edtUsername.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        val confirmPassword = edtConfirmPassword.text.toString().trim()

        if (username.isEmpty()) {
            edtUsername.error = "Username tidak boleh kosong"
            edtUsername.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            edtEmail.error = "Email tidak boleh kosong"
            edtEmail.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.error = "Format email tidak valid"
            edtEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            edtPassword.error = "Password tidak boleh kosong"
            edtPassword.requestFocus()
            return false
        }

        if (password.length < 6) {
            edtPassword.error = "Password minimal 6 karakter"
            edtPassword.requestFocus()
            return false
        }

        if (confirmPassword.isEmpty()) {
            edtConfirmPassword.error = "Confirm password tidak boleh kosong"
            edtConfirmPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            edtConfirmPassword.error = "Password tidak sama"
            edtConfirmPassword.requestFocus()
            return false
        }

        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
        return true
    }
}