package com.hieuwu.groceriesstore.domain.mapper.entitymapper

interface EntityMapper<From, To> {
    fun mapFromEntity(from: From): To
    fun mapToEntity(from: To): From
}