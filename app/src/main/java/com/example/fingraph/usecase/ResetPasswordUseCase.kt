package com.example.fingraph.usecase

import com.example.fingraph.models.base.UseCase
import com.example.fingraph.models.networking.request.ResetPasswordRequest
import com.example.fingraph.models.networking.response.ResetPasswordResponse
import com.example.fingraph.networking.RestClient
import kotlinx.coroutines.Dispatchers

class ResetPasswordUseCase: UseCase<ResetPasswordRequest, ResetPasswordResponse>(Dispatchers.IO) {
    val client = RestClient.INSTANCE

    override suspend fun execute(params: ResetPasswordRequest): ResetPasswordResponse {
        return client.sendEmailResetPassword(params.email)
    }
}