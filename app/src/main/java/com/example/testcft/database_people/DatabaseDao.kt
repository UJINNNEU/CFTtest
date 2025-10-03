package com.example.testcft.database_people

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface DatabaseDao {

        //CRUD
        @Query("Select * from People order by id desc")
        fun getAllPeople(): Flow<List<PeopleEntity>>

        @Query("Select * from People where id = :id limit 1")
        suspend fun getPeopleById(id:Int): PeopleEntity

        @Query("SELECT COUNT(*) FROM people")
        suspend fun getPeopleCount(): Int

        @Query("Delete FROM people")
        suspend fun deleteAllPeople(): Int

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertPeople(peopleEntity: PeopleEntity):Long

        @Update
        suspend fun updateContact(peopleEntity: PeopleEntity):Int

        @Delete
        suspend fun deleteContact(peopleEntity: PeopleEntity):Int
}

