package com.example.applaunchtask.domain.model

import com.example.applaunchtask.data.local.entities.UserEntity

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val profilePic: String
) {
    fun toUserEntity(): UserEntity {
        return UserEntity(
            firstName = firstName,
            lastName = lastName,
            email = email,
            profilePic = profilePic
        )
    }
}