package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.SearchProductUseCase
import javax.inject.Inject

class SearchProductUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    SearchProductUseCase {
    override suspend fun execute(input: SearchProductUseCase.Input): SearchProductUseCase.Output {
        val result = productRepository.searchProductsListByName(input.name)
        return SearchProductUseCase.Output(result)
    }
}