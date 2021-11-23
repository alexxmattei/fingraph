package com.example.fingraph.usecase

import android.util.Log
import com.example.fingraph.models.base.UseCase
import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.networking.RestClient
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.lang.IllegalStateException

class LoginUserUseCase : UseCase<CreateUserRequest, Unit>(Dispatchers.IO) {
    private val client = RestClient.INSTANCE

    override suspend fun execute(params: CreateUserRequest) {
        val response = client.addUser(params)
        val tokenResponse = response.body().toString()
        val tokenValue = JSONObject(tokenResponse)
        val authToken = VerifyTokenRequest(tokenValue["token"].toString())
        Log.i("Token value at login: ", authToken.token)
        if(!response.isSuccessful) {
            throw IllegalStateException("Bad Credentials")
        }
        //TODO use SharedPreferences to store user session token
    }
}