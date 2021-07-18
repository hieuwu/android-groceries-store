package com.hieuwu.groceriesstore.domain.mapper

interface EntityMapper <From,To> {
    fun mapFromEntity(from: From): To
    fun mapToEntity(from: To): From
}