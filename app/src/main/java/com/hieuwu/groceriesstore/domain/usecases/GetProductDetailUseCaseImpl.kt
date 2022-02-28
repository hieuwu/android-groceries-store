package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
) : GetProductDetailUseCase{
    override fun getProductDetail(productId: String) = productRepository.getProductById(productId)
}