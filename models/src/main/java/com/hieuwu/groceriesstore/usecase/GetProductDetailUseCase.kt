package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface GetProductDetailUseCase {
    fun getProductDetail(productId: String): Flow<ProductModel>
}

