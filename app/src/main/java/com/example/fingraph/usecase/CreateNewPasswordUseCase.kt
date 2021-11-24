package com.example.fingraph.usecase

import com.example.fingraph.models.base.UseCase
import com.example.fingraph.models.networking.request.CreateNewPasswordRequest
import com.example.fingraph.networking.RestClient
import kotlinx.coroutines.Dispatchers

class CreateNewPasswordUseCase : UseCase<CreateNewPasswordRequest, Unit>(Dispatchers.IO) {
    private val client = RestClient.INSTANCE

    override suspend fun execute(params: CreateNewPasswordRequest) {
        client.updatePassword(params)
    }
}