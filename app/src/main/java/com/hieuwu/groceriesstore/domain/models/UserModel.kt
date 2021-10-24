package com.hieuwu.groceriesstore.domain.models

import com.google.firebase.firestore.IgnoreExtraProperties

data class UserModel(val id: String, val name: String? = null, val email: String?, val phone: String)