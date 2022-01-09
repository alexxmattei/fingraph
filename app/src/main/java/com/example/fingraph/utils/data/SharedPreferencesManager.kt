package com.example.fingraph.utils.data

import android.content.Context
import com.example.fingraph.models.UserData
import com.example.fingraph.models.networking.request.VerifyTokenRequest
import com.example.fingraph.models.networking.response.*


class SharedPreferencesManager private constructor(private val domainContext: Context) {
    val getLoggedInToken: Boolean
        get() {
            val sharedPreferences =
                domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("Token", null) != null
        }

    val userAppData: UserData
        get() {
            val sharedPreferences =
                domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            return UserData(
                sharedPreferences.getString("Email", "") ?: "",
                sharedPreferences.getString("Token", "") ?: ""
            )
        }

    fun saveUserEmail(user: UserData) {
        val sharedPreferences =
            domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("Email", user.email)

        editor.apply()
    }

    fun saveUserDataToken(userToken: VerifyTokenRequest) {
        val sharedPreferencesManager = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferencesManager.edit()

        editor.putString("Token", userToken.token)
        editor.apply()
    }

    fun saveLatestNews(cryptoNewsLatest: CryptoNewsResponse) {
        val sharedPreferencesManager = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferencesManager.edit()

        currentNews = cryptoNewsLatest
        editor.apply()
    }

    fun clearSharedPreferences() {
        val sharedPreferences =
            domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREFERENCE_NAME = "mainPrefs"
        var domainPreferenceInstance: SharedPreferencesManager? = null
        var currentNews: CryptoNewsResponse = CryptoNewsResponse(
            status = "",
            totalResults = 0,
            articles = listOf(
                Article(
                    source = ArticleSource(id = "", name = ""),
                    author = "",
                    title = "",
                    description = "",
                    url = "",
                    urlToImage = "",
                    publishedAt = "",
                    content = ""
                )
            )
        )
        var currentPrices: List<CryptoPriceResponse> = listOf(CryptoPriceResponse(
            id = "",
            currency = "",
            symbol = "",
            name = "",
            logo_url = "",
            status = "",
            price = 0.0,
            priceDate = "",
            priceTimestamp = "",
            circulatingSupply = "",
            maxSupply = "",
            marketCap = "",
            marketCapDominance = "",
            numExchanges = "",
            numPairs = "",
            numPairsUnmapped = "",
            firstCandle = "",
            firstTrade = "",
            firstOrderBook = "",
            rank = "",
            high = 0.0,
            highTimestamp = "",
            oneHour = OneHour(
                volume = "",
                priceChange = 0.0,
                priceChangePct = 0.0,
                volumeChange = 0.0,
                volumeChangePct = 0.0,
                marketCapChange = 0.0,
                marketCapChangePct = 0.0
            )
        ))
        var defaultCryptoList = "BTC,ETH,BNB,USDT,SOL,ADA,XRP,DOT,USDC,DOGE,SHIB,LUNA,AVAX,LTC,LINK,UNI,ALGO,XLM"

        @Synchronized
        fun getInstance(domainContext: Context): SharedPreferencesManager {
            if (domainPreferenceInstance == null) {
                domainPreferenceInstance = SharedPreferencesManager(domainContext)
            }
            return domainPreferenceInstance as SharedPreferencesManager
        }
    }

}