package com.hieuwu.groceriesstore

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.hieuwu.groceriesstore.works.RefreshDatabaseWork
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class GroceriesStoreApplication : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            val repeatingRequest =
                PeriodicWorkRequestBuilder<RefreshDatabaseWork>(1, TimeUnit.DAYS).build()
            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                RefreshDatabaseWork.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP, repeatingRequest
            )

        }
    }
}