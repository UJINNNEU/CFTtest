package com.example.testcft.data.remote
import android.util.Log
import com.example.testcft.App
import com.example.testcft.data.remote.api.results.DTO.ResultsDTO

class UserRemoteDataSource(val app:App) {

     suspend fun retrofitGetAPI():List<ResultsDTO> {
        try {
            val getApi = app.getApi
            val responce = getApi.getUser(10)
             val users = responce.results
            users.map{
              //  Log.d("MyLog","{${it.nameDTO?.firstName}}")

            }
            return users
        }
        catch (e: Exception) {
            Log.d("MyLog","$e")
            return emptyList()
        }
    }
}