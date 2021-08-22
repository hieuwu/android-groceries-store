package com.hieuwu.groceriesstore.data.dao

import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems

@Dao
interface OrderDao {
    @Insert
    fun insert(order: Order)

    @Update
    fun update(product: Order)

    @Transaction
    @Query("SELECT * FROM `${DataConstant.ORDER_TABLE}`")
    fun getOrderWithLineItems(): List<OrderWithLineItems>
}