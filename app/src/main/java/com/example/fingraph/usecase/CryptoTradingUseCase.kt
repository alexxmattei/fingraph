package com.example.fingraph.usecase

import android.util.Log
import com.example.fingraph.models.base.UseCase
import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.networking.RestClient
import com.example.fingraph.utils.data.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.lang.IllegalStateException

class CryptoTradingUseCase : UseCase<CreateUserRequest, Unit>(Dispatchers.IO) {
    private val client = RestClient.INSTANCE

    override suspend fun execute(params: CreateUserRequest) {

    }
}