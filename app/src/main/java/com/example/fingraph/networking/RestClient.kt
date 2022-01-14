package com.example.fingraph.networking

import com.example.fingraph.models.networking.request.CreateNewPasswordRequest
import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.models.networking.request.RegisterUserRequest
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.models.networking.response.CryptoMetadataResponse
import com.example.fingraph.models.networking.response.CryptoPriceResponse
import com.example.fingraph.models.networking.response.ResetPasswordResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface RestClient {
    suspend fun registerUser(newUser: RegisterUserRequest): Response<Any>
    suspend fun addUser(username: CreateUserRequest): Response<Any>
    suspend fun verifyToken(userToken: VerifyTokenRequest): Response<Any>
    suspend fun sendEmailResetPassword(username: String): ResetPasswordResponse
    suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest)
    suspend fun getCoinPriceById(coin: String): List<CryptoPriceResponse>
    suspend fun getCoinMetadataById(coin: String): List<CryptoMetadataResponse>

    companion object {
        val INSTANCE: RestClient = RetrofitRestClient()
    }
}

private class RetrofitRestClient : RestClient {
    private val retrofit: Retrofit
    private val api: ApiInterface

    override suspend fun registerUser(newUser: RegisterUserRequest): Response<Any> {
        return api.registerUser(newUser)
    }

    override suspend fun addUser(
        username: CreateUserRequest
    ): Response<Any> {
        return api.addUser(username)
    }

    override suspend fun verifyToken(userToken: VerifyTokenRequest): Response<Any> {
        return api.verifyToken(userToken)
    }

    override suspend fun sendEmailResetPassword(username: String): ResetPasswordResponse {
        return api.sendEmailResetPassword(username)
    }

    override suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest) {
        return api.updatePassword(createNewPasswordRequest)
    }

    override suspend fun getCoinPriceById(coin: String): List<CryptoPriceResponse> {
        return api.getCoinPriceById(coin)
    }

    override suspend fun getCoinMetadataById(coin: String): List<CryptoMetadataResponse> {
        return api.getCoinMetadataById(coin)
    }

    init {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
        retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        api = retrofit.create(ApiInterface::class.java)
    }

}