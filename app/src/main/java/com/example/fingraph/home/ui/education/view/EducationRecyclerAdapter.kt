package com.example.fingraph.home.ui.education.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.fingraph.R
import com.example.fingraph.home.ui.education.EducationActivity
import com.example.fingraph.models.networking.response.CryptoMetadataResponse
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class EducationRecyclerAdapter(private val educationContentData: List<CryptoMetadataResponse>) : RecyclerView.Adapter<EducationRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_education_cryptocurrency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(educationContentData[position].logo_url.isNotEmpty()) {
            if(".svg" in educationContentData[position].logo_url) {
                holder.educationContentImage.loadImage(educationContentData[position].logo_url, R.drawable.ic_loading_coin_icon)
            } else {
                Picasso.get().load(educationContentData[position].logo_url).fit().centerCrop()
                    .into(holder.educationContentImage)
            }
        }
        var contentTitle = educationContentData[position].description.take(36)
        contentTitle = "$contentTitle [READ MORE]"
        holder.educationContentTitle.text = contentTitle
        var contentTag = educationContentData[position].id
        contentTag = "$contentTag, Education"
        holder.educationContentTag.text = contentTag
        holder.educationContentPublishDate.text = SimpleDateFormat("dd/M/yyyy").format(Date())
        holder.educationContentTopic.text = educationContentData[position].website_url
        holder.educationContentText.text = educationContentData[position].description.take(300)
    }

    override fun getItemCount(): Int {
        return educationContentData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var educationContentImage: ImageView
        var educationContentTitle: TextView
        var educationContentTag: TextView
        var educationContentPublishDate: TextView
        var educationContentTopic: TextView
        var educationContentText: TextView


        init {
            educationContentImage = itemView.findViewById(R.id.education_image_cryptocurrency)
            educationContentTitle = itemView.findViewById(R.id.card_education_title)
            educationContentTag = itemView.findViewById(R.id.card_education_tags)
            educationContentPublishDate = itemView.findViewById(R.id.card_education_publish_date)
            educationContentTopic = itemView.findViewById(R.id.text_education_topic)
            educationContentText = itemView.findViewById(R.id.text_education_description)

            itemView.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, EducationActivity::class.java).apply {
                    this.putExtra("METADATA", educationContentData[position])
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