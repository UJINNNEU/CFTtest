package com.example.testcft.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {
    @GET("api")
    suspend fun getUser(
        @Query ("results")result:Int
    ): UserResponce

}