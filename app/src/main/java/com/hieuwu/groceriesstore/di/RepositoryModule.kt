package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.data.repository.impl.CategoryRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.OrderRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.ProductRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.RecipeRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.impl.UserRepositoryImpl
import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.data.repository.MealPlanRepository
import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.data.repository.RecipeRepository
import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.data.repository.impl.MealPlanRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(impl: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    abstract fun bindOrderRepository(impl: OrderRepositoryImpl): OrderRepository

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Singleton
    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Singleton
    @Binds
    abstract fun bindMealRepository(impl: MealPlanRepositoryImpl): MealPlanRepository
}

val repositoryModule = module {
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
    singleOf(::OrderRepositoryImpl) { bind<OrderRepository>() }
    singleOf(::CategoryRepositoryImpl) { bind<CategoryRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::RecipeRepositoryImpl) { bind<RecipeRepository>() }
}