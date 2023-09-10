package com.hieuwu.groceriesstore.di

import android.content.Context
import android.content.SharedPreferences
import com.hieuwu.groceriesstore.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharePrefModule {

    @Provides
    @Singleton
    fun provideSharePrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.sync_status_pref_name),
            Context.MODE_PRIVATE
        )
    }
}