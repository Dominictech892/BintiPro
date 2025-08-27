package com.dominic.bintipro.repository

import com.dominic.bintipro.data.UserDao
import com.dominic.bintipro.model.User

// Fake DAO for Previews
class FakeUserDao : UserDao {
    override suspend fun registerUser(user: User) { /* no-op */ }

    override suspend fun loginUser(email: String, password: String): User? {
        return User(
            id = 1,
            username = "PreviewUser",
            email = email,
            password = password,
            role = "user"
        )
    }
}

// Fake Repository wrapping the Fake DAO
class FakeUserRepository : UserRepository(FakeUserDao())
