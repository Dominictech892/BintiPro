package com.dominic.bintipro.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class User(
    val name: String,
    val email: String,
    val phone: String
)

class ProfileViewModel : ViewModel() {

    private val _user = MutableStateFlow(
        User(
            name = "Jane Doe",
            email = "janedoe@email.com",
            phone = "+254 712 345 678"
        )
    )
    val user: StateFlow<User> = _user

    // Example: load user info from DB or API
    fun loaduser() {
        viewModelScope.launch {
            // TODO: Replace with real data fetch
            _user.value = User("Dominic", "dominic@email.com", "+254 700 123 456")
        }
    }
}
