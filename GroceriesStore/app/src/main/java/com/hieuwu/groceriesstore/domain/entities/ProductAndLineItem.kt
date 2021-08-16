package com.hieuwu.groceriesstore.domain.entities

import androidx.room.PrimaryKey

data class ProductWithLineItem(
    @PrimaryKey val lineItemId: Long,
    val productId: String
)