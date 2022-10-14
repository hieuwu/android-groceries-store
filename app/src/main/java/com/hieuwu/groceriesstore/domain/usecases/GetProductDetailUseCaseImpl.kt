package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductDetailUseCase {
    override suspend fun execute(input: GetProductDetailUseCase.Input): GetProductDetailUseCase.Output {
        val result = productRepository.getProductById(input.id)
        return GetProductDetailUseCase.Output(result)
    }
}
