package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Mood
import java.text.SimpleDateFormat
import java.util.*

class MoodAdapter(
    private val moods: List<Mood>
) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    class MoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtIcon: TextView = view.findViewById(R.id.txtMoodIcon)
        val txtLabel: TextView = view.findViewById(R.id.txtMoodLabel)
        val txtTimestamp: TextView = view.findViewById(R.id.txtMoodTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mood, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        val mood = moods[position]
        holder.txtIcon.text = mood.moodIcon
        holder.txtLabel.text = getLabelForMood(mood.moodIcon)
        
        val sdf = SimpleDateFormat("MMM dd, yyyy - hh:mm a", Locale.getDefault())
        holder.txtTimestamp.text = sdf.format(Date(mood.timestamp))
    }

    override fun getItemCount(): Int = moods.size

    private fun getLabelForMood(icon: String): String {
        return when (icon) {
            "😄" -> "Happy"
            "😌" -> "Calm"
            "😢" -> "Sad"
            "😣" -> "Anxious"
            "😰" -> "Panic"
            else -> "Neutral"
        }
    }
}
