package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.hieuwu.groceriesstore.data.dao.UserDao
import com.hieuwu.groceriesstore.domain.entities.asDomainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) {

    fun getUser(id: String) = Transformations.map(userDao.getById(id).asLiveData()) {
        it.asDomainModel()
    }
}