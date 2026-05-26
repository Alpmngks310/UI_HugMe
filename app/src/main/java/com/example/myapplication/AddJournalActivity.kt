package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.Journal
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddJournalActivity : AppCompatActivity() {

    private lateinit var txtTitle: TextView
    private lateinit var edtTitle: EditText
    private lateinit var edtContent: EditText
    private lateinit var btnSave: Button
    private lateinit var db: AppDatabase
    private var journalId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_journal)

        db = AppDatabase.getDatabase(this)
        txtTitle = findViewById(R.id.txtAddJournalTitle)
        edtTitle = findViewById(R.id.edtJournalTitle)
        edtContent = findViewById(R.id.edtJournalContent)
        btnSave = findViewById(R.id.btnSaveJournal)

        journalId = intent.getIntExtra("JOURNAL_ID", -1)
        if (journalId != -1) {
            txtTitle.text = "Edit Journal"
            loadJournal(journalId)
        }

        btnSave.setOnClickListener {
            saveJournal()
        }
    }

    private fun loadJournal(id: Int) {
        lifecycleScope.launch {
            val journal = db.journalDao().getJournalById(id)
            journal?.let {
                edtTitle.setText(it.title)
                edtContent.setText(it.content)
            }
        }
    }

    private fun saveJournal() {
        val title = edtTitle.text.toString().trim()
        val content = edtContent.text.toString().trim()

        // 1. Validasi Field Kosong
        var isValid = true
        if (title.isEmpty()) {
            edtTitle.error = "Judul jurnal tidak boleh kosong"
            isValid = false
        }
        if (content.isEmpty()) {
            edtContent.error = "Isi jurnal tidak boleh kosong"
            isValid = false
        }

        if (!isValid) {
            Toast.makeText(this, "Validasi Gagal: Harap lengkapi semua field", Toast.LENGTH_SHORT).show()
            return
        }

        val date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())

        // 2. Error Handling pada Operasi Database (Save Data Journaling)
        lifecycleScope.launch {
            try {
                if (journalId == -1) {
                    val newJournal = Journal(title = title, content = content, date = date)
                    db.journalDao().insertJournal(newJournal)
                    Toast.makeText(this@AddJournalActivity, "Jurnal Berhasil Disimpan!", Toast.LENGTH_SHORT).show()
                } else {
                    val existingJournal = db.journalDao().getJournalById(journalId)
                    existingJournal?.let {
                        val updatedJournal = it.copy(title = title, content = content, date = date)
                        db.journalDao().updateJournal(updatedJournal)
                        Toast.makeText(this@AddJournalActivity, "Jurnal Berhasil Diperbarui!", Toast.LENGTH_SHORT).show()
                    }
                }
                finish()
            } catch (e: Exception) {
                // Warning / Error message jika gagal simpan ke DB
                Toast.makeText(this@AddJournalActivity, "Gagal menyimpan jurnal: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
