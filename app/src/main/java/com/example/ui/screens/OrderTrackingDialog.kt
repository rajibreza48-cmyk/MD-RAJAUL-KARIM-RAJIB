package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.OutdoorGrill
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.OrderEntity
import com.example.ui.theme.GoldenAccent
import com.example.ui.theme.VegGreen
import com.example.ui.theme.WarmAmberPrimary

@Composable
fun OrderTrackingScreen(
    order: OrderEntity?,
    onBackToMenu: () -> Unit,
    onViewHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (order == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = onBackToMenu) {
                Text("মেনুতে ফিরুন")
            }
        }
        return
    }

    val isHomeDelivery = order.orderTypeBn.contains("হোম")

    val steps = listOf(
        TrackingStep(
            titleBn = "অর্ডার গৃহীত হয়েছে",
            subtitleBn = "রেস্টুরেন্ট কিচেনে অর্ডার পাঠানো হয়েছে",
            icon = Icons.Filled.CheckCircle,
            isCompleted = true
        ),
        TrackingStep(
            titleBn = "শেফ রান্না করছেন",
            subtitleBn = "গরম ও তাজা মসলায় রান্না চলছে",
            icon = Icons.Filled.OutdoorGrill,
            isCompleted = order.statusBn == "রান্না হচ্ছে" || order.statusBn.contains("ডেলিভারি") || order.statusBn.contains("পরিবেশন") || order.statusBn == "সম্পন্ন"
        ),
        TrackingStep(
            titleBn = if (isHomeDelivery) "ডেলিভারির পথে" else "পরিবেশনের জন্য প্রস্তুত",
            subtitleBn = if (isHomeDelivery) "রাইডার আপনার ঠিকানার দিকে রওনা হচ্ছে" else "টেবিলে খাবার সাজিয়ে আনা হচ্ছে",
            icon = if (isHomeDelivery) Icons.Filled.DeliveryDining else Icons.Filled.Dining,
            isCompleted = order.statusBn.contains("ডেলিভারি") || order.statusBn.contains("পরিবেশন") || order.statusBn == "সম্পন্ন"
        ),
        TrackingStep(
            titleBn = "ডেলিভারি সম্পন্ন",
            subtitleBn = "সুস্বাদু খাবারের স্বাদ উপভোগ করুন!",
            icon = Icons.Filled.Restaurant,
            isCompleted = order.statusBn == "সম্পন্ন"
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Success badge
        Surface(
            shape = CircleShape,
            color = VegGreen.copy(alpha = 0.15f),
            modifier = Modifier.size(80.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Success",
                    tint = VegGreen,
                    modifier = Modifier.size(44.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "অর্ডার সফলভাবে সম্পন্ন হয়েছে!",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "অর্ডার নম্বর: ${order.orderNumber}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Live Tracking Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "লাইভ ট্র্যাকিং স্ট্যাটাস",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = WarmAmberPrimary.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = order.statusBn,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = WarmAmberPrimary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                steps.forEachIndexed { index, step ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Surface(
                                shape = CircleShape,
                                color = if (step.isCompleted) WarmAmberPrimary else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.size(34.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = step.icon,
                                        contentDescription = null,
                                        tint = if (step.isCompleted) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                            if (index < steps.size - 1) {
                                Box(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(30.dp)
                                        .background(if (step.isCompleted) WarmAmberPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.padding(bottom = 12.dp)) {
                            Text(
                                text = step.titleBn,
                                fontSize = 14.sp,
                                fontWeight = if (step.isCompleted) FontWeight.Bold else FontWeight.Normal,
                                color = if (step.isCompleted) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = step.subtitleBn,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Receipt Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "অর্ডার বিবরণী ও রসিদ",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("খাবার তালিকা:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text(
                    text = order.itemsSummaryBn,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 2.dp)
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("অর্ডারের ধরণ:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(order.orderTypeBn, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        if (isHomeDelivery) "ঠিকানা:" else "টেবিল নম্বর:",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(order.destinationDetail, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("পেমেন্ট পদ্ধতি:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(order.paymentMethodBn, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("মোট পরিশোধিত:", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "৳${order.totalAmount.toInt()}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = WarmAmberPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Action Buttons
        Button(
            onClick = onBackToMenu,
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WarmAmberPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("back_to_menu_from_tracking")
        ) {
            Text("আরও খাবার অর্ডার করুন", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onViewHistory,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("view_order_history_button")
        ) {
            Icon(imageVector = Icons.Filled.History, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("সকল অর্ডার হিস্ট্রি দেখুন", fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

data class TrackingStep(
    val titleBn: String,
    val subtitleBn: String,
    val icon: ImageVector,
    val isCompleted: Boolean
)
