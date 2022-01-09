package com.example.fingraph.home

import android.content.ClipData
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fingraph.models.networking.response.CryptoMetadataResponse
import com.example.fingraph.models.networking.response.CryptoNewsResponse
import com.example.fingraph.models.networking.response.CryptoPriceResponse
import com.example.fingraph.networking.RestClient
import com.example.fingraph.networking.news.NewsApiInterface
import com.example.fingraph.networking.news.NewsRestClient
import com.example.fingraph.utils.data.SharedPreferencesManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ItemViewModel: ViewModel() {
    val cryptoNewsFlowData: LiveData<CryptoNewsResponse> = MutableLiveData()
    val cryptoPriceFlowData: LiveData<List<CryptoPriceResponse>> = MutableLiveData()
    val cryptoMetadataFlowData: LiveData<List<CryptoMetadataResponse>> = MutableLiveData()
    private val newsErrorHandler = CoroutineExceptionHandler { _, _ ->
        Log.i("Main", "Error loading news data!")
    }
    private val priceErrorHandler = CoroutineExceptionHandler { _, _ ->
        Log.i("Main", "Error loading price data!")
    }
    private val metadataErrorHandler = CoroutineExceptionHandler { _, _ ->
        Log.i("Main", "Error loading coin metadata!")
    }

    fun fetchCryptoNewsData() = viewModelScope.launch(newsErrorHandler) {
        val response: CryptoNewsResponse = NewsRestClient.INSTANCE.getNewsCryptoLatest(
            q = NewsApiInterface.BASE_QUERY,
            sortBy = NewsApiInterface.SORTED,
            apiKey = NewsApiInterface.API_KEY
        )
        (cryptoNewsFlowData as MutableLiveData).value = response
        SharedPreferencesManager.currentNews = cryptoNewsFlowData.value!!
    }

    fun fetchCryptoPriceData() = viewModelScope.launch(priceErrorHandler) {
        val response: List<CryptoPriceResponse> = RestClient.INSTANCE.getCoinPriceById(
            SharedPreferencesManager.defaultCryptoList
        )
        (cryptoPriceFlowData as MutableLiveData).value = response
        SharedPreferencesManager.currentPrices = cryptoPriceFlowData.value!!
    }

    fun fetchCryptoMetadata() = viewModelScope.launch(metadataErrorHandler) {
        val response: List<CryptoMetadataResponse> = RestClient.INSTANCE.getCoinMetadataById(
            SharedPreferencesManager.defaultCryptoList
        )
        (cryptoMetadataFlowData as MutableLiveData).value = response
        SharedPreferencesManager.currentEducationList = cryptoMetadataFlowData.value!!
    }
}