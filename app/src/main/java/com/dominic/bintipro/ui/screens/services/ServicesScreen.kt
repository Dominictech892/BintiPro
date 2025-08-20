@file:OptIn(ExperimentalMaterial3Api::class)

package com.dominic.bintipro.ui.screens.services

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.navigation.ROUT_BOOKING

// Service Data Class
data class Service(val name: String, val description: String, val price: Int)

// Sample Services
val bintiproServices = listOf(
    Service("Facial Treatments", "Premium facials for glowing skin", 2500),
    Service("Makeup Artistry", "Luxury makeup for weddings & parties", 4000),
    Service("Spa & Relaxation", "Aromatherapy and massage sessions", 3500),
    Service("Luxury Hair Styling", "Styling with high-end products", 3000),
    Service("Nail Care", "Manicure, pedicure & nail art", 2000)
)

@Composable
fun ServicesScreen(navController: NavController) {
    var selectedServices by remember { mutableStateOf(listOf<Service>()) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("BintiPro Services", fontWeight = FontWeight.Bold) }) },
        bottomBar = {
            if (selectedServices.isNotEmpty()) {
                BottomAppBar {
                    val total = selectedServices.sumOf { it.price }
                    Button(
                        onClick = { navController.navigate("$ROUT_BOOKING?total=$total") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text("Book Now - KES $total")
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            bintiproServices.forEach { service ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(service.name, fontWeight = FontWeight.Bold)
                            Text(service.description, style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("KES ${service.price}", fontWeight = FontWeight.SemiBold)
                        }
                        Checkbox(
                            checked = selectedServices.contains(service),
                            onCheckedChange = { checked ->
                                selectedServices = if (checked) {
                                    selectedServices + service
                                } else {
                                    selectedServices - service
                                }
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicesScreenPreview() {
    ServicesScreen(navController = rememberNavController())
}
