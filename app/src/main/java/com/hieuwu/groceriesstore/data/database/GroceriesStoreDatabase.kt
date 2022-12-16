package com.hieuwu.groceriesstore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hieuwu.groceriesstore.data.dao.CategoryDao
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.OrderDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.data.dao.RecipeDao
import com.hieuwu.groceriesstore.data.dao.UserDao
import com.hieuwu.groceriesstore.data.entities.Category
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.data.entities.Product
import com.hieuwu.groceriesstore.data.entities.Recipe
import com.hieuwu.groceriesstore.data.entities.User

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
