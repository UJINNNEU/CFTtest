package com.example.testcft.domain.model

data class User (
    val id:Int?,
    val titleName:String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val numberPhone:String?,
    val photo:ByteArray?,
    val latitude: Double?,
    val longitude:Double?
)