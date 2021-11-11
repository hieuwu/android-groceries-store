package com.hieuwu.groceriesstore.works

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import timber.log.Timber
import javax.inject.Inject

class RefreshDatabaseWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository
    override suspend fun doWork(): Result {
        return try {
            Timber.d("Refreshing database")
            productRepository.getFromServer()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshDatabaseWorker"
    }

}