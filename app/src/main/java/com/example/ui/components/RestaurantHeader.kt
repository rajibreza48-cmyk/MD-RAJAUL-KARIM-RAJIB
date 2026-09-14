package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.MenuCategory
import com.example.ui.theme.GoldenAccent
import com.example.ui.theme.WarmAmberPrimary
import com.example.ui.viewmodel.DietaryFilter

@Composable
fun RestaurantHeader(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedCategory: MenuCategory,
    onCategorySelected: (MenuCategory) -> Unit,
    selectedDiet: DietaryFilter,
    onDietSelected: (DietaryFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Search Bar pinned at the very top of the menu
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("menu_search_input"),
            placeholder = {
                Text(
                    text = "খাবার বা মেনু খুঁজুন (যেমন: সরিষা ইলিশ, ভাজা ইলিশ)...",
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "সার্চ করুন",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { onSearchChange("") },
                        modifier = Modifier.testTag("clear_search_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "সার্চ ক্লিয়ার করুন"
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )
        Spacer(modifier = Modifier.height(14.dp))

        // Hero Visual Banner
        Card(
            shape = RoundedCornerShape(16.dp), // Adjust corner shape since it's no longer the absolute top edge
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_ilisher_bari_hero),
                    contentDescription = "ইলিশের বাড়ি রেস্টুরেন্ট ব্যানার",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                // Dark gradient overlay for readability
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.2f),
                                    Color.Black.copy(alpha = 0.88f)
                                )
                            )
                        )
                )

                // Restaurant Details Overlay
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = GoldenAccent,
                            contentColor = Color.Black
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = Color.Black,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "৪.৯ (২.৫k+)",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Surface(
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.25f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.AccessTime,
                                    contentDescription = "Opening Hours",
                                    tint = Color.White,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "খোলা: ১১:০০ AM - ১১:৩০ PM",
                                    color = Color.White,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.img_ilisher_bari_logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "ইলিশের বাড়ি রেস্টুরেন্ট",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.DeliveryDining,
                            contentDescription = "ডেলিভারি",
                            tint = Color(0xFFFFD54F),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "পদ্মার খাঁটি ইলিশ • ৩০-৪০ মিনিট ডেলিভারি",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Category Chips (Horizontal scroll)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MenuCategory.values().forEach { category ->
                val isSelected = selectedCategory == category
                FilterChip(
                    selected = isSelected,
                    onClick = { onCategorySelected(category) },
                    label = {
                        Text(
                            text = category.displayNameBn,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        labelColor = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.testTag("category_chip_${category.name.lowercase()}")
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dietary tags filter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            DietaryFilter.values().forEach { diet ->
                val isSelected = selectedDiet == diet
                Surface(
                    selected = isSelected,
                    onClick = { onDietSelected(diet) },
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    },
                    border = androidx.compose.foundation.BorderStroke(
                        width = 1.dp,
                        color = if (isSelected) WarmAmberPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                    ),
                    modifier = Modifier.testTag("diet_filter_${diet.name.lowercase()}")
                ) {
                    Text(
                        text = diet.labelBn,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}
