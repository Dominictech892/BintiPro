
package com.dominic.bintipro.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.airbnb.lottie.compose.*
import com.dominic.bintipro.R
import com.dominic.bintipro.navigation.ROUT_LOGIN
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navController: NavController?, isPreview: Boolean = false) {

    // Navigate after 2.5s (only if not preview)
    if (!isPreview) {
        LaunchedEffect(Unit) {
            delay(2500)
            navController?.navigate(ROUT_LOGIN) {
                popUpTo(0)
            }
        }
    }

    // Gradient background (luxury feel)
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFD700), Color(0xFFFFC0CB), Color.White)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Lottie Animation
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ecommerce))
        val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

        LottieAnimation(
            composition,
            progress,
            modifier = Modifier.size(280.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "BintiPro",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Text(
                    text = "Shop Smart. Live Better.",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                    color = Color(0xFF8B0000),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Thousands of products at your fingertips.\nGreat deals. Fast delivery.",
                color = Color.Black.copy(alpha = 0.75f),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bottom progress indicator
        CircularProgressIndicator(
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.CenterHorizontally),
            strokeWidth = 4.dp,
            color = Color(0xFF8B0000)
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = null, isPreview = true)
}
