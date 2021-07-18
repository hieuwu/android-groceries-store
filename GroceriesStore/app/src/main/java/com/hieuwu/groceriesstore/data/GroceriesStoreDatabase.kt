package com.hieuwu.groceriesstore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.domain.entities.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class GroceriesStoreDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}