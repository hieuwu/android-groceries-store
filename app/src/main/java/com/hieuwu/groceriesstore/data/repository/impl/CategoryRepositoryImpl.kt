package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.database.dao.CategoryDao
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.dto.CategoriesDto
import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.SupabaseMapper
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(

    private val categoryDao: CategoryDao,
    private val supabasePostgrest: Postgrest
) :
    CategoryRepository {

    override suspend fun refreshDatabase() {
        try {
            val result = supabasePostgrest[CollectionNames.categories]
                .select()
            val res = result.decodeList<CategoriesDto>()
            val categories = res.map { SupabaseMapper.mapToEntity(it) }
            categoryDao.insertAll(categories)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override fun getFromLocal() = categoryDao.getAll().map { it.asDomainModel() }

}
