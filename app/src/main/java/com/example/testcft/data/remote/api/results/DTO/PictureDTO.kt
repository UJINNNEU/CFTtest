package com.example.testcft.data.remote.api.results.DTO

import com.google.gson.annotations.SerializedName

data class PictureDTO(
    @SerializedName("large")
    val pictureLarge:String?,
    @SerializedName("medium")
    val pictureMadium:String?
)
