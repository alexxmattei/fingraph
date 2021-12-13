package com.example.fingraph.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val error = MutableLiveData<String>()

    protected fun setError(ex: Exception) {
        error.postValue(ex.message ?: "Unknown error occurred");
    }
}