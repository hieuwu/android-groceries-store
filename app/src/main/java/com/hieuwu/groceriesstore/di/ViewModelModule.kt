package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.presentation.account.AccountViewModel
import com.hieuwu.groceriesstore.presentation.authentication.signin.SignInViewModel
import com.hieuwu.groceriesstore.presentation.authentication.signup.SignUpViewModel
import com.hieuwu.groceriesstore.presentation.cart.CartViewModel
import com.hieuwu.groceriesstore.presentation.checkout.CheckOutViewModel
import com.hieuwu.groceriesstore.presentation.explore.ExploreViewModel
import com.hieuwu.groceriesstore.presentation.favourite.FavouriteViewModel
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.OverviewViewModel
import com.hieuwu.groceriesstore.presentation.notificationsettings.NotificationSettingsViewModel
import com.hieuwu.groceriesstore.presentation.onboarding.OnboardingViewModel
import com.hieuwu.groceriesstore.presentation.orderhistory.OrderListViewModel
import com.hieuwu.groceriesstore.presentation.productdetail.ProductDetailViewModel
import com.hieuwu.groceriesstore.presentation.productlist.ProductListViewModel
import com.hieuwu.groceriesstore.presentation.shop.ShopViewModel
import com.hieuwu.groceriesstore.presentation.updateprofile.UpdateProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ProductDetailViewModel)
    viewModelOf(::NotificationSettingsViewModel)
    viewModelOf(::UpdateProfileViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::AccountViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::CartViewModel)
    viewModelOf(::CheckOutViewModel)
    viewModelOf(::FavouriteViewModel)
    viewModelOf(::ExploreViewModel)
    viewModelOf(::ShopViewModel)
    viewModelOf(::OrderListViewModel)
    viewModelOf(::ProductListViewModel)
    viewModelOf(::OverviewViewModel)
    viewModelOf(::OverviewViewModel)
}