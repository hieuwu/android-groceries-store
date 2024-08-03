package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.core.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface GetProductDetailUseCase {
    fun getProductDetail(productId: String): Flow<ProductModel>
}

