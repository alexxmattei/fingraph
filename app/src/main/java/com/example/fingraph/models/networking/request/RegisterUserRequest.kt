package com.example.fingraph.models.networking.request

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("callingName")
    val callingName: String
)
