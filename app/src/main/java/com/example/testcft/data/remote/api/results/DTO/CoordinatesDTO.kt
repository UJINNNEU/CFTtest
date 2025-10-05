package com.example.testcft.data.remote.api.results.DTO

import com.google.gson.annotations.SerializedName

data class CoordinatesDTO(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude:Double?
)