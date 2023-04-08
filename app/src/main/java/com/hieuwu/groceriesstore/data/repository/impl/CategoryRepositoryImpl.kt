package com.hieuwu.groceriesstore.data.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.database.dao.CategoryDao
import com.hieuwu.groceriesstore.data.database.entities.Category
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.dto.CategoriesDto
import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.SupabaseMapper
import com.hieuwu.groceriesstore.utilities.convertCategoryDocumentToEntity
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
class CategoryRepositoryImpl @Inject constructor(

    private val categoryDao: CategoryDao,
    private val supabasePostgrest: Postgrest
) :
    CategoryRepository {

    override suspend fun refreshDatabase() {
        val result = supabasePostgrest[CollectionNames.categories]
            .select()
        val res = result.decodeList<CategoriesDto>()
        val categories = res.map { SupabaseMapper.mapToEntity(it) }
        categoryDao.insertAll(categories)
    }

    override fun getFromLocal() = categoryDao.getAll().map { it.asDomainModel() }

}
