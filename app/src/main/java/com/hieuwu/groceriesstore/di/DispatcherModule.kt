package com.hieuwu.groceriesstore.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

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
