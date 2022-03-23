package com.example.applaunchtask.domain.repository

import com.example.applaunchtask.data.local.entities.UserEntity
import com.example.applaunchtask.data.remote.dto.WeatherResponseDto
import com.example.applaunchtask.domain.model.User
import com.example.applaunchtask.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getUsers(): Flow<List<UserEntity>>

    suspend fun addUser(user: User)

    fun verifyLogin(id: String, password: String): Boolean

    suspend fun deleteUser(user: User)

    suspend fun getWeatherData(): Flow<WeatherResponseDto>
}