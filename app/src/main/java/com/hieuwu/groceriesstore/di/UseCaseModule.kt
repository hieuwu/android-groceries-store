package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetProductListUseCase(impl: GetProductListUseCaseImpl): GetProductListUseCase

    @Singleton
    @Binds
    abstract fun bindGetProductDetailUseCase(impl: GetProductDetailUseCaseImpl): GetProductDetailUseCase

    @Singleton
    @Binds
    abstract fun bindAuthenticateUserUseCase(impl: AuthenticateUserUseCaseImpl): AuthenticateUserUseCase
}