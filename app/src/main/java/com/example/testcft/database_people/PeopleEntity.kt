package com.example.testcft.database_people

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "People")
data class PeopleEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @ColumnInfo(name = "firstName")
    val firstName:String,
    @ColumnInfo(name = "middleName")
    val middleName:String,
    @ColumnInfo(name = "lastName")
    val lastName:String,
    @ColumnInfo(name = "latitude")
    val latitude:Double,
    @ColumnInfo(name = "longitude")
    val longitude:Double,
    @ColumnInfo(name = "numberPhone")
    val numberPhone:String,
    @ColumnInfo(name = "email")
    val email:String,
    @ColumnInfo(name = "photo")
    var photo: ByteArray? = null
)
