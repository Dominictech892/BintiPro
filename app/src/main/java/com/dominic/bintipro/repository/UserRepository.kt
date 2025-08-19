package com.dominic.bintipro.repository

import com.dominic.bintipro.data.UserDao
import com.dominic.bintipro.model.User


class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}