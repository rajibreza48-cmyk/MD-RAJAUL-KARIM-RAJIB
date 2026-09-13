package com.example.data.repository

import com.example.data.local.OrderDao
import com.example.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val orderDao: OrderDao) {
    val allOrders: Flow<List<OrderEntity>> = orderDao.getAllOrders()

    suspend fun createOrder(order: OrderEntity): Long {
        return orderDao.insertOrder(order)
    }

    suspend fun getOrder(orderId: Long): OrderEntity? {
        return orderDao.getOrderById(orderId)
    }

    suspend fun updateStatus(orderId: Long, statusBn: String) {
        orderDao.updateOrderStatus(orderId, statusBn)
    }

    suspend fun deleteOrder(orderId: Long) {
        orderDao.deleteOrder(orderId)
    }
}
