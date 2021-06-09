package com.hieuwu.groceriesstore.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.domain.entities.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("DELETE FROM ${DataConstant.PRODUCT_TABLE}")
    fun clear()
}