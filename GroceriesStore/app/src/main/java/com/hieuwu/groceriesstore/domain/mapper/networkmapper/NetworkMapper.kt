package com.hieuwu.groceriesstore.domain.mapper.networkmapper

interface NetworkMapper <From,To> {
    fun mapFromNetwork(from: From): To
    fun mapToNetwork(from: To): From
}