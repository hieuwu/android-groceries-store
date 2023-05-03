package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.database.dao.LineItemDao
import com.hieuwu.groceriesstore.data.database.dao.ProductDao
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.dto.ProductDto
import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.SupabaseMapper
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val lineItemDao: LineItemDao,
    private val supabasePostgrest: Postgrest
) : ProductRepository {

    override val products: Flow<List<ProductModel>> =
        productDao.getAll().map {
            it.asDomainModel()
        }

    override suspend fun refreshDatabase() {
        try {
            val result = supabasePostgrest[CollectionNames.products]
                .select().decodeList<ProductDto>()
            val products = result.map { SupabaseMapper.mapToEntity(it) }
            productDao.insertAll(products)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override suspend fun updateLineItemQuantityById(quantity: Int, id: Long) {
        try {
            lineItemDao.updateQuantityById(quantity, id)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override suspend fun removeLineItemById(id: Long) {
        try {
            lineItemDao.removeLineItemById(id)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override fun searchProductsListByName(name: String?) =
        productDao.searchProductByName(name).map { it.asDomainModel() }

    override fun getAllProductsByCategory(categoryId: String): Flow<List<ProductModel>> {
        return try {
            productDao.getAllByCategory(categoryId).map {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Timber.e(e.message)
            flow {}
        }
    }

    override fun getProductById(productId: String): Flow<ProductModel> {
        try {
            val productFlow = productDao.getById(productId)
            return productFlow.map {
                it.asDomainModel()
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return flow {}
    }

}
