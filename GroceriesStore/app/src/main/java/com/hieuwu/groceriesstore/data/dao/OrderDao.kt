package com.hieuwu.groceriesstore.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.hieuwu.groceriesstore.domain.entities.Order

@Dao
interface OrderDao {
    @Insert
    fun insert(order: Order)

    @Update
    fun update(product: Order)
}