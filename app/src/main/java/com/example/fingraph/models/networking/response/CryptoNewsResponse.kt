package com.example.fingraph.models.networking.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CryptoNewsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    var articles: List<Article>
) : Serializable

data class Article(
    @SerializedName("source")
    val source: ArticleSource,
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String
) : Serializable

data class ArticleSource(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) : Serializable