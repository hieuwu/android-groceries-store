package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface GetProductDetailUseCase {
    fun getProductDetail(productId: String): LiveData<ProductModel>
}
