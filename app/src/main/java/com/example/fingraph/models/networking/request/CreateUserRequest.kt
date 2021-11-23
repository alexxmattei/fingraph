package com.example.fingraph.models.networking.request

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)