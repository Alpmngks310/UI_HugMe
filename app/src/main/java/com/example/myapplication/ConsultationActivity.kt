package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Consultant

class ConsultationActivity : AppCompatActivity() {

    private lateinit var rvConsultants: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)

        rvConsultants = findViewById(R.id.rvConsultants)
        rvConsultants.layoutManager = LinearLayoutManager(this)

        val dummyConsultants = listOf(
            Consultant(1, "Dr. Jane Doe", "Clinical Psychologist"),
            Consultant(2, "Dr. John Smith", "Counselor"),
            Consultant(3, "Dr. Sarah Wilson", "Psychotherapist"),
            Consultant(4, "Dr. Michael Brown", "Child Psychologist")
        )

        rvConsultants.adapter = ConsultantAdapter(dummyConsultants) { consultant ->
            Toast.makeText(this, "Starting chat with ${consultant.name}", Toast.LENGTH_SHORT).show()
            // Future: Navigate to ChatActivity
        }
    }
}
