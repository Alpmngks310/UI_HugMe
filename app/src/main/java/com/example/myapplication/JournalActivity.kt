package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class JournalActivity : AppCompatActivity() {

    private lateinit var rvJournals: RecyclerView
    private lateinit var fabAddJournal: FloatingActionButton
    private lateinit var adapter: JournalAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        db = AppDatabase.getDatabase(this)
        rvJournals = findViewById(R.id.rvJournals)
        fabAddJournal = findViewById(R.id.fabAddJournal)

        adapter = JournalAdapter(emptyList()) { journal ->
            val intent = Intent(this, AddJournalActivity::class.java)
            intent.putExtra("JOURNAL_ID", journal.id)
            startActivity(intent)
        }

        rvJournals.layoutManager = LinearLayoutManager(this)
        rvJournals.adapter = adapter

        fabAddJournal.setOnClickListener {
            startActivity(Intent(this, AddJournalActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadJournals()
    }

    private fun loadJournals() {
        lifecycleScope.launch {
            val journals = db.journalDao().getAllJournals()
            adapter.updateData(journals)
        }
    }
}
