package com.example.testcft.presentation.main_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testcft.domain.usecases.GetUsersUseCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcft.domain.model.User
import com.example.testcft.domain.usecases.RefreshUsersUseCase
import kotlinx.coroutines.launch

class ViewModelMain(
    val refreshUsersUseCase: RefreshUsersUseCase
):ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    fun getPeopleList(){
        viewModelScope.launch {
            try {
                val userList = refreshUsersUseCase.execute()
                _users.value = userList
            } catch (e: Exception) {
                _users.value = emptyList()
            }
        }
    }
}