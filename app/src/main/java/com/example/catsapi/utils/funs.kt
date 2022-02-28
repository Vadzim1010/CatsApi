package com.example.catsapi.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.catsapi.R
import com.example.catsapi.data.model.ApiData
import com.example.catsapi.model.Cat

fun ImageView.setImageByGlide(imageUrl: String?) {
    Glide
        .with(this.context)
        .load(imageUrl)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}

fun List<ApiData>.mapApiDataListToCatList(): List<Cat> {
    return this.map {
        Cat(
            it.id,
            it.imageUrl
        )
    }
}