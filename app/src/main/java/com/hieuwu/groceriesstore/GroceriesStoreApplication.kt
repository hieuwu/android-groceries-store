package com.hieuwu.groceriesstore

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.hieuwu.groceriesstore.di.databaseModule
import com.hieuwu.groceriesstore.di.dispatchersModule
import com.hieuwu.groceriesstore.di.repositoryModule
import com.hieuwu.groceriesstore.di.sharedPrefModule
import com.hieuwu.groceriesstore.di.supabaseModule
import com.hieuwu.groceriesstore.di.usecaseModule
import com.hieuwu.groceriesstore.di.viewModelModule
import com.hieuwu.groceriesstore.works.RefreshDatabaseWork
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class GroceriesStoreApplication : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@GroceriesStoreApplication)
            modules(listOf(
                dispatchersModule,
                repositoryModule,
                usecaseModule,
                databaseModule,
                supabaseModule,
                sharedPrefModule,
                viewModelModule
            ))
        }

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
