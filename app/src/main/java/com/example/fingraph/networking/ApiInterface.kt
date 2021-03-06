package com.example.fingraph.networking

import com.example.fingraph.models.networking.request.CreateNewPasswordRequest
import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.models.networking.request.RegisterUserRequest
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.models.networking.response.CryptoMetadataResponse
import com.example.fingraph.models.networking.response.CryptoPriceResponse
import com.example.fingraph.models.networking.response.ResetPasswordResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @POST("api/v1/auth/register")
    suspend fun registerUser(@Body userToCreate: RegisterUserRequest): Response<Any>

    @POST("api/v1/auth/login")
    suspend fun addUser(@Body userToCreate: CreateUserRequest): Response<Any>

    @POST("api/v1/auth/token")
    suspend fun verifyToken(@Body userToken: VerifyTokenRequest): Response<Any>

    @POST("api/v1/reset_pass/requests/{email}")
    suspend fun sendEmailResetPassword(@Path("email") email : String) : ResetPasswordResponse

    @PUT("api/v1/reset_pass/update")
    suspend fun updatePassword(@Body updatePassword: CreateNewPasswordRequest)

    @GET("api/v1/nomics/price/{coin}")
    suspend fun getCoinPriceById(@Path("coin") coin : String): List<CryptoPriceResponse>

    @GET("api/v1/nomics/meta/{coin}")
    suspend fun getCoinMetadataById(@Path("coin") coin : String): List<CryptoMetadataResponse>

    companion object {
        const val BASE_URL = "http://192.168.1.9:8080"
    }
}