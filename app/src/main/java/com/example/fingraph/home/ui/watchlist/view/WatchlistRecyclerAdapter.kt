package com.example.fingraph.home.ui.watchlist.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.fingraph.R
import com.example.fingraph.cryptocurrency.CryptoTradingActivity
import com.example.fingraph.models.networking.response.CryptoPriceResponse
import com.squareup.picasso.Picasso

class WatchlistRecyclerAdapter(private val cryptoPriceDataList: List<CryptoPriceResponse>) : RecyclerView.Adapter<WatchlistRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_watchlist_cryptocurrency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(cryptoPriceDataList[position].logo_url.isNotEmpty()) {
            if(".svg" in cryptoPriceDataList[position].logo_url) {
                holder.cryptocurrencyImage.loadImage(cryptoPriceDataList[position].logo_url, R.drawable.ic_loading_coin_icon)
            } else {
                Picasso.get().load(cryptoPriceDataList[position].logo_url).fit().centerCrop()
                    .into(holder.cryptocurrencyImage)
            }
        }
        holder.cryptocurrencyName.text = cryptoPriceDataList[position].currency
        holder.cryptocurrencyDescription.text = cryptoPriceDataList[position].name
        holder.cryptocurrencyPrice.text = cryptoPriceDataList[position].price.toString()
        holder.cryptocurrencyPriceChange.text = cryptoPriceDataList[position].oneHour.priceChangePct.toString()
        holder.cryptocurrencyPriceChangePercent.text = cryptoPriceDataList[position].oneHour.priceChange.toString()

        holder.cryptocurrencyFirstRow.setPadding(0, 0, 0, 12)
        holder.cryptocurrencyName.textSize = 16.0F
        holder.cryptocurrencyName.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.design_primary_text))

        holder.cryptocurrencyDescription.textSize = 14.0F
        holder.cryptocurrencyDescription.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.design_secondary_text))

        if ("+" in cryptoPriceDataList[position].oneHour.priceChangePct.toString()) {
            holder.cryptocurrencyPriceChange.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.design_primary_ticker_positive
                )
            )
            holder.cryptocurrencyPriceChangePercent.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.design_primary_ticker_positive
                )
            )
        } else if ("-" in cryptoPriceDataList[position].oneHour.priceChangePct.toString()) {
            holder.cryptocurrencyPriceChange.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.design_primary_ticker_negative
                )
            )
            holder.cryptocurrencyPriceChangePercent.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.design_primary_ticker_negative
                )
            )
        } else {
            holder.cryptocurrencyPriceChange.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.design_primary_asset_description
                )
            )
            holder.cryptocurrencyPriceChangePercent.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.design_primary_asset_description
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return cryptoPriceDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cryptocurrencyImage: ImageView
        var cryptocurrencyFirstRow: LinearLayout
        var cryptocurrencyName: TextView
        var cryptocurrencyDescription: TextView
        var cryptocurrencyPrice: TextView
        var cryptocurrencyPriceChange: TextView
        var cryptocurrencyPriceChangePercent: TextView

        init {
            cryptocurrencyFirstRow = itemView.findViewById(R.id.card_cryptocurrency_first_row)
            cryptocurrencyImage = itemView.findViewById(R.id.cryptocurrency_image)
            cryptocurrencyName = itemView.findViewById(R.id.cryptocurrency_name)
            cryptocurrencyDescription = itemView.findViewById(R.id.cryptocurrency_description)
            cryptocurrencyPrice = itemView.findViewById(R.id.cryptocurrency_price)
            cryptocurrencyPriceChange =
                itemView.findViewById(R.id.cryptocurrency_timeframe_change_fiat)
            cryptocurrencyPriceChangePercent =
                itemView.findViewById(R.id.cryptocurrency_timeframe_change_percent)

            itemView.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, CryptoTradingActivity::class.java).apply {
                    putExtra("POSITION", position)
                    putExtra("NAME", cryptocurrencyName.text)
                    putExtra("PRICE", cryptocurrencyPrice.text)
                    putExtra("PRICE_CHANGE", cryptocurrencyPriceChange.text)
                    putExtra("PRICE_CHANGE_PERCENT", cryptocurrencyPriceChangePercent.text)
                }
                context.startActivity(intent)
            }
        }
    }

    fun ImageView.loadImage(imageUri: String, placeholder: Int? = null) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadImage.context)) }
            .build()

        load(uri = imageUri, imageLoader = imageLoader) {
            crossfade(true)
            placeholder(placeholder ?: R.drawable.ic_loading_coin_icon)
        }
    }

}