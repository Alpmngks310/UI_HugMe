package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Article

class ArticleListActivity : AppCompatActivity() {

    private lateinit var rvArticles: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        rvArticles = findViewById(R.id.rvArticles)
        rvArticles.layoutManager = LinearLayoutManager(this)

        val dummyArticles = listOf(
            Article(1, "Understanding Anxiety", "Anxiety is a normal response to stress..."),
            Article(2, "Benefits of Meditation", "Meditation can help reduce stress and improve focus..."),
            Article(3, "Healthy Sleep Habits", "Getting enough sleep is crucial for mental health..."),
            Article(4, "How to Manage Stress", "Stress management involves various techniques...")
        )

        rvArticles.adapter = ArticleAdapter(dummyArticles) { article ->
            val intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("ARTICLE_TITLE", article.title)
            intent.putExtra("ARTICLE_CONTENT", article.content)
            startActivity(intent)
        }
    }
}
