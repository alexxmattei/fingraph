package com.example.fingraph.cryptocurrency

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.fingraph.R
import com.example.fingraph.models.crypto.CandleEntry
import com.example.fingraph.models.networking.response.Article
import com.example.fingraph.models.networking.response.ArticleSource
import com.example.fingraph.models.networking.response.CryptoNewsResponse
import com.example.fingraph.networking.news.NewsApiInterface
import com.example.fingraph.networking.news.NewsRestClient
import com.google.gson.Gson
import kotlinx.coroutines.*

class CryptoTradingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_trading)
        setCandleStickChart()

        val button = findViewById<Button>(R.id.loadCryptoData)

        button.setOnClickListener {
            Toast.makeText(applicationContext, "Loading cryto asset...", Toast.LENGTH_LONG).show()
        }
    }

    fun setCandleStickChart() {
        val xValues = ArrayList<String>()
        xValues.add("12:00 AM")
        xValues.add("2:00 AM")
        xValues.add("4:00 AM")
        xValues.add("6:00 AM")
        xValues.add("8:00 AM")
        xValues.add("10:00 AM")
        xValues.add("12:00 PM")
        xValues.add("2:00 PM")
        xValues.add("4:00 PM")
        xValues.add("6:00 PM")
        xValues.add("8:00 PM")
        xValues.add("10:00 PM")

        val candleStickEntry = ArrayList<CandleEntry>()
    }

}