package com.hieuwu.groceriesstore.domain.mapper

interface Mapper<in From, out To>  {
    fun map(from: From): To
}