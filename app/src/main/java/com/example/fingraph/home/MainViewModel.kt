package com.example.fingraph.home

import com.example.fingraph.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow


class MainViewModel : BaseViewModel() {
    var isSearchVisible = false

    // TODO add introduction boolean step to shared preferences manager
    val showAppIntroduction: Boolean = false
    val noInternet = MutableSharedFlow<Boolean?>()
    private val searchTermFlow = MutableStateFlow("")

    private fun getAllTags() {
        // TODO create dictionary of crypto search tags by name (for at least 100 cryptocurrencies)
    }

    private fun filterUsingSearchTerm(tags: List<String>?, searchTerm: String): Boolean {
        if (tags.isNullOrEmpty()) return true
        if (searchTerm.isBlank()) return true
        tags.forEach { tag ->
            if (tag.lowercase().contains(searchTerm)) return true
        }
        return false
    }

    // TODO replace String argument in the Flow List with cryptocurrency model
    private fun onCryptoAssetsFlowReceived(
        cryptoFlow: Flow<List<String>>,
        flowToEmit: MutableSharedFlow<List<String>>,
        updateCurrentCryptoList: () -> Unit
    ) {
        // TODO implement treatment of received flow from backend when calling home activity
    }

    fun filterCryptocurrenciesByTag(tag: String) {
        searchTermFlow.value = tag
    }

}