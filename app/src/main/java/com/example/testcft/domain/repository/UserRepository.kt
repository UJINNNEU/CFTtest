package com.example.testcft.domain.repository

import com.example.testcft.domain.model.User

interface UserRepository {

 //  suspend fun getUsers():List<User>

    suspend fun refreshUsers():List<User>
}