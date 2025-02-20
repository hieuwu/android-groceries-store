package com.hieuwu.groceriesstore.di

import android.content.Context
import com.hieuwu.groceriesstore.R
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPrefModule = module {
    single {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.sync_status_pref_name),
            Context.MODE_PRIVATE
        )
    }

}