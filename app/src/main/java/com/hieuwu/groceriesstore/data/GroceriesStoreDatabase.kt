package com.hieuwu.groceriesstore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hieuwu.groceriesstore.data.dao.*
import com.hieuwu.groceriesstore.domain.entities.*

@Database(
    entities = [Product::class, LineItem::class, Order::class, Category::class, User::class, Recipe::class],
    version = 1,
    exportSchema = false
)
abstract class GroceriesStoreDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun lineItemDao(): LineItemDao
    abstract fun orderDao(): OrderDao
    abstract fun categoryDao(): CategoryDao
    abstract fun userDao(): UserDao
    abstract fun recipeDao(): RecipeDao

}