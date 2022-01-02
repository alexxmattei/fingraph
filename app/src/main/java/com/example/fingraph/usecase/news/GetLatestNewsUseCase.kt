package com.example.fingraph.usecase.news

import android.util.Log
import com.example.fingraph.models.base.UseCase
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.models.networking.request.news.GetLatestNewsRequest
import com.example.fingraph.models.networking.response.CryptoNewsResponse
import com.example.fingraph.networking.news.NewsRestClient
import com.example.fingraph.utils.data.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.lang.IllegalStateException

class GetLatestNewsUseCase : UseCase<GetLatestNewsRequest, Unit>(Dispatchers.IO) {
    private val client = NewsRestClient.INSTANCE

    override suspend fun execute(params: GetLatestNewsRequest) {
        val response = client.getNewsCryptoLatest(params.q, params.sortBy, params.apiKey)
        val newsResponse = response.body().toString()
        val newsResponseValue = JSONObject(newsResponse)
        Log.i("News", newsResponseValue["articles"].toString())
        if(!response.isSuccessful) {
            throw IllegalStateException("Something Went Wrong!")
        }
    }
}