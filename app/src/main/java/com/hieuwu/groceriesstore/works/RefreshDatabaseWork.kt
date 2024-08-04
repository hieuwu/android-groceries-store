package com.hieuwu.groceriesstore.works

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hieuwu.groceriesstore.usecase.RefreshAppDataUseCase
import javax.inject.Inject
import timber.log.Timber

class RefreshDatabaseWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    @Inject
    lateinit var refreshAppDataUseCase: RefreshAppDataUseCase

    override suspend fun doWork(): Result {
        return try {
            Timber.d("Refreshing database")
            refreshAppDataUseCase(Unit)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshDatabaseWorker"
    }
}
