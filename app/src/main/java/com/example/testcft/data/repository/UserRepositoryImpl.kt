package com.example.testcft.data.repository

import com.example.testcft.data.local.UserLocalDataSource
import com.example.testcft.data.mappers.UserMapper
import com.example.testcft.domain.model.User
import com.example.testcft.domain.repository.UserRepository

class UserRepositoryImpl(
    val userLocalDataSource: UserLocalDataSource,
    val userMapper: UserMapper
): UserRepository
{

    override suspend fun getUsers(): List<User> {

        return userMapper.mapFromPeopleEntityToUser(userLocalDataSource.getUserFromLocal())

    }

    override suspend fun refreshUsers(): List<User> {
        TODO("Not yet implemented")
    }
}