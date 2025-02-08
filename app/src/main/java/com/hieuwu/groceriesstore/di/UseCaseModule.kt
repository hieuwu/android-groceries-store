package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.domain.usecases.AddMealToPlanUseCase
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.AddToCartUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.CreateNewOrderUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetCategoriesListUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.GetCategoriesListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetOrderListUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.GetCurrentCartUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProductDetailUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProductsByCategoryUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProductsListUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProductsListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProfileUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import com.hieuwu.groceriesstore.domain.usecases.RemoveMealFromPlanUseCase
import com.hieuwu.groceriesstore.domain.usecases.RetrieveMealByTypeUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.RefreshAppDataUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.SearchProductUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.SearchProductUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.SignInUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.SignOutUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.SubmitOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.SubmitOrderUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.UpdateCartItemUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.UpdateProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.UpdateProfileUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.AddMealToPlanUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetOrderListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.RemoveMealFromPlanUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.RetrieveMealByTypeUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.UserSettingsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseModule {
    @ViewModelScoped
    @Binds
    abstract fun bindGetProductsListUseCase(impl: GetProductsListUseCaseImpl): GetProductsListUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetProductDetailUseCase(impl: GetProductDetailUseCaseImpl): GetProductDetailUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindUpdateCartItemUseCase(impl: UpdateCartItemUseCaseImpl): UpdateCartItemUseCase

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

    @ViewModelScoped
    @Binds
    abstract fun bindUpdateProfileUseCase(impl: UpdateProfileUseCaseImpl): UpdateProfileUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindSubmitOrderUseCase(impl: SubmitOrderUseCaseImpl): SubmitOrderUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetCategoriesListUseCase(impl: GetCategoriesListUseCaseImpl): GetCategoriesListUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindSearchProductUseCase(impl: SearchProductUseCaseImpl): SearchProductUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetOrdersUseCase(impl: GetOrderListUseCaseImpl): GetOrderListUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindAddMealToPlan(impl: AddMealToPlanUseCaseImpl): AddMealToPlanUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindRetrieveMealByType(impl: RetrieveMealByTypeUseCaseImpl): RetrieveMealByTypeUseCase


    @ViewModelScoped
    @Binds
    abstract fun bindRemoveMealFromPlan(impl: RemoveMealFromPlanUseCaseImpl): RemoveMealFromPlanUseCase

}

val usecaseModule = module {
    single<GetProductsListUseCase> { GetProductsListUseCaseImpl(productRepository = get()) }

    single<GetProductDetailUseCase> { GetProductDetailUseCaseImpl(productRepository = get()) }

    single<UpdateCartItemUseCase> {
        UpdateCartItemUseCaseImpl(
            productRepository = get(),
            orderRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }
    single<RefreshAppDataUseCase> {
        RefreshAppDataUseCaseImpl(
            productRepository = get(),
            categoryRepository = get(),
            recipeRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<SignInUseCase> {
        SignInUseCaseImpl(
            userRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<SignOutUseCase> {
        SignOutUseCaseImpl(
            userRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<GetProfileUseCase> {
        GetProfileUseCaseImpl(
            userRepository = get(),
        )
    }

    single<GetCurrentCartUseCase> {
        GetCurrentCartUseCaseImpl(
            orderRepository = get(),
        )
    }

    single<AddToCartUseCase> {
        AddToCartUseCaseImpl(
            orderRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<CreateNewOrderUseCase> {
        CreateNewOrderUseCaseImpl(
            orderRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<GetProductsByCategoryUseCase> {
        GetProductsByCategoryUseCaseImpl(
            productRepository = get(),
        )
    }

    single<UpdateProfileUseCase> {
        UpdateProfileUseCaseImpl(
            userRepository = get(),
        )
    }

    single<SubmitOrderUseCase> {
        SubmitOrderUseCaseImpl(
            orderRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<GetCategoriesListUseCase> {
        GetCategoriesListUseCaseImpl(
            categoryRepository = get(),
        )
    }

    single<SearchProductUseCase> {
        SearchProductUseCaseImpl(
            productRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<GetOrderListUseCase> {
        GetOrderListUseCaseImpl(
            orderRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }

    single<AddMealToPlanUseCase> {
        AddMealToPlanUseCaseImpl(
            mealPlanRepository = get(),
        )
    }

    single<RetrieveMealByTypeUseCase> {
        RetrieveMealByTypeUseCaseImpl(
            mealPlanRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }
    single<RemoveMealFromPlanUseCase> {
        RemoveMealFromPlanUseCaseImpl(
            mealPlanRepository = get(),
            ioDispatcher = get(named(DispatchersName.IO))
        )
    }
}