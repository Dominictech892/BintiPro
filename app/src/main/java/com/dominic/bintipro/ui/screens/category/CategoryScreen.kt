package com.dominic.bintipro.ui.screens.categories

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dominic.bintipro.ui.screens.home.BottomNavigationBar  // ✅ import bottom nav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categories", fontWeight = FontWeight.Bold, fontSize = 20.sp) }
            )
        },
        bottomBar = { BottomNavigationBar(navController) }  // ✅ added bottom navigation
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text("📦 Categories coming soon!", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}
