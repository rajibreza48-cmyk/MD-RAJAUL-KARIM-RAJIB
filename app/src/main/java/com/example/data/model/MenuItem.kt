package com.example.data.model

import androidx.annotation.DrawableRes

enum class MenuCategory(val displayNameBn: String, val displayNameEn: String) {
    ALL("সব খাবার", "All Items"),
    ILISH_SPECIALS("ইলিশ স্পেশাল", "Ilish Specials"),
    THALI_COMBO("ইলিশ থালি ও কম্বো", "Thali & Combos"),
    POLAO_KHICHURI("পোলাও ও খিচুড়ি", "Polao & Khichuri"),
    CURRY_BHORTA("কারি ও ভর্তা", "Curries & Bhorta"),
    GRILL_KEBAB("ফ্রাই ও কাবাব", "Fry & Kebabs"),
    BEVERAGES_DESSERTS("পানীয় ও মিষ্টি", "Drinks & Desserts")
}

data class PortionOption(
    val nameBn: String,
    val nameEn: String,
    val priceDiff: Double = 0.0
)

/**
 * Data class representing food items with name, price, description, and image URL
 * as requested, enhanced with rich restaurant attributes.
 */
data class MenuItem(
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String = "",
    val id: String = name.lowercase().replace(" ", "_"),
    val nameBn: String = name,
    val nameEn: String = name,
    val basePrice: Double = price,
    val descriptionBn: String = description,
    val category: MenuCategory = MenuCategory.ILISH_SPECIALS,
    val rating: Float = 4.9f,
    val reviewCount: Int = 150,
    val prepTimeBn: String = "১৫-২০ মিনিট",
    val caloriesBn: String = "৫২০ ক্যালোরি",
    val isSpicy: Boolean = false,
    val spiceLevelBn: String = "স্বাভাবিক",
    val isVegetarian: Boolean = false,
    val isChefSpecial: Boolean = true,
    @DrawableRes val imageRes: Int = 0,
    val portionOptions: List<PortionOption> = listOf(
        PortionOption("রেগুলার ১ পিস", "Regular 1 Piece", 0.0),
        PortionOption("স্পেশাল বড় পেটি / চাকা", "Large Belly Piece", 90.0),
        PortionOption("পরিবার প্ল্যাটার (৪ পিস)", "Family Platter (4 Pcs)", 750.0)
    )
)
