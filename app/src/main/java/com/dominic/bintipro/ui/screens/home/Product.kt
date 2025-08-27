package com.dominic.bintipro.ui.screens.home

import androidx.annotation.DrawableRes
import com.dominic.bintipro.R

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    @DrawableRes val imageRes: Int,
    val rating: Float
)

val Products = listOf(
    Product(1, "Luxury Lipstick", "Ksh1500.00", R.drawable.bodylotion, 4.8f),
    Product(2, "Organic Face Cream", "Ksh3500.00", R.drawable.eyeliner, 4.6f),
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
