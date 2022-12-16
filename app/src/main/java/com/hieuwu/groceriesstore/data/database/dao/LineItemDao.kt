package com.hieuwu.groceriesstore.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.hieuwu.groceriesstore.data.database.entities.LineItem
import com.hieuwu.groceriesstore.data.database.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.utilities.LINE_ITEM_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface LineItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lineItem: LineItem)

    @Query("SELECT * FROM $LINE_ITEM_TABLE WHERE lineItemId = :id")
    fun getById(id: Long): Flow<LineItem>

    @Query("UPDATE  $LINE_ITEM_TABLE SET quantity = :quantity  WHERE lineItemId = :id")
    fun updateQuantityById(quantity: Int, id: Long)

    @Delete
    suspend fun remove(lineItem: LineItem)

    @Update
    suspend fun update(lineItem: LineItem)

    @Transaction
    @Query("SELECT * FROM $LINE_ITEM_TABLE ")
    fun getAll(): Flow<List<ProductAndLineItem>>

    @Transaction
    @Query("SELECT * FROM $LINE_ITEM_TABLE WHERE orderId = :orderId")
    fun getLineItemInOrder(orderId: String): Flow<List<ProductAndLineItem>>

    @Query("DELETE FROM $LINE_ITEM_TABLE WHERE lineItemId = :id")
    fun removeLineItemById(id: Long)
}
