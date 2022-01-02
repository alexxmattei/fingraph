package com.example.fingraph.home.ui.news.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.cryptocurrency.CryptoTradingActivity
import com.example.fingraph.models.networking.response.CryptoNewsResponse

class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_news_cryptocurrency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsTitle.text = newsResponse.articles[position].title.toString()
        holder.newsPublishDate.text = newsResponse.articles[position].publishedAt.toString()
        holder.newsAuthor.text = newsResponse.articles[position].author.toString()
        holder.newsDescription.text = newsResponse.articles[position].description.toString()
    }

    override fun getItemCount(): Int {
        return newsResponse.articles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsTitle: TextView
        var newsPublishDate: TextView
        var newsImageSlug: ImageView
        var newsAuthor: TextView
        var newsDescription: TextView
        var readMoreButton: Button

        init {
            newsTitle = itemView.findViewById(R.id.card_news_title)
            newsPublishDate = itemView.findViewById(R.id.card_news_publish_date)
            newsImageSlug = itemView.findViewById(R.id.news_image_cryptocurrency)
            newsAuthor = itemView.findViewById(R.id.card_news_author)
            newsDescription =
                itemView.findViewById(R.id.text_news_description)
            readMoreButton = itemView.findViewById(R.id.news_read_more_button)

            readMoreButton.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, CryptoTradingActivity::class.java).apply {
                    putExtra("POSITION", position)
                    putExtra("TITLE", newsTitle.text)
                    putExtra("DATE", newsPublishDate.text)
                    putExtra("AUHTOR", newsAuthor.text)
                }
                context.startActivity(intent)
            }
        }
    }

    private lateinit var newsResponse: CryptoNewsResponse
    private lateinit var newsTitle: String
    private lateinit var newsPublishDate: String
    private lateinit var newsImageSlug: String
    private lateinit var newsAuthor: String
    private lateinit var newsDescription: String
}