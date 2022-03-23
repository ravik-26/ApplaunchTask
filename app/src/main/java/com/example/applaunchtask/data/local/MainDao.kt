package com.example.applaunchtask.data.local

import androidx.room.*
import com.example.applaunchtask.data.local.entities.UserEntity
import com.example.applaunchtask.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM userentity")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM userentity WHERE email=:email")
    suspend fun deleteUserById(email: String)
}