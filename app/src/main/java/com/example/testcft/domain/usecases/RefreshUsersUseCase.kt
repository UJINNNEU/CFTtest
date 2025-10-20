package com.example.testcft.domain.usecases

import com.example.testcft.domain.model.User
import com.example.testcft.domain.repository.UserRepository

class RefreshUsersUseCase(private val userRepository: UserRepository)
{
    suspend fun execute(): List<User>
    {
      return userRepository.refreshUsers()
    }
}