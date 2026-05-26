package com.example.myapplication.data

data class Article(
    val id: Int,
    val title: String,
    val content: String,
    val imageUrl: String = ""
)
