package com.example.testcft.data.remote.api.results.DTO
import com.google.gson.annotations.SerializedName

data class NameDTO(
    @SerializedName("title")
    val titleName:String?,
    @SerializedName("first")
    val firstName:String?,
    @SerializedName("last")
    val lastName:String?
)