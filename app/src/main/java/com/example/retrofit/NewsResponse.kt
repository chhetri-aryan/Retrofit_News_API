package com.example.retrofit

public final data class NewsResponse(
    val data: List<Article>,
)

data class Article(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val source: String,
    val published_at: String,
)
