package com.example.applaunchtask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaunchtask.data.repository.MainRepositoryImpl
import com.example.applaunchtask.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryImpl: MainRepositoryImpl
): ViewModel() {
    suspend fun getUser() = repositoryImpl.getUsers()

    fun addUser(user: User) = viewModelScope.launch {
        repositoryImpl.addUser(user)
    }

    fun verifyLogin(id: String, password: String) = repositoryImpl.verifyLogin(id, password)

    fun deleteUser(user: User) = viewModelScope.launch {
        repositoryImpl.deleteUser(user)
    }

    suspend fun getWeatherData() = repositoryImpl.getWeatherData()
}