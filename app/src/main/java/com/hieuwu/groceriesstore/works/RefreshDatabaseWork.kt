package com.hieuwu.groceriesstore.works

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import javax.inject.Inject

class RefreshDatabaseWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository
    override suspend fun doWork(): Result {
        return try {
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}