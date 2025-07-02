package com.example.testcft.database_people

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PeopleEntity::class], version = 1, exportSchema = false)
abstract class PeopleDataBase: RoomDatabase() {
    abstract fun DatabaseDao():DatabaseDao
    companion object{
        fun getDataBase(context: Context):PeopleDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                PeopleDataBase::class.java,
                name = "test.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}