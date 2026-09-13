package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MenuCategory
import com.example.data.model.MenuItem
import com.example.ui.components.CartBottomBar
import com.example.ui.components.MenuItemGridCard
import com.example.ui.components.RestaurantHeader
import com.example.ui.theme.WarmAmberPrimary
import com.example.ui.viewmodel.DietaryFilter

/**
 * Composable screen 'MenuScreen' that displays a grid of food items available in
 * 'ইলিশের বাড়ি রেস্টুরেন্ট' (Ilisher Bari Restaurant).
 */
@Composable
fun MenuScreen(
    menuItems: List<MenuItem>,
    searchQuery: String = "",
    onSearchChange: (String) -> Unit = {},
    selectedCategory: MenuCategory = MenuCategory.ALL,
    onCategorySelected: (MenuCategory) -> Unit = {},
    selectedDiet: DietaryFilter = DietaryFilter.ALL,
    onDietSelected: (DietaryFilter) -> Unit = {},
    onItemClick: (MenuItem) -> Unit = {},
    onQuickAdd: (MenuItem) -> Unit = {},
    cartItemCount: Int = 0,
    cartTotalPrice: Double = 0.0,
    onViewCartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .testTag("menu_items_grid"),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = 0.dp,
                bottom = if (cartItemCount > 0) 90.dp else 24.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Full-width Header with Hero Banner, Search, & Filters
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column {
                    RestaurantHeader(
                        searchQuery = searchQuery,
                        onSearchChange = onSearchChange,
                        selectedCategory = selectedCategory,
                        onCategorySelected = onCategorySelected,
                        selectedDiet = selectedDiet,
                        onDietSelected = onDietSelected
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    // Section Title Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (searchQuery.isNotEmpty()) {
                                    "অনুসন্ধান ফলাফল (${menuItems.size})"
                                } else {
                                    selectedCategory.displayNameBn
                                },
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            )
                            Text(
                                text = "তাজা পদ্মার ইলিশ ও ঐতিহ্যবাহী খাবার তালিকা",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.GridView,
                                    contentDescription = "Grid",
                                    tint = WarmAmberPrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${menuItems.size} পদ",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = WarmAmberPrimary
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }

            // Empty State
            if (menuItems.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SearchOff,
                            contentDescription = "Not found",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "কোনো খাবার খুঁজে পাওয়া যায়নি!",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "অন্য কোনো নামে খুঁজুন বা ক্যাটাগরি ফিল্টার পরিবর্তন করুন",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                onSearchChange("")
                                onCategorySelected(MenuCategory.ALL)
                                onDietSelected(DietaryFilter.ALL)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = WarmAmberPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("সব মেনু দেখুন")
                        }
                    }
                }
            } else {
                // Grid of Food Items
                items(
                    items = menuItems,
                    key = { it.id }
                ) { item ->
                    MenuItemGridCard(
                        item = item,
                        onItemClick = { onItemClick(item) },
                        onQuickAdd = { onQuickAdd(item) }
                    )
                }
            }
        }

        // Floating Cart Bar pinned at bottom
        CartBottomBar(
            itemCount = cartItemCount,
            totalPrice = cartTotalPrice,
            onViewCartClick = onViewCartClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
