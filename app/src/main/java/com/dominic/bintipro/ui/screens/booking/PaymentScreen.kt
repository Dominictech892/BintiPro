package com.dominic.bintipro.ui.screens.booking

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.navigation.ROUT_BOOKING_CONFIRMATION
import com.dominic.bintipro.navigation.ROUT_PAYMENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController, total: Int) {
    val bookingFee = (total * 0.2).toInt()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Payment", fontWeight = FontWeight.Bold) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Booking Fee (20%): KES $bookingFee",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "You must pay the booking fee before confirming your booking.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    // Simulate successful payment
                    navController.navigate("$ROUT_BOOKING_CONFIRMATION?total=$total") {
                        popUpTo("$ROUT_PAYMENT") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Pay Now")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(navController = rememberNavController(), total = 5000)
}
