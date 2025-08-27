package com.dominic.bintipro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.navigation.AppNavHost
import com.dominic.bintipro.ui.theme.BintiProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BintiProTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController) // <- This replaces Greeting()
            }
        }
    }
}
