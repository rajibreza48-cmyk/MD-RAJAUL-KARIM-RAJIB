package com.example.data.model

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int = 1,
    val selectedPortion: PortionOption = menuItem.portionOptions.firstOrNull()
        ?: PortionOption("রেগুলার", "Regular", 0.0),
    val spicePreference: String = "স্বাভাবিক ঝাল",
    val specialNotes: String = ""
) {
    val unitPrice: Double
        get() = menuItem.basePrice + selectedPortion.priceDiff

    val totalPrice: Double
        get() = unitPrice * quantity
}
