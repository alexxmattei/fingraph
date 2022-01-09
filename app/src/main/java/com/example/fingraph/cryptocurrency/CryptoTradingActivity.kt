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
    private val initialResponse = CryptoNewsResponse(
        status = "",
        totalResults = 0,
        articles = listOf(Article(
            source = ArticleSource(id = "", name = ""),
            author = "",
            title = "",
            description = "",
            url = "",
            urlToImage = "",
            publishedAt = "",
            content = ""
        ))
    )

    private var newsResponse: MutableLiveData<CryptoNewsResponse> = MutableLiveData<CryptoNewsResponse>(initialResponse)
    private var newsResponseString: MutableLiveData<String> = MutableLiveData<String>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_trading)
        setCandleStickChart()

        val button = findViewById<Button>(R.id.loadCryptoData)

        button.setOnClickListener {
            lifecycleScope.launch {
                fetchCryptoNews()
            }

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

    private suspend fun loadCryptoNews() {
        val response = NewsRestClient.INSTANCE.getNewsCryptoLatest(
            q = NewsApiInterface.BASE_QUERY,
            sortBy = NewsApiInterface.SORTED,
            apiKey = NewsApiInterface.API_KEY
        )
        System.out.println(response.articles[0])
        newsResponseString.postValue(response.articles.toString())
    }

    private fun fetchCryptoNews() {
        val newsFetchJob = Job()
        val errorHandler = CoroutineExceptionHandler { _, _ ->
            Toast.makeText(applicationContext, "Error loading data", Toast.LENGTH_LONG).show()
        }
        val scope = CoroutineScope(newsFetchJob + Dispatchers.Main)
        scope.launch(errorHandler) {
            val response: CryptoNewsResponse = NewsRestClient.INSTANCE.getNewsCryptoLatest(
                q = NewsApiInterface.BASE_QUERY,
                sortBy = NewsApiInterface.SORTED,
                apiKey = NewsApiInterface.API_KEY
            )
            System.out.println(response.articles)
        }
    }

    private fun deserializeNewsData() {
        val gson = Gson()
    }
}