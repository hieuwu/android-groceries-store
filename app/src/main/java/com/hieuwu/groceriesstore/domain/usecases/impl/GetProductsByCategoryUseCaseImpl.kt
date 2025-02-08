package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCase

class GetProductsByCategoryUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductsByCategoryUseCase {
    override fun invoke(input: GetProductsByCategoryUseCase.Input): GetProductsByCategoryUseCase.Output {
        val result = productRepository.getAllProductsByCategory(input.categoryId)
        return GetProductsByCategoryUseCase.Output(result)
    }
}
