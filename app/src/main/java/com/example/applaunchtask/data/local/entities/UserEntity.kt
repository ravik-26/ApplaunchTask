package com.example.applaunchtask.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applaunchtask.domain.model.User

@Entity
data class UserEntity(
    val firstName: String,
    val lastName: String,
    @PrimaryKey
    val email: String,
    val profilePic: String
) {
    fun toUser(): User {
        return User(
            firstName = firstName,
            lastName = lastName,
            email = email,
            profilePic = profilePic
        )
    }
}