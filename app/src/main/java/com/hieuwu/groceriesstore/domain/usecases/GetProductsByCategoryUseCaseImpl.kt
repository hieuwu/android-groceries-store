package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsByCategoryUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    GetProductsByCategoryUseCase {
    override suspend fun execute(input: GetProductsByCategoryUseCase.Input): GetProductsByCategoryUseCase.Output {
        val result = productRepository.getAllProductsByCategory(input.categoryId)
        return GetProductsByCategoryUseCase.Output(result)
    }
}