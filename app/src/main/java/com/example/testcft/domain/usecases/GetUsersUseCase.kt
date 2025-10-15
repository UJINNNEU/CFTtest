package com.example.testcft.domain.usecases
import com.example.testcft.domain.model.User
import com.example.testcft.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository)
{
    suspend fun execute():List<User>{
        return userRepository.getUsers()
    }

}