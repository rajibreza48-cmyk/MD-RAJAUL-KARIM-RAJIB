package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.datasource.SampleMenuData
import com.example.data.local.OrderEntity
import com.example.data.local.RestaurantDatabase
import com.example.data.model.CartItem
import com.example.data.model.MenuCategory
import com.example.data.model.MenuItem
import com.example.data.model.PortionOption
import com.example.data.repository.OrderRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

enum class AppScreen {
    MENU,
    CART,
    ORDERS,
    TRACKING,
    ADMIN
}

enum class DietaryFilter(val labelBn: String) {
    ALL("সব"),
    VEG("নিরামিষ / Veg"),
    SPICY("ঝাল / Spicy"),
    CHEF_SPECIAL("শেফস স্পেশাল")
}

enum class OrderType(val labelBn: String, val subtitleBn: String) {
    HOME_DELIVERY("হোম ডেলিভারি", "বাসায় বা অফিসে পৌঁছে দেওয়া হবে"),
    DINE_IN("ডাইন-ইন / টেবিলে অর্ডার", "রেস্টুরেন্টে বসে খাওয়ার জন্য")
}

enum class PaymentMethod(val labelBn: String, val badgeBn: String) {
    CASH_ON_DELIVERY("ক্যাশ অন ডেলিভারি / কাউন্টারে পরিশোধ", "নগদ"),
    BKASH("বিকাশ (bKash)", "অনলাইন"),
    NAGAD("নগদ (Nagad)", "অনলাইন"),
    CARD("ক্রেডিট / ডেবিট কার্ড", "POS")
}

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: OrderRepository

    init {
        val db = RestaurantDatabase.getDatabase(application)
        repository = OrderRepository(db.orderDao())
    }

    val pastOrders: StateFlow<List<OrderEntity>> = repository.allOrders
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _currentScreen = MutableStateFlow(AppScreen.MENU)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow(MenuCategory.ALL)
    val selectedCategory: StateFlow<MenuCategory> = _selectedCategory.asStateFlow()

    private val _selectedDietFilter = MutableStateFlow(DietaryFilter.ALL)
    val selectedDietFilter: StateFlow<DietaryFilter> = _selectedDietFilter.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _selectedDetailItem = MutableStateFlow<MenuItem?>(null)
    val selectedDetailItem: StateFlow<MenuItem?> = _selectedDetailItem.asStateFlow()

    private val _appliedCoupon = MutableStateFlow<String?>(null)
    val appliedCoupon: StateFlow<String?> = _appliedCoupon.asStateFlow()

    private val _couponError = MutableStateFlow<String?>(null)
    val couponError: StateFlow<String?> = _couponError.asStateFlow()

    private val _orderType = MutableStateFlow(OrderType.HOME_DELIVERY)
    val orderType: StateFlow<OrderType> = _orderType.asStateFlow()

    private val _customerName = MutableStateFlow("")
    val customerName: StateFlow<String> = _customerName.asStateFlow()

    private val _customerPhone = MutableStateFlow("")
    val customerPhone: StateFlow<String> = _customerPhone.asStateFlow()

    private val _deliveryAddress = MutableStateFlow("")
    val deliveryAddress: StateFlow<String> = _deliveryAddress.asStateFlow()

    private val _tableNumber = MutableStateFlow("টেবিল ৫")
    val tableNumber: StateFlow<String> = _tableNumber.asStateFlow()

    private val _paymentMethod = MutableStateFlow(PaymentMethod.CASH_ON_DELIVERY)
    val paymentMethod: StateFlow<PaymentMethod> = _paymentMethod.asStateFlow()

    private val _activeTrackingOrder = MutableStateFlow<OrderEntity?>(null)
    val activeTrackingOrder: StateFlow<OrderEntity?> = _activeTrackingOrder.asStateFlow()

    private val _orderSuccessSnackbar = MutableStateFlow<String?>(null)
    val orderSuccessSnackbar: StateFlow<String?> = _orderSuccessSnackbar.asStateFlow()

    private val _allMenuItems = MutableStateFlow(SampleMenuData.items)
    val allMenuItems: StateFlow<List<MenuItem>> = _allMenuItems.asStateFlow()

    // Filtered menu items
    val filteredMenuItems: StateFlow<List<MenuItem>> = combine(
        _allMenuItems,
        _searchQuery,
        _selectedCategory,
        _selectedDietFilter
    ) { allItems, query, category, dietFilter ->
        val q = query.trim().lowercase(Locale.ROOT)
        allItems.filter { item ->
            val matchesCategory = category == MenuCategory.ALL || item.category == category
            val matchesQuery = q.isEmpty() ||
                    item.nameBn.lowercase().contains(q) ||
                    item.nameEn.lowercase().contains(q) ||
                    item.descriptionBn.lowercase().contains(q)
            val matchesDiet = when (dietFilter) {
                DietaryFilter.ALL -> true
                DietaryFilter.VEG -> item.isVegetarian
                DietaryFilter.SPICY -> item.isSpicy
                DietaryFilter.CHEF_SPECIAL -> item.isChefSpecial
            }
            matchesCategory && matchesQuery && matchesDiet
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SampleMenuData.items
    )

    val cartSubtotal: StateFlow<Double> = _cartItems.combine(_cartItems) { items, _ ->
        items.sumOf { it.totalPrice }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val totalCartCount: StateFlow<Int> = _cartItems.combine(_cartItems) { items, _ ->
        items.sumOf { it.quantity }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val deliveryFee: StateFlow<Double> = _orderType.combine(_cartItems) { type, items ->
        if (items.isEmpty() || type == OrderType.DINE_IN) 0.0 else 40.0
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 40.0)

    val discountAmount: StateFlow<Double> = combine(_appliedCoupon, cartSubtotal) { coupon, subtotal ->
        when (coupon?.uppercase(Locale.ROOT)) {
            "SAVE50" -> if (subtotal >= 200) 50.0 else 0.0
            "TASTY10" -> subtotal * 0.10
            "NEW100" -> if (subtotal >= 400) 100.0 else 0.0
            else -> 0.0
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val grandTotal: StateFlow<Double> = combine(cartSubtotal, deliveryFee, discountAmount) { subtotal, delivery, discount ->
        val total = subtotal + delivery - discount
        if (total > 0.0) total else 0.0
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelect(category: MenuCategory) {
        _selectedCategory.value = category
    }

    fun onDietFilterSelect(filter: DietaryFilter) {
        _selectedDietFilter.value = filter
    }

    fun openItemDetail(item: MenuItem) {
        _selectedDetailItem.value = item
    }

    fun closeItemDetail() {
        _selectedDetailItem.value = null
    }

    fun addToCart(
        item: MenuItem,
        quantity: Int = 1,
        portion: PortionOption = item.portionOptions.first(),
        spice: String = "স্বাভাবিক ঝাল",
        notes: String = ""
    ) {
        val currentList = _cartItems.value.toMutableList()
        val existingIndex = currentList.indexOfFirst {
            it.menuItem.id == item.id &&
            it.selectedPortion.nameBn == portion.nameBn &&
            it.spicePreference == spice &&
            it.specialNotes == notes
        }

        if (existingIndex >= 0) {
            val existing = currentList[existingIndex]
            currentList[existingIndex] = existing.copy(quantity = existing.quantity + quantity)
        } else {
            currentList.add(
                CartItem(
                    menuItem = item,
                    quantity = quantity,
                    selectedPortion = portion,
                    spicePreference = spice,
                    specialNotes = notes
                )
            )
        }
        _cartItems.value = currentList
        closeItemDetail()
    }

    fun updateCartItemQuantity(index: Int, newQuantity: Int) {
        val currentList = _cartItems.value.toMutableList()
        if (index in currentList.indices) {
            if (newQuantity <= 0) {
                currentList.removeAt(index)
            } else {
                currentList[index] = currentList[index].copy(quantity = newQuantity)
            }
            _cartItems.value = currentList
        }
    }

    fun removeCartItem(index: Int) {
        val currentList = _cartItems.value.toMutableList()
        if (index in currentList.indices) {
            currentList.removeAt(index)
            _cartItems.value = currentList
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
        _appliedCoupon.value = null
        _couponError.value = null
    }

    fun applyCoupon(code: String) {
        val clean = code.trim().uppercase(Locale.ROOT)
        when (clean) {
            "SAVE50" -> {
                if (cartSubtotal.value >= 200) {
                    _appliedCoupon.value = clean
                    _couponError.value = null
                } else {
                    _couponError.value = "ন্যূনতম ২০০ টাকার অর্ডারে কার্যকর"
                }
            }
            "TASTY10" -> {
                _appliedCoupon.value = clean
                _couponError.value = null
            }
            "NEW100" -> {
                if (cartSubtotal.value >= 400) {
                    _appliedCoupon.value = clean
                    _couponError.value = null
                } else {
                    _couponError.value = "ন্যূনতম ৪০০ টাকার অর্ডারে কার্যকর"
                }
            }
            else -> {
                _couponError.value = "অকার্যকর কুপন কোড (ব্যবহার করুন SAVE50 বা TASTY10)"
            }
        }
    }

    fun removeCoupon() {
        _appliedCoupon.value = null
        _couponError.value = null
    }

    fun setOrderType(type: OrderType) {
        _orderType.value = type
    }

    fun setCustomerName(name: String) {
        _customerName.value = name
    }

    fun setCustomerPhone(phone: String) {
        _customerPhone.value = phone
    }

    fun setDeliveryAddress(address: String) {
        _deliveryAddress.value = address
    }

    fun setTableNumber(table: String) {
        _tableNumber.value = table
    }

    fun setPaymentMethod(method: PaymentMethod) {
        _paymentMethod.value = method
    }

    fun placeOrder(): Boolean {
        val items = _cartItems.value
        if (items.isEmpty()) return false

        val currentType = _orderType.value
        val destination = if (currentType == OrderType.HOME_DELIVERY) {
            if (_deliveryAddress.value.isBlank()) "ধানমন্ডি, ঢাকা" else _deliveryAddress.value
        } else {
            if (_tableNumber.value.isBlank()) "টেবিল ৫" else _tableNumber.value
        }
        val phone = if (_customerPhone.value.isBlank()) "০১৭XXXXXXXX" else _customerPhone.value

        val itemsSummary = items.joinToString(", ") { "${it.menuItem.nameBn} (${it.quantity}x)" }
        val randomNum = Random.nextInt(1000, 9999)
        val orderNo = "#KB-$randomNum"
        val dateFormatted = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date())

        val newOrder = OrderEntity(
            orderNumber = orderNo,
            orderDateFormatted = dateFormatted,
            itemsSummaryBn = itemsSummary,
            totalAmount = grandTotal.value,
            deliveryFee = deliveryFee.value,
            discount = discountAmount.value,
            orderTypeBn = currentType.labelBn,
            destinationDetail = destination,
            customerPhone = phone,
            paymentMethodBn = _paymentMethod.value.labelBn,
            statusBn = "অর্ডার গৃহীত হয়েছে"
        )

        viewModelScope.launch {
            val id = repository.createOrder(newOrder)
            val savedOrder = newOrder.copy(id = id)
            _activeTrackingOrder.value = savedOrder
            clearCart()
            _currentScreen.value = AppScreen.TRACKING

            // Simulate kitchen progress after order is placed
            delay(5000)
            repository.updateStatus(id, "রান্না হচ্ছে")
            if (_activeTrackingOrder.value?.id == id) {
                _activeTrackingOrder.value = savedOrder.copy(statusBn = "রান্না হচ্ছে")
            }

            delay(6000)
            val nextStatus = if (currentType == OrderType.HOME_DELIVERY) "ডেলিভারির পথে" else "টেবিলে পরিবেশন হচ্ছে"
            repository.updateStatus(id, nextStatus)
            if (_activeTrackingOrder.value?.id == id) {
                _activeTrackingOrder.value = savedOrder.copy(statusBn = nextStatus)
            }
        }
        return true
    }

    fun viewTrackingOrder(order: OrderEntity) {
        _activeTrackingOrder.value = order
        _currentScreen.value = AppScreen.TRACKING
    }

    fun reorder(order: OrderEntity) {
        // Quick add a featured item from this order or sample items
        val item = SampleMenuData.items.firstOrNull() ?: return
        addToCart(item, 1)
        _currentScreen.value = AppScreen.CART
    }

    fun dismissSnackbar() {
        _orderSuccessSnackbar.value = null
    }

    fun updateMenuItem(updatedItem: MenuItem) {
        val currentList = _allMenuItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == updatedItem.id }
        if (index >= 0) {
            currentList[index] = updatedItem
            _allMenuItems.value = currentList
            // Update cart items if they contain this item to reflect new price
            val currentCart = _cartItems.value.toMutableList()
            var cartUpdated = false
            for (i in currentCart.indices) {
                if (currentCart[i].menuItem.id == updatedItem.id) {
                    currentCart[i] = currentCart[i].copy(menuItem = updatedItem)
                    cartUpdated = true
                }
            }
            if (cartUpdated) {
                _cartItems.value = currentCart
            }
        }
    }
}
