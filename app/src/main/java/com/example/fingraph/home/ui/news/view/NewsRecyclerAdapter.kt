package com.example.fingraph.home.ui.news.view

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.cryptocurrency.CryptoTradingActivity
import com.example.fingraph.models.networking.response.CryptoNewsResponse
import com.squareup.picasso.Picasso

private const val NO_TITLE_AVAILABLE = "No title available"
private const val NO_PUBLISH_DATE_AVAILABLE = "No publish date"
private const val NO_AUTHOR_AVAILABLE = "No author"
private const val NO_DESCRIPTION_AVAILABLE = "No description available"

class NewsRecyclerAdapter(private val newsResponse: CryptoNewsResponse) : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_news_cryptocurrency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(newsResponse.articles[position].title.isNullOrEmpty()) {
            holder.newsTitle.text = NO_TITLE_AVAILABLE
        } else {
            holder.newsTitle.text = newsResponse.articles[position].title.toString()
        }

        if(newsResponse.articles[position].publishedAt.isNullOrEmpty()) {
            holder.newsTitle.text = NO_PUBLISH_DATE_AVAILABLE
        } else {
            holder.newsPublishDate.text = newsResponse.articles[position].publishedAt.toString()
        }

        if(!newsResponse.articles[position].urlToImage.isNullOrEmpty()) {
            Picasso.get().load(newsResponse.articles[position].urlToImage).fit().centerCrop()
                .into(holder.newsImageSlug);
        }

        if(newsResponse.articles[position].title.isNullOrEmpty()) {
            holder.newsTitle.text = NO_AUTHOR_AVAILABLE
        } else {
            holder.newsAuthor.text = newsResponse.articles[position].author.toString()
        }

        if(newsResponse.articles[position].title == "") {
            holder.newsTitle.text = NO_DESCRIPTION_AVAILABLE
        } else {
            holder.newsDescription.text = newsResponse.articles[position].description.toString()
        }
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
            newsAuthor = itemView.findViewById(R.id.text_news_author)
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
}