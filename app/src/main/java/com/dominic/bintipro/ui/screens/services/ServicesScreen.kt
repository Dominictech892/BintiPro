@file:OptIn(ExperimentalMaterial3Api::class)

package com.dominic.bintipro.ui.screens.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.R

// ✅ Actual screen for runtime (with NavController)
@Composable
fun ServicesScreen(navController: NavController) {
    ServicesScreenContent(
        onBookClick = { navController.navigate("booking") }
    )
}

// ✅ Extracted UI content so it can be reused in Preview
@Composable
fun ServicesScreenContent(onBookClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Our Luxury Services", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ServiceCardImage(
                imageRes = R.drawable.facialbanner,
                title = "Facial Treatments",
                description = "Indulge in premium facials with gold & pearl extracts.",
                onBookClick = onBookClick
            )
            ServiceCardImage(
                imageRes = R.drawable.makeupbanner,
                title = "Makeup Artistry",
                description = "Luxury makeup looks for weddings, parties, and photoshoots.",
                onBookClick = onBookClick
            )
            ServiceCardImage(
                imageRes = R.drawable.spabanner,
                title = "Spa & Relaxation",
                description = "Aromatherapy, massage, and full-body pampering.",
                onBookClick = onBookClick
            )
            ServiceCardImage(
                imageRes = R.drawable.hairbanner,
                title = "Luxury Hair Styling",
                description = "Elegant styling with high-end products for every occasion.",
                onBookClick = onBookClick
            )
        }
    }
}

@Composable
fun ServiceCardImage(imageRes: Int, title: String, description: String, onBookClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color(0xAA000000))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                Text(description, fontSize = 14.sp, color = Color.LightGray)
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = onBookClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB8860B))
                ) {
                    Text("Book Now", color = Color.White)
                }
            }
        }
    }
}

// ✅ Preview without NavController navigation crash
@Preview(showBackground = true)
@Composable
fun ServicesScreenPreview() {
    ServicesScreenContent(
        onBookClick = {} // No-op in preview
    )
}
