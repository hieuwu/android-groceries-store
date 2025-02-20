package com.hieuwu.groceriesstore.di

import com.hieuwu.groceriesstore.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val supabaseModule = module {
    single {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.API_KEY,
        ) {
            defaultSerializer = KotlinXSerializer(Json { ignoreUnknownKeys = true })
            install(Postgrest)
            install(Auth)
            install(Storage)
        }
    }

    single<Postgrest> {
        get<SupabaseClient>().postgrest
    }

    single<Auth> {
        get<SupabaseClient>().auth
    }
    single<Storage> {
        get<SupabaseClient>().storage
    }
}