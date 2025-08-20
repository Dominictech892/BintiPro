@file:OptIn(ExperimentalMaterial3Api::class)

package com.dominic.bintipro.ui.screens.booking

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Booking(
    val id: Int,
    val serviceName: String,
    val date: String,
    val time: String,
    val price: Int,
    val isUpcoming: Boolean
)

private val sampleBookings = listOf(
    Booking(1, "Facial Treatments", "Aug 25, 2025", "2:00 PM", 5000, true),
    Booking(2, "Spa & Relaxation", "Aug 28, 2025", "10:00 AM", 6000, true),
    Booking(3, "Luxury Hair Styling", "Aug 10, 2025", "4:00 PM", 4500, false),
    Booking(4, "Makeup Artistry", "July 20, 2025", "12:00 PM", 7000, false)
)

@Composable
fun BookingHistoryScreen(navController: NavController) {
    BookingHistoryContent(
        bookings = sampleBookings,
        onCancel = { /* TODO cancel booking */ },
        onReschedule = { /* TODO reschedule booking */ }
    )
}

@Composable
fun BookingHistoryContent(
    bookings: List<Booking>,
    onCancel: (Int) -> Unit,
    onReschedule: (Int) -> Unit
) {
    val upcoming = remember(bookings) { bookings.filter { it.isUpcoming } }
    val past = remember(bookings) { bookings.filterNot { it.isUpcoming } }

    Scaffold(
        topBar = { TopAppBar(title = { Text("My Bookings", fontWeight = FontWeight.Bold) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (upcoming.isNotEmpty()) {
                Text("Upcoming", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
                upcoming.forEach { b ->
                    BookingCard(b, showActions = true, onCancel = onCancel, onReschedule = onReschedule)
                }
            }
            if (past.isNotEmpty()) {
                Text("Past", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)
                past.forEach { b ->
                    BookingCard(b, showActions = false, onCancel = onCancel, onReschedule = onReschedule)
                }
            }
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
private fun BookingCard(
    booking: Booking,
    showActions: Boolean,
    onCancel: (Int) -> Unit,
    onReschedule: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(booking.serviceName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("${booking.date} • ${booking.time}", fontSize = 14.sp)
            Text("KES ${booking.price}", fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)

            if (showActions) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(
                        onClick = { onCancel(booking.id) },
                        modifier = Modifier.weight(1f),
                    ) { Text("Cancel") }
                    Button(
                        onClick = { onReschedule(booking.id) },
                        modifier = Modifier.weight(1f),
                    ) { Text("Reschedule") }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookingHistoryPreview() {
    MaterialTheme {
        BookingHistoryContent(
            bookings = sampleBookings,
            onCancel = {},
            onReschedule = {}
        )
    }
}

