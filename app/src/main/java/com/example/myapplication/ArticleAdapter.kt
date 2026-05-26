package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Article

class ArticleAdapter(
    private val articles: List<Article>,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txtArticleTitle)
        val txtSnippet: TextView = view.findViewById(R.id.txtArticleSnippet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.txtTitle.text = article.title
        holder.txtSnippet.text = if (article.content.length > 100) {
            article.content.substring(0, 100) + "..."
        } else {
            article.content
        }
        holder.itemView.setOnClickListener { onItemClick(article) }
    }

    override fun getItemCount(): Int = articles.size
}
