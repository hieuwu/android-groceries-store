package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.data.database.dao.LineItemDao
import com.hieuwu.groceriesstore.data.database.dao.OrderDao
import com.hieuwu.groceriesstore.data.database.entities.LineItem
import com.hieuwu.groceriesstore.data.database.entities.Order
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.dto.OrderDto
import com.hieuwu.groceriesstore.repository.OrderRepository
import com.hieuwu.groceriesstore.models.OrderModel
import com.hieuwu.groceriesstore.data.network.RemoteTable
import com.hieuwu.groceriesstore.data.network.dto.LineItemDto
import com.hieuwu.groceriesstore.models.LineItemModel
import com.hieuwu.groceriesstore.models.OrderStatus
import io.github.jan.supabase.postgrest.Postgrest
import java.util.Date
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val lineItemDao: LineItemDao,
    private val postgrest: Postgrest
) : OrderRepository {
    override suspend fun createOrUpdate(
        id: String,
        status: String,
        address: String
    ) {
        try {
            val order = Order(
                id = id,
                status = status,
                address = address
            )
            orderDao.insert(order)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override suspend fun addLineItem(
        id: Long,
        productId: String,
        orderId: String,
        quantity: Int,
        subtotal: Double
    ) {
        try {
            val newLineItem = LineItem(
                productId = productId,
                orderId = orderId,
                quantity = quantity,
                subtotal = subtotal
            )
            lineItemDao.insert(newLineItem)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override fun getOneOrderByStatus(status: OrderStatus): Flow<OrderModel?> {
        return try {
            return orderDao.getCartWithLineItems(status.value).map {
                it?.asDomainModel()
            }
        } catch (e: Exception) {
            Timber.e(e.message)
            flow {}
        }
    }

    override suspend fun sendOrderToServer(order: OrderModel): Boolean {
        val orderDto = mapModelToDto(order)
        val lineItems = mapModelListToDto(order)
        return try {
            postgrest[RemoteTable.Orders.tableName].insert(orderDto)
            postgrest[RemoteTable.LineItems.tableName].insert(lineItems)
            orderDao.clear()
            true
        } catch (e: Exception) {
            Timber.e(e.message)
            false
        }
    }

    override suspend fun getOrders(): List<OrderModel> {
        return try {
            val result = postgrest[RemoteTable.Orders.tableName].select().decodeList<OrderDto>()
            result.map { mapToModel(it) }
        } catch (e: Exception) {
            listOf()
        }
    }

    private fun mapToModel(order: OrderDto): OrderModel {
        return OrderModel(
            id = order.orderId,
            status = order.status,
            address = order.address,
            lineItemList = mutableListOf(),
            createdAt = order.createdAt
        ).apply {
            totalPrice = order.total
        }
    }

    private fun mapModelToDto(order: OrderModel): OrderDto {
        return OrderDto(
            orderId = order.id,
            address = order.address,
            status = order.status ?: "",
            createdAt = Date().toString(),
            total = order.total
        )
    }

    private fun mapModelToDto(lineItemModel: LineItemModel, orderId: String): LineItemDto {
        return LineItemDto(
            id = lineItemModel.id ?: 0,
            productId = lineItemModel.productId ?: "",
            orderId = orderId,
            quantity = lineItemModel.quantity ?: 0,
            subtotal = lineItemModel.subtotal ?: 0.0,
            lineItemId = lineItemModel.id ?: 0
        )
    }

    private fun mapModelListToDto(order: OrderModel): List<LineItemDto> {
        return order.lineItemList.map { mapModelToDto(it, order.id) }
    }
}
