package com.example.testcft.data.remote.api.results.DTO

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("coordinates")
    val coordinatesDTO: CoordinatesDTO?
)
