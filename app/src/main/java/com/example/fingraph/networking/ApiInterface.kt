package com.example.fingraph.networking

import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/v1/auth/login")
    suspend fun addUser(@Body userToCreate: CreateUserRequest): Response<Any>

    @POST("api/v1/auth/token")
    suspend fun verifyToken(@Body userToken: VerifyTokenRequest): Response<Any>

    companion object {
        const val BASE_URL = "http://localhost:8080"
    }

}