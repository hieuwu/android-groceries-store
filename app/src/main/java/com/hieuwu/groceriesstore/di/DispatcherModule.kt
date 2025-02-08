package com.hieuwu.groceriesstore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
object DispatchersName {
    val IO = "IoDispatcher"
    val DEFAULT = "DefaultDispatcher"
    val MAIN = "MainDispatcher"
}

val dispatchersModule = module {
    single(named(DispatchersName.IO)) {
        Dispatchers.IO
    }

    single(named(DispatchersName.DEFAULT)) {
        Dispatchers.Default
    }

    single(named(DispatchersName.MAIN)) {
        Dispatchers.Main
    }
}
