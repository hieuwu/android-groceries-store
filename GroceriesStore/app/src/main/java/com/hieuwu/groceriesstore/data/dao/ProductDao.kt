package com.hieuwu.groceriesstore.data.dao

import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.domain.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} WHERE id = :id")
    fun getById(id:String): Product

    @Query("DELETE FROM ${DataConstant.PRODUCT_TABLE}")
    fun clear()

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} LIMIT 10")
    fun getAll(): Flow<List<Product>>

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} LIMIT 1")
    fun hasProduct(): Product?

}