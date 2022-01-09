package com.example.fingraph.models.networking.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CryptoMetadataResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("original_symbol")
    val original_symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("website_url")
    val website_url: String,
    @SerializedName("logo_url")
    val logo_url: String,
    @SerializedName("blog_url")
    val blog_url: String,
    @SerializedName("discord_url")
    val discord_url: String,
    @SerializedName("facebook_url")
    val facebook_url: String,
    @SerializedName("github_url")
    val github_url: String,
    @SerializedName("medium_url")
    val medium_url: String,
    @SerializedName("reddit_url")
    val reddit_url: String,
    @SerializedName("telegram_url")
    val telegram_url: String,
    @SerializedName("twitter_url")
    val twitter_url: String,
    @SerializedName("whitepaper_url")
    val whitepaper_url: String,
    @SerializedName("youtube_url")
    val youtube_url: String,
    @SerializedName("bitcointalk_url")
    val bitcointalk_url: String,
    @SerializedName("block_explorer_url")
    val block_explorer_url: String,
    @SerializedName("markets_count")
    val markets_count: Int,
    @SerializedName("cryptocontrol_coin_id")
    val cryptocontrol_coin_id: String,
    @SerializedName("used_for_pricing")
    val used_for_pricing: Boolean,
    @SerializedName("platform_currency_id")
    val platform_currency_id: String,
    @SerializedName("platform_contract_address")
    val platform_contract_address: String
) : Serializable