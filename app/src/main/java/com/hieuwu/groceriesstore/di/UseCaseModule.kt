package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCase
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.ExploreProductProductUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.ExploreProductUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductsListUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductsListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseModule {

    @ViewModelScoped
    @Binds
    abstract fun bindGetProductListUseCase(impl: GetProductListUseCaseImpl): GetProductListUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetProductsListUseCase(impl: GetProductsListUseCaseImpl): GetProductsListUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetProductDetailUseCase(impl: GetProductDetailUseCaseImpl): GetProductDetailUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindAuthenticateUserUseCase(impl: AuthenticateUserUseCaseImpl): AuthenticateUserUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindUpdateCartItemUseCase(impl: UpdateCartItemUseCaseImpl): UpdateCartItemUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindCreateOrderUseCase(impl: CreateOrderUseCaseImpl): CreateOrderUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindExploreProductUseCase(impl: ExploreProductProductUseCaseImpl): ExploreProductUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindRefreshAppDataUseCase(impl: RefreshAppDataUseCaseImpl): RefreshAppDataUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindUserSettingsUseCase(impl: UserSettingsUseCaseImpl): UserSettingsUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindSignOutUseCase(impl: SignOutUseCaseImpl): SignOutUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindSignOutGetProfileUseCase(impl: GetProfileUseCaseImpl): GetProfileUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetCurrentCartUseCase(impl: GetCurrentCartUseCaseImpl): GetCurrentCartUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindAddToCartUseCase(impl: AddToCartUseCaseImpl): AddToCartUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindCreateNewOrderUseCase(impl: CreateNewOrderUseCaseImpl): CreateNewOrderUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetProductsByCategoryUseCase(impl: GetProductsByCategoryUseCaseImpl): GetProductsByCategoryUseCase

}
