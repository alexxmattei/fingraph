package com.example.fingraph.home.ui.watchlist.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.cryptocurrency.CryptoTradingActivity

class WatchlistRecyclerAdapter : RecyclerView.Adapter<WatchlistRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_watchlist_cryptocurrency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cryptocurrencyName.text = cryptocurrencyName[position]
        holder.cryptocurrencyDescription.text = cryptocurrencyDescription[position]
        holder.cryptocurrencyPrice.text = cryptocurrencyPrice[position]
        holder.cryptocurrencyPriceChange.text = cryptocurrencyPriceChange[position]
        holder.cryptocurrencyPriceChangePercent.text = cryptocurrencyPriceChangePercent[position]
    }

    override fun getItemCount(): Int {
        return cryptocurrencyName.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cryptocurrencyName: TextView
        var cryptocurrencyDescription: TextView
        var cryptocurrencyPrice: TextView
        var cryptocurrencyPriceChange: TextView
        var cryptocurrencyPriceChangePercent: TextView

        init {
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

    private val cryptocurrencyName = arrayOf(
        "BTC",
        "ETH", "BNB", "USDT",
        "SOL", "ADA", "XRP",
        "DOT"
    )

    private val cryptocurrencyDescription = arrayOf(
        "Bitcoin", "Ethereum",
        "Binance Coin", "Tether",
        "Solana", "Cardano",
        "Ripple", "Polkadot"
    )

    private val cryptocurrencyPrice = arrayOf(
        "47054.55",
        "4156.88", "312.00", "2.10",
        "213.77", "2.46", "2.88",
        "109.98"
    )

    private val cryptocurrencyPriceChange = arrayOf(
        "+1100",
        "+9.9", "+4.6", "+0.76",
        "-6.5", "+0.77", "-0.62",
        "+0.1"
    )

    private val cryptocurrencyPriceChangePercent = arrayOf(
        "-",
        "-", "-", "-",
        "-", "-", "-",
        "-"
    )


}