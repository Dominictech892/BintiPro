package com.dominic.bintipro.navigation

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
import com.dominic.bintipro.ui.screens.booking.BookingScreen
import com.dominic.bintipro.ui.screens.home.HomeScreen
import com.dominic.bintipro.ui.screens.services.ServicesScreen
import com.dominic.bintipro.ui.screens.splash.SplashScreen
import com.dominic.bintipro.viewmodel.AuthViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_SERVICES) {
            ServicesScreen(navController)
        }
        composable(ROUT_BOOKING) {
            BookingScreen(navController)
        }





















        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
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