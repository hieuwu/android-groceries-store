package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.data.repository.ProductRepositoryImpl
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ProductRepo

@InstallIn(SingletonComponent::class)
@Module
abstract class ProductRepositoryModule {

    @ProductRepo
    @Singleton
    @Binds
    abstract fun bindRepository(impl: ProductRepositoryImpl): ProductRepository
}
