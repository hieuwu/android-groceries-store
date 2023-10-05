package com.hieuwu.groceriesstore.presentation.productlist

import androidx.compose.runtime.Immutable
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
class ProductListViewState(
    val categoryTitle: String = "",
    val productList: ImmutableList<ProductModel> = persistentListOf()
) {
    companion object {
        val Empty = ProductListViewState()
    }
}
