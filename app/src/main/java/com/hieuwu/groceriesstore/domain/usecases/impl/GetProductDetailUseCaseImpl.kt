package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import javax.inject.Inject

class GetProductDetailUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
) : GetProductDetailUseCase {
    override fun getProductDetail(productId: String) = productRepository.getProductById(productId)
}

