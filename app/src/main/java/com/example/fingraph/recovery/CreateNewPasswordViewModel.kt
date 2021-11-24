package com.example.fingraph.recovery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fingraph.base.BaseViewModel
import com.example.fingraph.models.base.Result
import com.example.fingraph.models.networking.request.CreateNewPasswordRequest
import com.example.fingraph.usecase.CreateNewPasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateNewPasswordViewModel : BaseViewModel() {
    private val createNewPasswordUseCase = CreateNewPasswordUseCase()
    val isSuccess = MutableLiveData<Boolean>()

    fun updatePassword(updatePass: CreateNewPasswordRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = createNewPasswordUseCase(updatePass)) {
                is Result.Error -> setError(result.value)
                is Result.Success -> isSuccess.postValue(true)
            }
        }
    }
}