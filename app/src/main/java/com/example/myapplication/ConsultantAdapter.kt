package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Consultant

class ConsultantAdapter(
    private val consultants: List<Consultant>,
    private val onItemClick: (Consultant) -> Unit
) : RecyclerView.Adapter<ConsultantAdapter.ConsultantViewHolder>() {

    class ConsultantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtConsultantName)
        val txtSpecialty: TextView = view.findViewById(R.id.txtConsultantSpecialty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consultant, parent, false)
        return ConsultantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsultantViewHolder, position: Int) {
        val consultant = consultants[position]
        holder.txtName.text = consultant.name
        holder.txtSpecialty.text = consultant.specialty
        holder.itemView.setOnClickListener { onItemClick(consultant) }
    }

    override fun getItemCount(): Int = consultants.size
}
