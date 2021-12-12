package com.example.fingraph.utils.views

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun EditText.getTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    addTextChangedListener {
        query.value = it.toString()
    }
    return query
}

fun TabLayout.onTabSelected(onSelected: (TabLayout.Tab?) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
            TODO("Not yet implemented")
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            TODO("Not yet implemented")
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            onSelected(tab)
        }
    })
}