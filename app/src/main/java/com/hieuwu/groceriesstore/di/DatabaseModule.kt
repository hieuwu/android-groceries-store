package com.hieuwu.groceriesstore.di

import androidx.room.Room
import com.hieuwu.groceriesstore.data.database.GroceriesStoreDatabase
import com.hieuwu.groceriesstore.data.database.dao.CategoryDao
import com.hieuwu.groceriesstore.data.database.dao.LineItemDao
import com.hieuwu.groceriesstore.data.database.dao.OrderDao
import com.hieuwu.groceriesstore.data.database.dao.ProductDao
import com.hieuwu.groceriesstore.data.database.dao.RecipeDao
import com.hieuwu.groceriesstore.data.database.dao.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            GroceriesStoreDatabase::class.java,
            "groceries_store.db"
        ).fallbackToDestructiveMigration().build()
    }
    single<CategoryDao> {
        get<GroceriesStoreDatabase>().categoryDao()
    }
    single<ProductDao> {
        get<GroceriesStoreDatabase>().productDao()
    }
    single<LineItemDao> {
        get<GroceriesStoreDatabase>().lineItemDao()
    }
    single<OrderDao> {
        get<GroceriesStoreDatabase>().orderDao()
    }
    single<UserDao> {
        get<GroceriesStoreDatabase>().userDao()
    }
    single<RecipeDao> {
        get<GroceriesStoreDatabase>().recipeDao()
    }
}
