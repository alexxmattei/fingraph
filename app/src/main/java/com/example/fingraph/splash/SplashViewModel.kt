package com.example.fingraph.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fingraph.base.BaseViewModel
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.usecase.VerifyTokenUseCase
import com.example.fingraph.models.base.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel: BaseViewModel() {
    private val verifyTokenUseCase = VerifyTokenUseCase()
    val isTokenValid = MutableLiveData<Boolean>()

    fun verifyToken(userToken: VerifyTokenRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = verifyTokenUseCase(userToken)
            when (result) {
                is Result.Error -> {
                    isTokenValid.postValue(false)
                    setError(result.value)
                }
                is Result.Success -> isTokenValid.postValue(true)
            }
        }
    }
}