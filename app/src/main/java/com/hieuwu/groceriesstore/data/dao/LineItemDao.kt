package com.hieuwu.groceriesstore.data.dao

import androidx.room.*
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.utilities.LINE_ITEM_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface LineItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lineItem: LineItem)

    @Query("SELECT * FROM $LINE_ITEM_TABLE WHERE lineItemId = :id")
    fun getById(id: Long): Flow<LineItem>

    @Delete
    suspend fun remove(lineItem: LineItem)

    @Update
    suspend fun update(lineItem: LineItem)

    @Transaction
    @Query("SELECT * FROM $LINE_ITEM_TABLE ")
    fun getAll(): Flow<List<ProductAndLineItem>>


    @Transaction
    @Query("SELECT * FROM $LINE_ITEM_TABLE WHERE orderId = :orderId")
    fun getLineItemInOrder(orderId:String): Flow<List<ProductAndLineItem>>
}