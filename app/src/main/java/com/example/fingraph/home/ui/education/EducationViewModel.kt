package com.example.fingraph.home.ui.education

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EducationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Education Section"
    }
    val text: LiveData<String> = _text
}