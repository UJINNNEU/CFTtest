package com.example.testcft

import android.app.Application
import com.example.testcft.data.local.database_people.DatabaseDao
import com.example.testcft.data.local.database_people.PeopleDataBase
import com.example.testcft.data.remote.UserAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application()
{
    lateinit var getApi: UserAPI

   //private lateinit var db:PeopleDataBase
   // private lateinit var peopleDao: DatabaseDao

    override fun onCreate() {
        super.onCreate()

        val retrofit =  Retrofit.Builder().baseUrl("https://randomuser.me").
        addConverterFactory(GsonConverterFactory.create()).build()
        getApi = retrofit.create(UserAPI::class.java)

      // db = PeopleDataBase.getDataBase(applicationContext)
      // peopleDao = db.DatabaseDao()
    }
}