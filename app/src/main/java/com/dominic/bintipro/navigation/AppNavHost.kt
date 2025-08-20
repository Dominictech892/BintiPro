package com.dominic.bintipro.navigation

import ProfileScreen
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dominic.bintipro.data.UserDatabase
import com.dominic.bintipro.repository.UserRepository
import com.dominic.bintipro.ui.screens.about.AboutScreen
import com.dominic.bintipro.ui.screens.auth.LoginScreen
import com.dominic.bintipro.ui.screens.auth.RegisterScreen
import com.dominic.bintipro.ui.screens.booking.BookingConfirmationScreen
import com.dominic.bintipro.ui.screens.booking.BookingHistoryScreen
import com.dominic.bintipro.ui.screens.booking.BookingScreen
import com.dominic.bintipro.ui.screens.booking.PaymentScreen
import com.dominic.bintipro.ui.screens.contact.ContactScreen
import com.dominic.bintipro.ui.screens.home.HomeScreen
import com.dominic.bintipro.ui.screens.services.ServicesScreen
import com.dominic.bintipro.ui.screens.splash.SplashScreen
import com.dominic.bintipro.viewmodel.AuthViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    val context = LocalContext.current
    val appDatabase = UserDatabase.getDatabase(context)
    val authRepository = UserRepository(appDatabase.userDao())
    val authViewModel: AuthViewModel = AuthViewModel(authRepository)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Splash
        composable(ROUT_SPLASH) { SplashScreen(navController) }

        // Home
        composable(ROUT_HOME) { HomeScreen(navController) }

        // About
        composable(ROUT_ABOUT) { AboutScreen(navController) }

        //Contact
        composable(ROUT_CONTACT) { ContactScreen(navController) }


        // Services
        composable(ROUT_SERVICES) { ServicesScreen(navController) }

        // BookingScreen with total from Services
        composable("$ROUT_BOOKING?total={total}") { backStackEntry ->
            val total = backStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            BookingScreen(navController, total)
        }

        // PaymentScreen with total
        composable("$ROUT_BOOKING/payment?total={total}") { backStackEntry ->
            val total = backStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            PaymentScreen(navController, total)
        }

        // Booking Confirmation Screen
        composable("$ROUT_BOOKING_CONFIRMATION?total={total}") { backStackEntry ->
            val total = backStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            BookingConfirmationScreen(navController, total)
        }

        // Booking History
        composable(ROUT_BOOKING_HISTORY) { BookingHistoryScreen(navController) }

        // Profile
        composable(ROUT_PROFILE) { ProfileScreen(navController) }

        // --- AUTH ---
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
    }
}
