package com.hieuwu.groceriesstore.presentation.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase

class ProductDetailViewModelFactory(
    private val id: String,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val orderRepository: OrderRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            return ProductDetailViewModel(id, getProductDetailUseCase, orderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}