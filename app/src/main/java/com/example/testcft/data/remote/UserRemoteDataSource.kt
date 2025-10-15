package com.example.testcft.data.remote

import android.util.Log
import com.example.testcft.App

class UserRemoteDataSource {
    val app = App()
    private suspend fun retrofitGetAPI(

    ) {

        try {
           // val getApi = (requireActivity().application as App ).getApi
            val getApi = app.getApi
            val responce = getApi.getUser(10)
            Log.d("MyLog", "$responce")
            val users = responce.results
            Log.d("MyLog","\n End ${ users.size}")
        }
        catch (e: Exception)
        {
            Log.d("MyLog","$e")
        }


    }

}