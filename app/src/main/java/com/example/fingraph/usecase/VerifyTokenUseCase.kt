package com.example.fingraph.usecase

import android.util.Log
import com.example.fingraph.models.base.UseCase
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.networking.RestClient
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.lang.IllegalStateException

class VerifyTokenUseCase: UseCase<VerifyTokenRequest, Unit>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: VerifyTokenRequest) {
        val response = restClient.verifyToken(params)
        val tokenResponse = response.body().toString()
        val isValidToken = JSONObject(tokenResponse)
        Log.i("Token at splash", isValidToken["valid"].toString())

        if(!(isValidToken["token"] as Boolean)) {
            throw IllegalStateException("Please log in first!")
        }
    }
}