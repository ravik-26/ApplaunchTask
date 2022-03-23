package com.example.applaunchtask.data.repository

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.applaunchtask.R
import com.example.applaunchtask.data.local.MainDao
import com.example.applaunchtask.data.local.MainDatabase
import com.example.applaunchtask.data.local.entities.UserEntity
import com.example.applaunchtask.data.remote.WeatherApi
import com.example.applaunchtask.data.remote.dto.WeatherResponseDto
import com.example.applaunchtask.domain.model.User
import com.example.applaunchtask.domain.repository.MainRepository
import com.example.applaunchtask.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: MainDao
): MainRepository {
    override suspend fun getUsers(): Flow<List<UserEntity>> {
        return dao.getUsers()
    }

    override suspend fun addUser(user: User) {
        dao.insertUser(user.toUserEntity())
    }

    override fun verifyLogin(id: String, password: String): Boolean {
        return id == "testapp@google.com" && password == "Test@123456"
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUserById(user.email)
    }

    override suspend fun getWeatherData(): Flow<WeatherResponseDto> {
        val weatherData = api.getWeather()
        return flow {
            emit(weatherData)
        }
    }
}