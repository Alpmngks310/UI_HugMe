package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.AppDatabase
import kotlinx.coroutines.launch

class MoodHistoryActivity : AppCompatActivity() {

    private lateinit var rvMoodHistory: RecyclerView
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_history)

        db = AppDatabase.getDatabase(this)
        rvMoodHistory = findViewById(R.id.rvMoodHistory)
        rvMoodHistory.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val moods = db.moodDao().getAllMoods()
            rvMoodHistory.adapter = MoodAdapter(moods)
        }
    }
}
