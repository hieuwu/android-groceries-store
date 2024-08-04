package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.ProductRepository
import com.hieuwu.groceriesstore.usecase.GetProductsListUseCase
import javax.inject.Inject

class GetProductsListUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    GetProductsListUseCase {
    override fun invoke(input: GetProductsListUseCase.Input): GetProductsListUseCase.Output {
        val result = productRepository.products
        return GetProductsListUseCase.Output(result)
    }
}
