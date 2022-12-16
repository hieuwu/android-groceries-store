package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsListUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    GetProductsListUseCase {
    override suspend fun execute(input: GetProductsListUseCase.Input): GetProductsListUseCase.Output {
        val result = productRepository.products
        return GetProductsListUseCase.Output(result)
    }
}