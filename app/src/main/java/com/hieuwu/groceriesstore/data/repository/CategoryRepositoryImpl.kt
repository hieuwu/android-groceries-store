package com.hieuwu.groceriesstore.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.CategoryDao
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao) :
    CategoryRepository {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    override fun getFromServer() {
        val fireStore = Firebase.firestore
        fireStore.collection("categories").get().addOnSuccessListener { result ->
            for (document in result) {
                val id = document.id
                val name: String = document.data["name"] as String
                val image: String = document.data["image"] as String
                executorService.execute {
                    categoryDao.insert(Category(id, name, image))
                }
            }
        }
            .addOnFailureListener { exception ->
                Timber.w("Error getting documents.${exception}")
            }
    }

    override fun getFromLocal() = categoryDao.getAll()
}