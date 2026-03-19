package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class UserDataActivity : AppCompatActivity() {

    private lateinit var txtUserInfo: TextView

    private lateinit var tilFullName: TextInputLayout
    private lateinit var tilCity: TextInputLayout
    private lateinit var tilOtherHobby: TextInputLayout

    private lateinit var edtFullName: EditText
    private lateinit var edtCity: EditText
    private lateinit var edtOtherHobby: EditText

    private lateinit var radioGroupGender: RadioGroup

    private lateinit var cbMembaca: CheckBox
    private lateinit var cbMusik: CheckBox
    private lateinit var cbGaming: CheckBox
    private lateinit var cbOlahraga: CheckBox
    private lateinit var cbTravelling: CheckBox
    private lateinit var cbLainnya: CheckBox

    private lateinit var spinnerStatus: Spinner
    private lateinit var btnSubmitData: Button

    private var usernameFromSignup: String = ""
    private var emailFromSignup: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)

        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )

        txtUserInfo = findViewById(R.id.txtUserInfo)

        tilFullName = findViewById(R.id.tilFullName)
        tilCity = findViewById(R.id.tilCity)
        tilOtherHobby = findViewById(R.id.tilOtherHobby)

        edtFullName = findViewById(R.id.edtFullName)
        edtCity = findViewById(R.id.edtCity)
        edtOtherHobby = findViewById(R.id.edtOtherHobby)

        radioGroupGender = findViewById(R.id.radioGroupGender)

        cbMembaca = findViewById(R.id.cbMembaca)
        cbMusik = findViewById(R.id.cbMusik)
        cbGaming = findViewById(R.id.cbGaming)
        cbOlahraga = findViewById(R.id.cbOlahraga)
        cbTravelling = findViewById(R.id.cbTravelling)
        cbLainnya = findViewById(R.id.cbLainnya)

        spinnerStatus = findViewById(R.id.spinnerStatus)
        btnSubmitData = findViewById(R.id.btnSubmitData)

        usernameFromSignup = intent.getStringExtra("username") ?: ""
        emailFromSignup = intent.getStringExtra("email") ?: ""

        txtUserInfo.text = "Halo $usernameFromSignup\n$emailFromSignup"

        setupSpinner()
        setupOtherHobby()
        setupSubmit()
        setupLongPressReset()
    }

    private fun setupSpinner() {
        val statusList = arrayOf(
            "Pilih Status",
            "Mahasiswa",
            "Pelajar",
            "Pekerja",
            "Freelancer"
        )

        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            statusList
        )

        adapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinnerStatus.adapter = adapter
        spinnerStatus.setSelection(0)
    }

    private fun setupOtherHobby() {
        edtOtherHobby.isEnabled = false
        edtOtherHobby.alpha = 0.5f

        cbLainnya.setOnCheckedChangeListener { _, isChecked ->
            edtOtherHobby.isEnabled = isChecked
            edtOtherHobby.alpha = if (isChecked) 1.0f else 0.5f

            if (!isChecked) {
                edtOtherHobby.text.clear()
                tilOtherHobby.error = null
            }
        }
    }

    private fun setupSubmit() {
        btnSubmitData.setOnClickListener {
            if (validateForm()) {
                showConfirmationDialog()
            }
        }
    }

    private fun setupLongPressReset() {
        btnSubmitData.setOnLongClickListener {
            resetForm()
            Toast.makeText(this, "Form berhasil direset", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun validateForm(): Boolean {
        val fullName = edtFullName.text.toString().trim()
        val city = edtCity.text.toString().trim()
        val otherHobby = edtOtherHobby.text.toString().trim()

        tilFullName.error = null
        tilCity.error = null
        tilOtherHobby.error = null

        if (fullName.isEmpty()) {
            tilFullName.error = "Nama lengkap tidak boleh kosong"
            edtFullName.requestFocus()
            return false
        }

        if (city.isEmpty()) {
            tilCity.error = "Kota tidak boleh kosong"
            edtCity.requestFocus()
            return false
        }

        if (radioGroupGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
            return false
        }

        val totalHobby = countSelectedHobbies()
        if (totalHobby < 3) {
            Toast.makeText(this, "Pilih minimal 3 hobi", Toast.LENGTH_SHORT).show()
            return false
        }

        if (cbLainnya.isChecked && otherHobby.isEmpty()) {
            tilOtherHobby.error = "Isi hobi lainnya"
            edtOtherHobby.requestFocus()
            return false
        }

        if (spinnerStatus.selectedItemPosition == 0) {
            Toast.makeText(this, "Pilih status terlebih dahulu", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun countSelectedHobbies(): Int {
        var count = 0

        if (cbMembaca.isChecked) count++
        if (cbMusik.isChecked) count++
        if (cbGaming.isChecked) count++
        if (cbOlahraga.isChecked) count++
        if (cbTravelling.isChecked) count++
        if (cbLainnya.isChecked) count++

        return count
    }

    private fun showConfirmationDialog() {
        val fullName = edtFullName.text.toString().trim()
        val city = edtCity.text.toString().trim()
        val status = spinnerStatus.selectedItem.toString()
        val hobbies = getSelectedHobbiesText()

        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Data")
            .setMessage(
                "Nama: $fullName\n" +
                        "Kota: $city\n" +
                        "Status: $status\n" +
                        "Hobi: $hobbies\n\n" +
                        "Yakin data sudah benar?"
            )
            .setPositiveButton("Ya") { _, _ ->
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_LONG).show()

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", usernameFromSignup)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun getSelectedHobbiesText(): String {
        val hobbies = mutableListOf<String>()

        if (cbMembaca.isChecked) hobbies.add("Membaca")
        if (cbMusik.isChecked) hobbies.add("Musik")
        if (cbGaming.isChecked) hobbies.add("Gaming")
        if (cbOlahraga.isChecked) hobbies.add("Olahraga")
        if (cbTravelling.isChecked) hobbies.add("Travelling")
        if (cbLainnya.isChecked) {
            val other = edtOtherHobby.text.toString().trim()
            if (other.isNotEmpty()) {
                hobbies.add(other)
            } else {
                hobbies.add("Lainnya")
            }
        }

        return hobbies.joinToString(", ")
    }

    private fun resetForm() {
        edtFullName.text.clear()
        edtCity.text.clear()
        edtOtherHobby.text.clear()

        tilFullName.error = null
        tilCity.error = null
        tilOtherHobby.error = null

        radioGroupGender.clearCheck()

        cbMembaca.isChecked = false
        cbMusik.isChecked = false
        cbGaming.isChecked = false
        cbOlahraga.isChecked = false
        cbTravelling.isChecked = false
        cbLainnya.isChecked = false

        spinnerStatus.setSelection(0)

        edtOtherHobby.isEnabled = false
        edtOtherHobby.alpha = 0.5f
    }
}