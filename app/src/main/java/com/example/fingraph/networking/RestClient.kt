package com.example.fingraph.networking

import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface RestClient {
    suspend fun addUser(username: CreateUserRequest): Response<Any>
    suspend fun verifyToken(userToken: VerifyTokenRequest): Response<Any>

    companion object {
        val INSTANCE: RestClient = RetrofitRestClient()
    }
}

private class RetrofitRestClient : RestClient {
    private val retrofit : Retrofit
    private val api : ApiInterface

    override suspend fun addUser(
        username: CreateUserRequest
    ): Response<Any> {
        return api.addUser(username)
    }

    override suspend fun verifyToken(userToken: VerifyTokenRequest): Response<Any> {
        return api.verifyToken(userToken)
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