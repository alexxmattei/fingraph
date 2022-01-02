package com.example.fingraph.home.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fingraph.base.BaseViewModel
import com.example.fingraph.models.base.Result
import com.example.fingraph.models.networking.request.news.GetLatestNewsRequest
import com.example.fingraph.models.networking.response.CryptoNewsResponse
import com.example.fingraph.usecase.news.GetLatestNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : BaseViewModel() {

    private val latestNewsUseCase = GetLatestNewsUseCase()
    val newsRequestResult = MutableLiveData<CryptoNewsResponse>()

    fun getNewsLatest(params: GetLatestNewsRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = latestNewsUseCase(params)) {
                is Result.Error -> setError(result.value)
                is Result.Success -> newsRequestResult.postValue(result)
            }
        }
    }
}

private fun <T> MutableLiveData<T>.postValue(result: Result.Success<Unit>) {
    return result.value
}

