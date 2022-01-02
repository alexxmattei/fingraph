package com.example.fingraph.networking.news

import com.example.fingraph.networking.ApiInterface
import com.example.fingraph.networking.HeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NewsRestClient {
    suspend fun getNewsCryptoLatest(q: String, sortBy: String, apiKey: String): Response<Any>

    companion object {
        val INSTANCE: NewsRestClient = RetrofitNewsRestClient()
    }
}

private class RetrofitNewsRestClient : NewsRestClient {
    private val retrofit : Retrofit
    private val api : NewsApiInterface

    override suspend fun getNewsCryptoLatest(
        q: String,
        sortBy: String,
        apiKey: String
    ): Response<Any> {
        return api.getNewsCryptoLatest(q, sortBy, apiKey)
    }

    init {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
        retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        api = retrofit.create(NewsApiInterface::class.java)
    }

}
