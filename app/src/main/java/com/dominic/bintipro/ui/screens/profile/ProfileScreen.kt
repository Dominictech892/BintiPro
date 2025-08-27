@file:OptIn(ExperimentalMaterial3Api::class)

package com.dominic.bintipro.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.ui.screens.home.BottomNavigationBar

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {
    val userState = viewModel.user.collectAsState()

    ProfileScreenContent(
        user = userState.value,
        onEditProfile = { /* navController.navigate("editProfile") */ },
        onChangePassword = { /* navController.navigate("changePassword") */ },
        onLogout = { /* handle logout */ },
        navController = navController
    )
}

@Composable
fun ProfileScreenContent(
    user: User,
    onEditProfile: () -> Unit,
    onChangePassword: () -> Unit,
    onLogout: () -> Unit,
    navController: NavController? = null
) {
    val orangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFA07A), Color(0xFFFF4500), Color(0xFFFF6347))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile", fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            navController?.let { BottomNavigationBar(it) }
        },
        containerColor = Color.Transparent, // Makes Scaffold background transparent
        modifier = Modifier.background(brush = orangeGradient)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // User Info Card
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(18.dp)),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        "Name: ${user.name}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        "Email: ${user.email}",
                        fontSize = 16.sp,
                        color = Color.DarkGray.copy(alpha = 0.8f)
                    )
                    Text(
                        "Phone: ${user.phone}",
                        fontSize = 16.sp,
                        color = Color.DarkGray.copy(alpha = 0.8f)
                    )
                }
            }

            // Actions
            Button(
                onClick = onEditProfile,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4500))
            ) { Text("Edit Profile", color = Color.White) }

            OutlinedButton(
                onClick = onChangePassword,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp, brush = orangeGradient)
            ) { Text("Change Password") }

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)), // A deep red for logout
                shape = RoundedCornerShape(12.dp)
            ) { Text("Logout", color = Color.White) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        user = User("Preview User", "preview@email.com", "+254 700 000 000"),
        onEditProfile = {},
        onChangePassword = {},
        onLogout = {},
        navController = rememberNavController()
    )
}
