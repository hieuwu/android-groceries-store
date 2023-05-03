package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.SearchProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchProductUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    SearchProductUseCase {
    override suspend fun execute(input: SearchProductUseCase.Input): SearchProductUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = productRepository.searchProductsListByName(input.name)
            SearchProductUseCase.Output(result)
        }
    }
}