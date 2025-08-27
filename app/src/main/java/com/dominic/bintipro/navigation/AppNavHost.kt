package com.dominic.bintipro.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dominic.bintipro.data.UserDatabase
import com.dominic.bintipro.repository.UserRepository
import com.dominic.bintipro.ui.screens.about.AboutScreen
import com.dominic.bintipro.ui.screens.auth.LoginScreen
import com.dominic.bintipro.ui.screens.auth.RegisterScreen
import com.dominic.bintipro.ui.screens.booking.BookingConfirmationScreen
import com.dominic.bintipro.ui.screens.booking.BookingHistoryScreen
import com.dominic.bintipro.ui.screens.booking.BookingScreen
import com.dominic.bintipro.ui.screens.booking.PaymentScreen
import com.dominic.bintipro.ui.screens.cart.CartScreen
import com.dominic.bintipro.ui.screens.categories.CategoriesScreen
import com.dominic.bintipro.ui.screens.contact.ContactScreen
import com.dominic.bintipro.ui.screens.home.HomeScreen
import com.dominic.bintipro.ui.screens.products.AddProductScreen
import com.dominic.bintipro.ui.screens.products.EditProductScreen
import com.dominic.bintipro.ui.screens.products.ProductListScreen
import com.dominic.bintipro.ui.screens.profile.ProfileScreen
import com.dominic.bintipro.ui.screens.services.ServicesScreen
import com.dominic.bintipro.ui.screens.splash.SplashScreen
import com.dominic.bintipro.viewmodel.AuthViewModel
import com.dominic.bintipro.viewmodel.CartViewModel
import com.dominic.bintipro.viewmodel.ProductViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    productViewModel: ProductViewModel = viewModel(),

    ) {
    val context = LocalContext.current
    val appDatabase = UserDatabase.getDatabase(context)
    val authRepository = UserRepository(appDatabase.userDao())
    val authViewModel: AuthViewModel = AuthViewModel(authRepository)
    val cartViewModel: CartViewModel = viewModel() // Shared cart state

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // --- Startup ---
        composable(ROUT_SPLASH) { SplashScreen(navController) }

        // --- Auth ---
        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }



        //Products
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, productViewModel)
        }

        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController, productViewModel)
            }
        }



        // --- Main Screens ---
        composable(ROUT_HOME) { HomeScreen(navController, cartViewModel) }
        composable(ROUT_PROFILE) { ProfileScreen(navController) }
        composable(ROUT_SERVICES) { ServicesScreen(navController) }
        composable(ROUT_CONTACT) { ContactScreen(navController) }
        composable(ROUT_ABOUT) { AboutScreen(navController) }
        composable(ROUT_CART) { CartScreen(cartViewModel, navController) } // ✅ CartScreen with navController
        composable(ROUT_CATEGORIES) { CategoriesScreen(navController) }

        // --- Booking & Payments ---
        composable("$ROUT_BOOKING?total={total}") { backStackEntry ->
            val total = backStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            BookingScreen(navController, total)
        }
        composable("$ROUT_PAYMENT?total={total}") { backStackEntry ->
            val total = backStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            PaymentScreen(navController, total)
        }
        composable("$ROUT_BOOKING_CONFIRMATION?total={total}") { backStackEntry ->
            val total = backStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            BookingConfirmationScreen(navController, total)
        }
        composable(ROUT_BOOKING_HISTORY) { BookingHistoryScreen(navController) }
    }
}
