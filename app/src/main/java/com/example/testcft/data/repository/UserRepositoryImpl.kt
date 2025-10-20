package com.example.testcft.data.repository

import com.example.testcft.App
import com.example.testcft.data.local.UserLocalDataSource
import com.example.testcft.data.mappers.UserMapper
import com.example.testcft.data.remote.UserRemoteDataSource
import com.example.testcft.domain.model.User
import com.example.testcft.domain.repository.UserRepository

class UserRepositoryImpl(app:App

): UserRepository
{
   private val userRemoteDataSource = UserRemoteDataSource(app)
    //private val userLocalDataSource = UserLocalDataSource()
   private val userMapper = UserMapper()

//    override suspend fun getUsers(): List<User> {
//
//        return userMapper.mapFromPeopleEntityToUser(userLocalDataSource.getUserFromLocal())
//
//    }

    override suspend fun refreshUsers(): List<User> {
        return userMapper.mapResultDTOToUser(userRemoteDataSource.retrofitGetAPI())
    }
}