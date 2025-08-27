package com.dominic.bintipro.navigation

// Core
const val ROUT_SPLASH = "splash"
const val ROUT_HOME = "home"
const val ROUT_PROFILE = "profile"
const val ROUT_SERVICES = "services"
const val ROUT_CONTACT = "contact"
const val ROUT_ABOUT = "about"
const val ROUT_CART = "cart"
const val ROUT_CATEGORIES = "categories"
const val ROUT_PRODUCTS = "products"


// Booking & Payments
const val ROUT_BOOKING = "booking"
const val ROUT_BOOKING_CONFIRMATION = "booking_confirmation"
const val ROUT_BOOKING_HISTORY = "booking_history"
const val ROUT_PAYMENT = "payment"

// Auth
const val ROUT_LOGIN = "login"
const val ROUT_REGISTER = "register"

//CRUD
//Product
const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"


