package com.example.fingraph.home.ui.education

import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.fingraph.R
import com.example.fingraph.databinding.ActivityEducationBinding
import com.example.fingraph.models.networking.response.CryptoMetadataResponse
import com.example.fingraph.utils.data.SharedPreferencesManager
import com.squareup.picasso.Picasso

class EducationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var educationData : CryptoMetadataResponse = SharedPreferencesManager.currentEducationList[0]

        if(intent.extras != null) {
            educationData = intent.getSerializableExtra("METADATA") as CryptoMetadataResponse
        }

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.design_primary))
        binding.toolbarLayout.setContentScrimColor(ContextCompat.getColor(applicationContext, R.color.purple_700))
        binding.toolbarLayout.title = educationData.id
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if(educationData.logo_url.isNotEmpty()) {
            if(".svg" in educationData.logo_url) {
                binding.fab.loadImage(educationData.logo_url, R.drawable.ic_loading_coin_icon)
            } else {
                Picasso.get().load(educationData.logo_url).fit().centerCrop()
                    .into(binding.fab)
            }
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