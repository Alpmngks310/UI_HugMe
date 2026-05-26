package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Journal

class JournalAdapter(
    private var journals: List<Journal>,
    private val onItemClick: (Journal) -> Unit
) : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    class JournalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txtJournalTitle)
        val txtDate: TextView = view.findViewById(R.id.txtJournalDate)
        val txtContent: TextView = view.findViewById(R.id.txtJournalContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_journal, parent, false)
        return JournalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val journal = journals[position]
        holder.txtTitle.text = journal.title
        holder.txtDate.text = journal.date
        holder.txtContent.text = journal.content
        holder.itemView.setOnClickListener { onItemClick(journal) }
    }

    override fun getItemCount(): Int = journals.size

    fun updateData(newJournals: List<Journal>) {
        journals = newJournals
        notifyDataSetChanged()
    }
}
