package com.dominic.bintipro.ui.screens.booking

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.navigation.ROUT_BOOKING_HISTORY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingConfirmationScreen(navController: NavController, total: Int) {
    val bookingFee = (total * 0.2).toInt()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Booking Confirmed", fontWeight = FontWeight.Bold) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "💖 Booking Confirmed!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )

            Text("Total Cost: KES $total", style = MaterialTheme.typography.bodyMedium)
            Text("Booking Fee (20%): KES $bookingFee", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate(ROUT_BOOKING_HISTORY) },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("📅 View My Bookings")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BookingConfirmationScreenPreview() {
    BookingConfirmationScreen(navController = rememberNavController(), total = 5000)
}
