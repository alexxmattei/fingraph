package com.example.fingraph.models.networking.response

data class CryptoNewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: ArticleSource,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String
)

data class ArticleSource(
    val id: String?,
    val name: String?
)