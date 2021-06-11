package com.hieuwu.groceriesstore.data.mapper

import com.hieuwu.groceriesstore.domain.mapper.AtypeToBtype
import com.hieuwu.groceriesstore.domain.models.Order
import com.hieuwu.groceriesstore.domain.models.Product
import javax.inject.Inject

class AtypeToBtypeImpl @Inject constructor() : AtypeToBtype {
    override fun map(from: Product): Order {
        TODO("Not yet implemented")
    }
}