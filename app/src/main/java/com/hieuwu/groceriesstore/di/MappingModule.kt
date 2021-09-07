package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.data.mapper.ProductEntityModelMapperImpl
import com.hieuwu.groceriesstore.domain.mapper.ProductEntityModelMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class EntityModelProductMapper

@InstallIn(SingletonComponent::class)
@Module
abstract class ProductMapperModule {

    @EntityModelProductMapper
    @Singleton
    @Binds
    abstract fun bindMapper(impl: ProductEntityModelMapperImpl): ProductEntityModelMapper
}
