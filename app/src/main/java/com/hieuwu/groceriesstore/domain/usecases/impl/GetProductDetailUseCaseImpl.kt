package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase

class GetProductDetailUseCaseImpl (
    private val productRepository: ProductRepository,
) : GetProductDetailUseCase {
    override fun getProductDetail(productId: String) = productRepository.getProductById(productId)
}

