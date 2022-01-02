package com.example.fingraph.models.networking.request.news

import com.google.gson.annotations.SerializedName

data class GetLatestNewsRequest(
    @SerializedName("q")
    val q: String,
    @SerializedName("sortBy")
    val sortBy: String,
    @SerializedName("apiKey")
    val apiKey: String
)