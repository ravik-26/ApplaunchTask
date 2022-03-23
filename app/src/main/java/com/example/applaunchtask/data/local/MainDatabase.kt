package com.example.applaunchtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.applaunchtask.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract val mainDao: MainDao
}