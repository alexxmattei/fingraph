package com.example.fingraph.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fingraph.base.BaseViewModel
import com.example.fingraph.models.base.Result
import com.example.fingraph.models.networking.request.CreateUserRequest
import com.example.fingraph.usecase.LoginUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val loginUserUseCase = LoginUserUseCase()
    val loginSuccess = MutableLiveData<Boolean>()

    fun loginUser(user: CreateUserRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = loginUserUseCase(user)) {
                is Result.Error -> setError(result.value)
                is Result.Success -> loginSuccess.postValue(true)
            }
        }
    }
}