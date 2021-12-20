package com.example.fingraph.home.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "My Profile"
    }
    val text: LiveData<String> = _text
}