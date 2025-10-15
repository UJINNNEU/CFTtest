package com.example.testcft.data.local
import android.widget.Toast
import com.example.testcft.data.local.database_people.DatabaseDao
import com.example.testcft.data.local.database_people.PeopleEntity
import kotlinx.coroutines.flow.Flow

class UserLocalDataSource(private val databaseDao: DatabaseDao)
{
   suspend fun getUserFromLocal(): List<PeopleEntity> =  databaseDao.getAllPeople()

   suspend fun deleteAllUser(): Boolean = databaseDao.deleteAllPeople() > 0

}