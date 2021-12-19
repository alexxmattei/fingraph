package com.example.fingraph.networking

import com.example.fingraph.models.networking.request.CreateNewPasswordRequest
import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.models.networking.response.ResetPasswordResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {
    @POST("api/v1/auth/login")
    suspend fun addUser(@Body userToCreate: CreateUserRequest): Response<Any>

    @POST("api/v1/auth/token")
    suspend fun verifyToken(@Body userToken: VerifyTokenRequest): Response<Any>

    @POST("api/v1/reset_pass/requests/{email}")
    suspend fun sendEmailResetPassword(@Path("email") email : String) : ResetPasswordResponse

    @PUT("api/v1/reset_pass/update")
    suspend fun updatePassword(@Body updatePassword: CreateNewPasswordRequest)

    companion object {
        const val BASE_URL = "http://192.168.1.5:8080"
    }

}