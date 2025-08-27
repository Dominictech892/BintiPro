package com.dominic.bintipro.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector // Add the icon property here
) {
    object Home : BottomNavItem(ROUT_HOME, "Home", Icons.Default.Home)
    object Services : BottomNavItem(ROUT_SERVICES, "Services", Icons.Default.Star) // Using a placeholder icon
    object Categories : BottomNavItem(ROUT_CATEGORIES, "Categories", Icons.Default.List) // Using a placeholder icon
    object Profile : BottomNavItem(ROUT_PROFILE, "Profile", Icons.Default.Person)
    object Products : BottomNavItem(ROUT_PRODUCT_LIST, "Products", Icons.Default.ShoppingCart) // Using a placeholder icon
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Services,
    BottomNavItem.Categories,
    BottomNavItem.Profile,
    BottomNavItem.Products
)