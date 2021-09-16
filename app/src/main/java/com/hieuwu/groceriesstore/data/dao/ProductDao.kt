package com.hieuwu.groceriesstore.data.dao

import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} WHERE productId = :id")
    fun getById(id: String): Flow<Product>

    @Query("DELETE FROM ${DataConstant.PRODUCT_TABLE}")
    fun clear()

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} LIMIT 10")
    fun getAll(): Flow<List<Product>>

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} Where category =:categoryId LIMIT 20")
    fun getAllByCategory(categoryId: String): Flow<List<Product>>

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} LIMIT 1")
    fun hasProduct(): Product?
}