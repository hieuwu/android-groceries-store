package com.hieuwu.groceriesstore.domain.models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserModel(val name: String? = null)