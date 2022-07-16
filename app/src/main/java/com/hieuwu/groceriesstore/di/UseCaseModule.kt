package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCase
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.ExploreProductProductUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.ExploreProductUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCaseImpl
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

    @Singleton
    @Binds
    abstract fun bindUpdateCartItemUseCase(impl: UpdateCartItemUseCaseImpl): UpdateCartItemUseCase

    @Singleton
    @Binds
    abstract fun bindCreateOrderUseCase(impl: CreateOrderUseCaseImpl): CreateOrderUseCase

    @Singleton
    @Binds
    abstract fun bindExploreProductUseCase(impl: ExploreProductProductUseCaseImpl): ExploreProductUseCase

    @Singleton
    @Binds
    abstract fun bindRefreshAppDataUseCase(impl: RefreshAppDataUseCaseImpl): RefreshAppDataUseCase

    @Singleton
    @Binds
    abstract fun bindUserSettingsUseCase(impl: UserSettingsUseCaseImpl): UserSettingsUseCase
}
