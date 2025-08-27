@file:OptIn(ExperimentalMaterial3Api::class)

package com.dominic.bintipro.ui.screens.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.R
import com.dominic.bintipro.navigation.ROUT_BOOKING
import com.dominic.bintipro.ui.screens.home.BottomNavigationBar

// Service Data Class
data class Service(val name: String, val description: String, val price: Int, val imageResId: Int)

// Sample Services with placeholders for images
val bintiproServices = listOf(
    Service("Facial Treatments", "Premium facials for glowing skin", 2500, R.drawable.ic_facial),
    Service("Makeup Artistry", "Luxury makeup for weddings & parties", 4000, R.drawable.ic_makeup),
    Service("Spa & Relaxation", "Aromatherapy and massage sessions", 3500, R.drawable.ic_spa),
    Service("Luxury Hair Styling", "Styling with high-end products", 3000, R.drawable.ic_hair),
    Service("Nail Care", "Manicure, pedicure & nail art", 2000, R.drawable.ic_nails),
    Service("Waxing & Hair Removal", "Smooth skin with professional waxing", 1500, R.drawable.ic_waxing),
    Service("Eyelash Extensions", "Get fuller, more beautiful lashes", 3000, R.drawable.ic_lashes)
)

@Composable
fun ServicesScreen(navController: NavController) {
    var selectedServices by remember { mutableStateOf(listOf<Service>()) }

    val orangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFA07A), Color(0xFFFF4500), Color(0xFFFF6347))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BintiPro Services", fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            Column {
                if (selectedServices.isNotEmpty()) {
                    BottomAppBar(
                        containerColor = Color.Transparent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        val total = selectedServices.sumOf { it.price }
                        Button(
                            onClick = { navController.navigate("$ROUT_BOOKING?total=$total") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4500))
                        ) {
                            Text(
                                text = "Book Now - KES $total",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
                BottomNavigationBar(navController)
            }
        },
        containerColor = Color.Transparent,
        modifier = Modifier.background(brush = orangeGradient)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            bintiproServices.forEach { service ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = service.imageResId),
                            contentDescription = service.name,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = service.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.DarkGray
                            )
                            Text(
                                text = service.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "KES ${service.price}",
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFFF4500)
                            )
                        }

                        Checkbox(
                            checked = selectedServices.contains(service),
                            onCheckedChange = { checked ->
                                selectedServices = if (checked) {
                                    selectedServices + service
                                } else {
                                    selectedServices - service
                                }
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFFF4500),
                                uncheckedColor = Color.Gray,
                                checkmarkColor = Color.White
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp)) // extra space for bottom bar
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicesScreenPreview() {
    ServicesScreen(navController = rememberNavController())
}