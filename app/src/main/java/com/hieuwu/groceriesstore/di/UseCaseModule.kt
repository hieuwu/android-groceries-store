package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.domain.usecases.AddMealToPlanUseCase
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetCategoriesListUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetOrderListUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductsListUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import com.hieuwu.groceriesstore.domain.usecases.RemoveMealFromPlanUseCase
import com.hieuwu.groceriesstore.domain.usecases.RetrieveMealByTypeUseCase
import com.hieuwu.groceriesstore.domain.usecases.SearchProductUseCase
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import com.hieuwu.groceriesstore.domain.usecases.SubmitOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.domain.usecases.UpdateProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.impl.AddMealToPlanUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.AddToCartUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.CreateNewOrderUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetCategoriesListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetCurrentCartUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetOrderListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProductDetailUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProductsByCategoryUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProductsListUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.GetProfileUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.RefreshAppDataUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.RemoveMealFromPlanUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.RetrieveMealByTypeUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.SearchProductUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.SignInUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.SignOutUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.SubmitOrderUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.UpdateCartItemUseCaseImpl
import com.hieuwu.groceriesstore.domain.usecases.impl.UpdateProfileUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

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