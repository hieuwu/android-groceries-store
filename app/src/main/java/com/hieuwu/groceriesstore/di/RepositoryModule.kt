package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.data.repository.RecipeRepository
import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.data.repository.impl.CategoryRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.OrderRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.ProductRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.RecipeRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
    singleOf(::OrderRepositoryImpl) { bind<OrderRepository>() }
    singleOf(::CategoryRepositoryImpl) { bind<CategoryRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::RecipeRepositoryImpl) { bind<RecipeRepository>() }
}