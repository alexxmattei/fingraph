package com.example.fingraph.models.networking.response

import com.google.gson.annotations.SerializedName

data class CryptoPriceResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("logo_url")
    val logo_url: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("priceDate")
    val priceDate: String,
    @SerializedName("priceTimestamp")
    val priceTimestamp: String,
    @SerializedName("circulatingSupply")
    val circulatingSupply: String,
    @SerializedName("maxSupply")
    val maxSupply: String,
    @SerializedName("marketCap")
    val marketCap: String,
    @SerializedName("marketCapDominance")
    val marketCapDominance: String,
    @SerializedName("numExchanges")
    val numExchanges: String,
    @SerializedName("numPairs")
    val numPairs: String,
    @SerializedName("numPairsUnmapped")
    val numPairsUnmapped: String,
    @SerializedName("firstCandle")
    val firstCandle: String,
    @SerializedName("firstTrade")
    val firstTrade: String,
    @SerializedName("firstOrderBook")
    val firstOrderBook: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("high")
    val high: Double,
    @SerializedName("highTimestamp")
    val highTimestamp: String,
    @SerializedName("oneHour")
    val oneHour: OneHour
)

data class OneHour(
    @SerializedName("volume")
    val volume: String,
    @SerializedName("priceChange")
    val priceChange: Double,
    @SerializedName("priceChangePct")
    val priceChangePct: Double,
    @SerializedName("volumeChange")
    val volumeChange: Double,
    @SerializedName("volumeChangePct")
    val volumeChangePct: Double,
    @SerializedName("marketCapChange")
    val marketCapChange: Double,
    @SerializedName("marketCapChangePct")
    val marketCapChangePct: Double
)
