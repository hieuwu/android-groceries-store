package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.data.mapper.ProductModelToEntityImpl
import com.hieuwu.groceriesstore.domain.mapper.ProductModelToEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ProductMapper

@InstallIn(SingletonComponent::class)
@Module
abstract class ProductMapperModule {

    @ProductMapper
    @Singleton
    @Binds
    abstract fun bindMapper(impl: ProductModelToEntityImpl): ProductModelToEntity
}
