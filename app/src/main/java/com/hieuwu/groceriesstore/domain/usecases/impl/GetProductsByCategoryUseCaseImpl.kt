package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductsByCategoryUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    GetProductsByCategoryUseCase {
    override suspend fun execute(input: GetProductsByCategoryUseCase.Input): GetProductsByCategoryUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = productRepository.getAllProductsByCategory(input.categoryId)
            GetProductsByCategoryUseCase.Output(result)
        }
    }
}