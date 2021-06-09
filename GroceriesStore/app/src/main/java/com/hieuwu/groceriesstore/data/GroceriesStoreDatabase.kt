package com.hieuwu.groceriesstore.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hieuwu.groceriesstore.data.dao.OrderDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.Product

@Database(entities = [Product::class, Order::class], version = 1, exportSchema = false)
abstract class GroceriesStoreDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val orderDao: OrderDao

    companion object {
        @Volatile
        private var INSTANCE: GroceriesStoreDatabase? = null

        fun getInstance(context: Context): GroceriesStoreDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, GroceriesStoreDatabase::class.java,
                        "groceries_store_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}