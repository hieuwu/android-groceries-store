package com.hieuwu.groceriesstore.data.dao

import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import kotlinx.coroutines.flow.Flow

@Dao
interface LineItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lineItem: LineItem)

    @Query("SELECT * FROM ${DataConstant.LINE_ITEM_TABLE} WHERE lineItemId = :id")
    fun getById(id:Long): Flow<LineItem>

}