@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dominic.bintipro.ui.screens.profile.ProfileViewModel
import com.dominic.bintipro.ui.screens.profile.User

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val userState = viewModel.user.collectAsState()

    ProfileScreenContent(
        user = userState.value,
        onEditProfile = { /* navController.navigate("editProfile") */ },
        onChangePassword = { /* navController.navigate("changePassword") */ },
        onLogout = { /* handle logout */ }
    )
}

@Composable
fun ProfileScreenContent(
    user: User,
    onEditProfile: () -> Unit,
    onChangePassword: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile", fontWeight = FontWeight.Bold) }
            )
        }
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Name: ${user.name}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Email: ${user.email}", fontSize = 16.sp)
                    Text("Phone: ${user.phone}", fontSize = 16.sp)
                }
            }

            // Actions
            Button(
                onClick = onEditProfile,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) { Text("Edit Profile") }

            OutlinedButton(
                onClick = onChangePassword,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) { Text("Change Password") }

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                shape = MaterialTheme.shapes.medium
            ) { Text("Logout", color = MaterialTheme.colorScheme.onError) }
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
        onLogout = {}
    )
}
