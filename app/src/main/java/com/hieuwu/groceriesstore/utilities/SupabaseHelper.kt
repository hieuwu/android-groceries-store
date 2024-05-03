package com.hieuwu.groceriesstore.utilities

import com.hieuwu.groceriesstore.BuildConfig

object SupabaseHelper {

    fun buildImageUrl(imageKey: String): String {
        val storageUrl = "${BuildConfig.SUPABASE_URL}/storage/v1/object/public"
        return "$storageUrl/$imageKey"
    }

}