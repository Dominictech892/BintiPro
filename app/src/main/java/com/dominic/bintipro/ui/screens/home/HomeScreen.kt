package com.dominic.bintipro.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

// Product Data
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int,
    val rating: Float
)

val Products = listOf(
    Product(1, "Luxury Lipstick", "Ksh1500.00", R.drawable.lipstick, 4.8f),
    Product(2, "Organic Face Cream", "Ksh3500.00", R.drawable.facecream, 4.6f),
    Product(3, "Perfume Elegance", "Ksh5000.00", R.drawable.perfume, 4.9f),
    Product(4, "Makeup Kit Pro", "Ksh8000.00", R.drawable.makeupkit, 4.7f),
    Product(5, "Hydrating Toner", "Ksh2200.00", R.drawable.toner, 4.5f),
    Product(6, "Matte Foundation", "Ksh3000.00", R.drawable.foundation, 4.4f),
    Product(7, "Eyeliner Precision", "Ksh1200.00", R.drawable.eyeliner, 4.3f),
    Product(8, "Nail Polish Set", "Ksh1800.00", R.drawable.nailpolish, 4.6f),
    Product(9, "Hair Serum", "Ksh2700.00", R.drawable.hairserum, 4.7f),
    Product(10, "Body Lotion", "Ksh2500.00", R.drawable.bodylotion, 4.5f),
    Product(11, "Compact Powder", "Ksh2100.00", R.drawable.powder, 4.4f),
    Product(12, "Luxury Face Mask", "Ksh3200.00", R.drawable.facemask, 4.8f)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedBottomItem by remember { mutableStateOf("home") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "✨ BintiPro ✨",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { /* Navigate to cart */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF69B4) // Hot pink header
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFFF69B4) // Pink nav bar
            ) {
                NavigationBarItem(
                    selected = selectedBottomItem == "home",
                    onClick = { selectedBottomItem = "home" },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    selected = selectedBottomItem == "categories",
                    onClick = { selectedBottomItem = "categories" },
                    icon = { Icon(painter = painterResource(id = R.drawable.passwordshow), contentDescription = "") },
                    label = { Text("Categories") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    selected = selectedBottomItem == "cart",
                    onClick = { selectedBottomItem = "cart" },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                    label = { Text("Cart") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    selected = selectedBottomItem == "profile",
                    onClick = { selectedBottomItem = "profile" },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    alwaysShowLabel = true
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFF1F3), Color(0xFFFFE6F7)) // Light pink gradient
                    )
                )
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section Title
            item {
                Text(
                    text = "💋 Discover Beauty Essentials 💋",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFFDB3EB1) // Glam pink
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Shop the latest beauty products tailored just for you.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF7B1FA2) // Purple accent
                )
            }

            // Product Cards
            items(Products) { product ->
                ProductCard(product)
            }

            // Extra Info Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "🌸 Beauty Tips & Hot Deals 🌸",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFD81B60) // Strong pink
                    )
                )
                Text(
                    text = "✨ Get 20% off skincare this week! ✨",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF43A047) // Green for offers
                )
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(95.dp)
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFAD1457) // Deep pink for product names
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price,
                    color = Color(0xFF4A148C), // Purple price text
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    text = "⭐ ${product.rating}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFFF9800) // Orange for rating stars
                )
            }

            IconButton(onClick = { /* Add to cart */ }) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Add to Cart",
                    tint = Color(0xFFD81B60) // Pink cart button
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
