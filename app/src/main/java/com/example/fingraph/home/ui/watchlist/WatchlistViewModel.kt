package com.example.fingraph.home.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WatchlistViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Crypto Watchlist"
    }
    val text: LiveData<String> = _text
}