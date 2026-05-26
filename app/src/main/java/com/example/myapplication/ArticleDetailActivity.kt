package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val title = intent.getStringExtra("ARTICLE_TITLE") ?: "Article"
        val content = intent.getStringExtra("ARTICLE_CONTENT") ?: ""

        findViewById<TextView>(R.id.txtDetailArticleTitle).text = title
        findViewById<TextView>(R.id.txtDetailArticleContent).text = content
    }
}
