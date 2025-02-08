package com.hieuwu.groceriesstore.works

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class RefreshDatabaseWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) , KoinComponent {
     val refreshAppDataUseCase: RefreshAppDataUseCase by inject()

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
