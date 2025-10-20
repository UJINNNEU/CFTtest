package com.example.testcft.presentation.main_fragment.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testcft.domain.usecases.RefreshUsersUseCase
import com.example.testcft.presentation.main_fragment.ViewModelMain

class ViewModelMainFactory(
    private val refreshUsersUseCase: RefreshUsersUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelMain::class.java)) {
            return ViewModelMain(refreshUsersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}