package com.hieuwu.groceriesstore.data.dao

import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    fun insert(order: Order)

    @Update
    fun update(order: Order)

    @Transaction
    @Query("SELECT * FROM `${DataConstant.ORDER_TABLE}`")
    fun getOrderWithLineItems(): Flow<List<OrderWithLineItems>>

    @Query("SELECT * FROM `${DataConstant.ORDER_TABLE}` WHERE orderId = :id")
    fun getById(id: String): Flow<OrderWithLineItems>

    @Query("SELECT EXISTS(SELECT 1 FROM `${DataConstant.ORDER_TABLE}` WHERE status = :status LIMIT 1)")
    fun isCartExisted(status: String): Flow<Boolean>

    @Transaction
    @Query("SELECT * FROM `${DataConstant.ORDER_TABLE}` WHERE status = :status LIMIT 1")
    fun getOrderInCart(status: String): Flow<List<ProductAndLineItem>>

}