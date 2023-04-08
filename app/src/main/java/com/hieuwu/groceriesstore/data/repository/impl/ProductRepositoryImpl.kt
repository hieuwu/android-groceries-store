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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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
        val result = supabasePostgrest[CollectionNames.products]
            .select().decodeList<ProductDto>()
        val products = result.map { SupabaseMapper.mapToEntity(it) }
        productDao.insertAll(products)
    }

    override suspend fun updateLineItemQuantityById(quantity: Int, id: Long) {
        withContext(Dispatchers.IO) {
            lineItemDao.updateQuantityById(quantity, id)
        }
    }

    override suspend fun removeLineItemById(id: Long) {
        withContext(Dispatchers.IO) {
            lineItemDao.removeLineItemById(id)
        }
    }

    override fun searchProductsListByName(name: String?) =
        productDao.searchProductByName(name).map { it.asDomainModel() }

    override fun getAllProductsByCategory(categoryId: String) =
        productDao.getAllByCategory(categoryId).map {
            it.asDomainModel()
        }

    override fun getProductById(productId: String): Flow<ProductModel> {
        val productFlow = productDao.getById(productId)
        return productFlow.map {
            it.asDomainModel()
        }
    }

}
