package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.SearchProductUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SearchProductUseCaseImpl (
    private val productRepository: ProductRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : SearchProductUseCase {
    override suspend fun invoke(input: SearchProductUseCase.Input): SearchProductUseCase.Output {
        return withContext(ioDispatcher) {
            val result = productRepository.searchProductsListByName(input.name)
            SearchProductUseCase.Output(result)
        }
    }
}