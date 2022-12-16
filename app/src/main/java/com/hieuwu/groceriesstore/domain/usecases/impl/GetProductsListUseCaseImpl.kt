package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductsListUseCase
import javax.inject.Inject

class GetProductsListUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    GetProductsListUseCase {
    override suspend fun execute(input: GetProductsListUseCase.Input): GetProductsListUseCase.Output {
        val result = productRepository.products
        return GetProductsListUseCase.Output(result)
    }
}