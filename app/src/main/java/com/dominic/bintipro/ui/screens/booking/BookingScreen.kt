@file:OptIn(ExperimentalMaterial3Api::class)

package com.dominic.bintipro.ui.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BookingScreen(navController: NavController) {
    val services = listOf(
        "Facial Treatments" to 5000,
        "Makeup Artistry" to 7000,
        "Spa & Relaxation" to 6000,
        "Luxury Hair Styling" to 4500
    )

    var selectedService by remember { mutableStateOf(services[0]) }
    var expanded by remember { mutableStateOf(false) }

    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var preferredDate by remember { mutableStateOf("") }
    var preferredTime by remember { mutableStateOf("") }

    val bookingFee = (selectedService.second * 0.2).toInt()
    val balance = selectedService.second - bookingFee

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "✨ Book Your Appointment",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB8860B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFF1F3), Color(0xFFFFE4B5))
                    )
                )
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Indulge in Luxury ✨",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF6A1B9A),
                textAlign = TextAlign.Center
            )
            Text(
                "Reserve your spot with a small booking fee. Balance payable at the spa 💎",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Service Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedService.first,
                    onValueChange = {},
                    label = { Text("Select Service") },
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    services.forEach { service ->
                        DropdownMenuItem(
                            text = { Text("${service.first} - KES ${service.second}") },
                            onClick = {
                                selectedService = service
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Input Fields
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name") },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone") },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = preferredDate,
                onValueChange = { preferredDate = it },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Date") },
                label = { Text("Preferred Date") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = preferredTime,
                onValueChange = { preferredTime = it },
                label = { Text("Preferred Time") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Price Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Booking Summary", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Divider()
                    Text("Service Price: KES ${selectedService.second}")
                    Text("Booking Fee (20%): KES $bookingFee", color = Color(0xFF6A1B9A))
                    Text("Balance: KES $balance")
                }
            }

            // Payment Button
            Button(
                onClick = { /* TODO: Integrate M-Pesa */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB8860B),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(
                    "💳 Pay Booking Fee (KES $bookingFee)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookingScreenPreview() {
    BookingScreen(rememberNavController())
}
