package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.plugins.standaloneSupabaseModule
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.client.plugins.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseDatabase(): Postgrest {
        val postgrest = standaloneSupabaseModule(
            Postgrest,
            url = BuildConfig.SUPABASE_URL,
            apiKey = BuildConfig.API_KEY
        )

        return postgrest
    }

}
