package com.example.fingraph.home.ui.education.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.cryptocurrency.CryptoTradingActivity

class EducationRecyclerAdapter : RecyclerView.Adapter<EducationRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_education_cryptocurrency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.educationContentTitle.text = educationContentTitle[position]
        holder.educationContentTag.text = educationContentTag[position]
        holder.educationContentPublishDate.text = educationContentPublishDate[position]
        holder.educationContentTopic.text = educationContentTopic[position]
        holder.educationContentText.text = educationContentText[position]
    }

    override fun getItemCount(): Int {
        return educationContentTitle.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var educationContentTitle: TextView
        var educationContentTag: TextView
        var educationContentPublishDate: TextView
        var educationContentTopic: TextView
        var educationContentText: TextView


        init {
            educationContentTitle = itemView.findViewById(R.id.card_education_title)
            educationContentTag = itemView.findViewById(R.id.card_education_tags)
            educationContentPublishDate = itemView.findViewById(R.id.card_education_publish_date)
            educationContentTopic = itemView.findViewById(R.id.text_education_topic)
            educationContentText = itemView.findViewById(R.id.text_education_description)

            itemView.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, CryptoTradingActivity::class.java).apply {
                    putExtra("POSITION", position)
                    putExtra("TITLE", educationContentTitle.text)
                    putExtra("DATE", educationContentPublishDate.text)
                    putExtra("TOPIC", educationContentTopic.text)
                    putExtra("CONTENT", educationContentText.text)
                }
                context.startActivity(intent)
            }
        }
    }

    private val educationContentTitle = arrayOf(
        "Dollar Cost Average Your Crypto",
        "Learn How to Minimise Risk", "Leverage Your Assets by Earning Interest", "How Blockchain Works: Behind the Code",
        "Cryptocurrency vs. Gold and Other Assets"
    )

    private val educationContentTag = arrayOf(
        "Cryptocurrency", "Cryptocurrency",
        "InflationProtection",
        "Cryptocurrency", "Inflation Protection"
    )

    private val educationContentPublishDate = arrayOf(
        "2/01/2021",
        "2/01/2021", "2/01/2021", "2/01/2021",
        "2/01/2021"
    )

    private val educationContentTopic = arrayOf(
        "Agencies",
        "Agencies", "The Motley Fool", "Bloomberg",
        "Agencies"
    )

    private val educationContentText = arrayOf(
        "‘Huge Surprise’—El Salvador’s President Issued Six Big Bitcoin Predictions As The Price Of Ethereum, BNB, Solana, Cardano And XRP Limp Into 2022",
        "In our time, political speech and writing are largely the defense of the indefensible. George Orwell, Politics and the English LanguageRep. Ayanna Pressley of Massachusetts, a member of the progressive “squad,” recently accused those who disagree with student…",
        "Tesla founder and CEO Elon Musk, who has been using social media to recruit people, has disclosed that Indian-origin Ashok Elluswamy was the first employee to be hired for his electric vehicle company's Autopilot team.",
        "Happy New Year! Welcome to a bright shiny edition of The Weekly Authority, the Android Authority newsletter that breaks down the top Android and tech news from the week. The 175th edition here with t…",
        "AMD seems to have released an entry-level Athlon Gold PRO 4150GE in the Asian Pacific market which will feature the Zen 2 core architecture.\\r\\nIt's been a while since AMD has touched the entry-level d… [+1867 chars]"
    )


}