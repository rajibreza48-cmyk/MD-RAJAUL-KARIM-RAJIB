package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.RestaurantMenu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.FoodDetailDialog
import com.example.ui.screens.CartScreen
import com.example.ui.screens.MenuScreen
import com.example.ui.screens.OrderTrackingScreen
import com.example.ui.screens.OrdersHistoryScreen
import com.example.ui.theme.WarmAmberPrimary
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.RestaurantViewModel

@Composable
fun RestaurantApp(
    viewModel: RestaurantViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val allMenuItems by viewModel.allMenuItems.collectAsStateWithLifecycle()
    val filteredMenuItems by viewModel.filteredMenuItems.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val selectedDietFilter by viewModel.selectedDietFilter.collectAsStateWithLifecycle()
    val cartItems by viewModel.cartItems.collectAsStateWithLifecycle()
    val totalCartCount by viewModel.totalCartCount.collectAsStateWithLifecycle()
    val cartSubtotal by viewModel.cartSubtotal.collectAsStateWithLifecycle()
    val deliveryFee by viewModel.deliveryFee.collectAsStateWithLifecycle()
    val discountAmount by viewModel.discountAmount.collectAsStateWithLifecycle()
    val grandTotal by viewModel.grandTotal.collectAsStateWithLifecycle()
    val selectedDetailItem by viewModel.selectedDetailItem.collectAsStateWithLifecycle()
    val appliedCoupon by viewModel.appliedCoupon.collectAsStateWithLifecycle()
    val couponError by viewModel.couponError.collectAsStateWithLifecycle()
    val orderType by viewModel.orderType.collectAsStateWithLifecycle()
    val customerName by viewModel.customerName.collectAsStateWithLifecycle()
    val customerPhone by viewModel.customerPhone.collectAsStateWithLifecycle()
    val deliveryAddress by viewModel.deliveryAddress.collectAsStateWithLifecycle()
    val tableNumber by viewModel.tableNumber.collectAsStateWithLifecycle()
    val paymentMethod by viewModel.paymentMethod.collectAsStateWithLifecycle()
    val activeTrackingOrder by viewModel.activeTrackingOrder.collectAsStateWithLifecycle()
    val pastOrders by viewModel.pastOrders.collectAsStateWithLifecycle()

    BackHandler(enabled = currentScreen != AppScreen.MENU) {
        viewModel.navigateTo(AppScreen.MENU)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier.testTag("bottom_navigation_bar")
            ) {
                // Menu tab
                NavigationBarItem(
                    selected = currentScreen == AppScreen.MENU,
                    onClick = { viewModel.navigateTo(AppScreen.MENU) },
                    icon = {
                        Icon(
                            imageVector = if (currentScreen == AppScreen.MENU) Icons.Filled.RestaurantMenu else Icons.Outlined.RestaurantMenu,
                            contentDescription = "মেনু",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("মেনু", fontSize = 12.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = WarmAmberPrimary,
                        selectedTextColor = WarmAmberPrimary
                    ),
                    modifier = Modifier.testTag("nav_menu_tab")
                )

                // Cart tab with badge
                NavigationBarItem(
                    selected = currentScreen == AppScreen.CART,
                    onClick = { viewModel.navigateTo(AppScreen.CART) },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (totalCartCount > 0) {
                                    Badge(containerColor = WarmAmberPrimary) {
                                        Text("$totalCartCount")
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (currentScreen == AppScreen.CART) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                                contentDescription = "কার্ট",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("কার্ট", fontSize = 12.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = WarmAmberPrimary,
                        selectedTextColor = WarmAmberPrimary
                    ),
                    modifier = Modifier.testTag("nav_cart_tab")
                )

                // Orders history tab
                NavigationBarItem(
                    selected = currentScreen == AppScreen.ORDERS || currentScreen == AppScreen.TRACKING,
                    onClick = { viewModel.navigateTo(AppScreen.ORDERS) },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (pastOrders.isNotEmpty()) {
                                    Badge {
                                        Text("${pastOrders.size}")
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (currentScreen == AppScreen.ORDERS) Icons.Filled.History else Icons.Outlined.History,
                                contentDescription = "অর্ডার হিস্ট্রি",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("অর্ডার হিস্ট্রি", fontSize = 12.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = WarmAmberPrimary,
                        selectedTextColor = WarmAmberPrimary
                    ),
                    modifier = Modifier.testTag("nav_orders_tab")
                )

                // Admin tab
                NavigationBarItem(
                    selected = currentScreen == AppScreen.ADMIN,
                    onClick = { viewModel.navigateTo(AppScreen.ADMIN) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "অ্যাডমিন",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("অ্যাডমিন", fontSize = 12.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = WarmAmberPrimary,
                        selectedTextColor = WarmAmberPrimary
                    ),
                    modifier = Modifier.testTag("nav_admin_tab")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { targetScreen ->
                when (targetScreen) {
                    AppScreen.MENU -> {
                        MenuScreen(
                            menuItems = filteredMenuItems,
                            searchQuery = searchQuery,
                            onSearchChange = viewModel::onSearchQueryChange,
                            selectedCategory = selectedCategory,
                            onCategorySelected = viewModel::onCategorySelect,
                            selectedDiet = selectedDietFilter,
                            onDietSelected = viewModel::onDietFilterSelect,
                            onItemClick = viewModel::openItemDetail,
                            onQuickAdd = { item -> viewModel.addToCart(item, 1) },
                            cartItemCount = totalCartCount,
                            cartTotalPrice = cartSubtotal,
                            onViewCartClick = { viewModel.navigateTo(AppScreen.CART) }
                        )
                    }
                    AppScreen.CART -> {
                        CartScreen(
                            cartItems = cartItems,
                            subtotal = cartSubtotal,
                            deliveryFee = deliveryFee,
                            discount = discountAmount,
                            grandTotal = grandTotal,
                            orderType = orderType,
                            onOrderTypeChange = viewModel::setOrderType,
                            customerName = customerName,
                            onCustomerNameChange = viewModel::setCustomerName,
                            customerPhone = customerPhone,
                            onCustomerPhoneChange = viewModel::setCustomerPhone,
                            deliveryAddress = deliveryAddress,
                            onDeliveryAddressChange = viewModel::setDeliveryAddress,
                            tableNumber = tableNumber,
                            onTableNumberChange = viewModel::setTableNumber,
                            appliedCoupon = appliedCoupon,
                            couponError = couponError,
                            onApplyCoupon = viewModel::applyCoupon,
                            onRemoveCoupon = viewModel::removeCoupon,
                            paymentMethod = paymentMethod,
                            onPaymentMethodChange = viewModel::setPaymentMethod,
                            onUpdateQuantity = viewModel::updateCartItemQuantity,
                            onRemoveItem = viewModel::removeCartItem,
                            onPlaceOrder = { viewModel.placeOrder() },
                            onBackToMenu = { viewModel.navigateTo(AppScreen.MENU) }
                        )
                    }
                    AppScreen.TRACKING -> {
                        OrderTrackingScreen(
                            order = activeTrackingOrder,
                            onBackToMenu = { viewModel.navigateTo(AppScreen.MENU) },
                            onViewHistory = { viewModel.navigateTo(AppScreen.ORDERS) }
                        )
                    }
                    AppScreen.ORDERS -> {
                        OrdersHistoryScreen(
                            orders = pastOrders,
                            onBackToMenu = { viewModel.navigateTo(AppScreen.MENU) },
                            onTrackOrder = { order -> viewModel.viewTrackingOrder(order) },
                            onReorder = { order -> viewModel.reorder(order) }
                        )
                    }
                    AppScreen.ADMIN -> {
                        com.example.ui.screens.AdminScreen(
                            menuItems = allMenuItems,
                            onUpdateItem = viewModel::updateMenuItem
                        )
                    }
                }
            }

            // Food detail modal dialog
            selectedDetailItem?.let { item ->
                FoodDetailDialog(
                    item = item,
                    onDismiss = viewModel::closeItemDetail,
                    onAddToCart = { mItem, qty, portion, spice, notes ->
                        viewModel.addToCart(mItem, qty, portion, spice, notes)
                    }
                )
            }
        }
    }
}
