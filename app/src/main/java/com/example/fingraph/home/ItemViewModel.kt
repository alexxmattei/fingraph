package com.example.fingraph.home

import android.content.ClipData
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fingraph.models.networking.response.CryptoNewsResponse
import com.example.fingraph.networking.news.NewsApiInterface
import com.example.fingraph.networking.news.NewsRestClient
import com.example.fingraph.utils.data.SharedPreferencesManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ItemViewModel: ViewModel() {
    val cryptoNewsFlowData: LiveData<CryptoNewsResponse> = MutableLiveData<CryptoNewsResponse>()
    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        Log.i("Main", "Error loading news data!")
    }

    fun fetchCryptoNewsData() = viewModelScope.launch(errorHandler) {
        val response: CryptoNewsResponse = NewsRestClient.INSTANCE.getNewsCryptoLatest(
            q = NewsApiInterface.BASE_QUERY,
            sortBy = NewsApiInterface.SORTED,
            apiKey = NewsApiInterface.API_KEY
        )
        (cryptoNewsFlowData as MutableLiveData).value = response
        SharedPreferencesManager.currentNews = cryptoNewsFlowData.value!!
    }
}