package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.Mood
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var txtGreeting: TextView
    private lateinit var moodHappy: TextView
    private lateinit var moodNeutral: TextView
    private lateinit var moodSad: TextView
    private lateinit var moodAnxious: TextView
    private lateinit var moodPanic: TextView
    private lateinit var cardMoodStats: LinearLayout
    private lateinit var cardJournaling: LinearLayout
    private lateinit var cardArticles: LinearLayout
    private lateinit var cardConsultation: LinearLayout

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        db = AppDatabase.getDatabase(this)

        txtGreeting = findViewById(R.id.txtGreeting)
        moodHappy = findViewById(R.id.moodHappy)
        moodNeutral = findViewById(R.id.moodNeutral)
        moodSad = findViewById(R.id.moodSad)
        moodAnxious = findViewById(R.id.moodAnxious)
        moodPanic = findViewById(R.id.moodPanic)
        cardMoodStats = findViewById(R.id.cardMoodStats)
        cardJournaling = findViewById(R.id.cardJournaling)
        cardArticles = findViewById(R.id.cardArticles)
        cardConsultation = findViewById(R.id.cardConsultation)

        val username = intent.getStringExtra("username") ?: "User"
        txtGreeting.text = "Hi, $username 👋"

        setupMoodListeners()
        setupCardListeners()
    }

    private fun setupMoodListeners() {
        moodHappy.setOnClickListener { saveMood("😄") }
        moodNeutral.setOnClickListener { saveMood("😌") }
        moodSad.setOnClickListener { saveMood("😢") }
        moodAnxious.setOnClickListener { saveMood("😣") }
        moodPanic.setOnClickListener { saveMood("😰") }
    }

    private fun setupCardListeners() {
        cardMoodStats.setOnClickListener {
            startActivity(Intent(this, MoodHistoryActivity::class.java))
        }

        cardJournaling.setOnClickListener {
            startActivity(Intent(this, JournalActivity::class.java))
        }

        cardArticles.setOnClickListener {
            startActivity(Intent(this, ArticleListActivity::class.java))
        }

        cardConsultation.setOnClickListener {
            startActivity(Intent(this, ConsultationActivity::class.java))
        }
    }

    private fun saveMood(icon: String) {
        // Error Handling pada Save Data Mood Tracker
        lifecycleScope.launch {
            try {
                val mood = Mood(moodIcon = icon)
                db.moodDao().insertMood(mood)
                
                // Toast sebagai konfirmasi sukses untuk testing
                Toast.makeText(this@HomeActivity, "Mood $icon berhasil dicatat!", Toast.LENGTH_SHORT).show()
                
                // Opsional: Langsung arahkan ke history untuk verifikasi data
                // startActivity(Intent(this@HomeActivity, MoodHistoryActivity::class.java))
            } catch (e: Exception) {
                // Error message / warning jika database gagal
                Toast.makeText(this@HomeActivity, "Gagal mencatat mood: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}