package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderNumber: String,
    val orderDateFormatted: String,
    val itemsSummaryBn: String,
    val totalAmount: Double,
    val deliveryFee: Double,
    val discount: Double,
    val orderTypeBn: String, // "হোম ডেলিভারি" or "ডাইন-ইন / পিকআপ"
    val destinationDetail: String, // Address or Table Number
    val customerPhone: String,
    val paymentMethodBn: String, // "ক্যাশ অন ডেলিভারি", "বিকাশ", "নগদ", "কার্ড"
    val statusBn: String = "অর্ডার গৃহীত হয়েছে", // "অর্ডার গৃহীত হয়েছে", "রান্না হচ্ছে", "ডেলিভারির পথে", "সম্পন্ন"
    val timestamp: Long = System.currentTimeMillis()
)
