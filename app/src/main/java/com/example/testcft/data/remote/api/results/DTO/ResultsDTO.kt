package com.example.testcft.data.remote.api.results.DTO

import com.google.gson.annotations.SerializedName

data class ResultsDTO(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("location")
    val locationDTO: LocationDTO?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phoneNumer:String?,
    @SerializedName("picture")
    val pictureDTO: PictureDTO?
)