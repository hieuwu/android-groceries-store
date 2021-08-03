package com.hieuwu.groceriesstore.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE} WHERE id = :id")
    fun getById(id:String): Product

    @Query("DELETE FROM ${DataConstant.PRODUCT_TABLE}")
    fun clear()

    @Query("SELECT * FROM ${DataConstant.PRODUCT_TABLE}")
    fun getAll(): List<Product>

}