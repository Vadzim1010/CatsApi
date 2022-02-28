package com.example.catsapi.data.model

import com.google.gson.annotations.SerializedName

data class ApiData(
    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val imageUrl: String,
)
