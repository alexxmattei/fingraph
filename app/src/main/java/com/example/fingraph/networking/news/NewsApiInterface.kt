package com.example.fingraph.networking.news

import com.example.fingraph.models.networking.response.CryptoNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {
    @GET("everything")
    suspend fun getNewsCryptoLatest(
        @Query("q") q: String = BASE_QUERY,
        @Query("sortBy") sortBy: String = SORTED,
        @Query("apiKey") apiKey: String = API_KEY
    ): CryptoNewsResponse


    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val BASE_QUERY = "crypto"
        const val API_KEY = "7f171a65efec43c8898bd882cab1b3b6"
        const val SORTED = "publishedAt"
    }
}