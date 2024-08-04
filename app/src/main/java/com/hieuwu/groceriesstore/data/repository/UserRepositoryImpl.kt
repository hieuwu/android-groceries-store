package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.data.database.dao.UserDao
import com.hieuwu.groceriesstore.data.database.entities.User
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.dto.UserDto
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.data.network.RemoteTable
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import java.util.UUID
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val authService: Auth,
    private val postgrest: Postgrest,
) : UserRepository {

    override suspend fun createAccount(email: String, password: String, name: String): Boolean {
        return try {
            authService.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            val userDto = UserDto(
                id = UUID.randomUUID().toString(),
                name = name,
                email = email,
                address = null,
                phone = null,
                isOrderCreatedNotiEnabled = false,
                isPromotionNotiEnabled = false,
                isDataRefreshedNotiEnabled = false
            )
            postgrest[RemoteTable.Users.tableName].upsert(value = userDto)
            val user = userDto.asEntity()
            userDao.insert(user)
            true
        } catch (e: Exception) {
            Timber.e(e.message)
            false
        }
    }

    override suspend fun authenticate(email: String, password: String): Boolean {
        return try {
            authService.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val userDto = postgrest[RemoteTable.Users.tableName].select()
            {
                filter {
                    UserDto::email eq email
                }
            }
                .decodeSingle<UserDto>()
            val user = userDto.asEntity()
            userDao.insert(user)
            true
        } catch (e: Exception) {
            Timber.e(e.message)
            false
        }
    }

    override suspend fun updateUserProfile(
        userId: String,
        name: String,
        email: String,
        phone: String,
        address: String
    ) {
        val dbUser = User(
            userId, name, email, address, phone,
            isOrderCreatedNotiEnabled = false,
            isPromotionNotiEnabled = false,
            isDataRefreshedNotiEnabled = false
        )
        try {
            postgrest[RemoteTable.Users.tableName].update(
                {
                    UserDto::phone setTo phone
                    UserDto::email setTo email
                    UserDto::address setTo address
                }
            ) {
                UserDto::id to userId
            }
            userDao.insert(dbUser)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun clearUser() {
        userDao.clearUser()
    }

    override suspend fun updateUserSettings(
        id: String,
        isOrderCreatedEnabled: Boolean,
        isDatabaseRefreshedEnabled: Boolean,
        isPromotionEnabled: Boolean
    ) {
        try {
            postgrest[RemoteTable.Users.tableName].update(
                {
                    UserDto::isOrderCreatedNotiEnabled setTo isOrderCreatedEnabled
                    UserDto::isDataRefreshedNotiEnabled setTo isDatabaseRefreshedEnabled
                    UserDto::isPromotionNotiEnabled setTo isPromotionEnabled
                }
            ) {
                filter {
                    UserDto::id eq id
                }
            }
            userDao.updateUserSettings(
                id,
                isOrderCreatedEnabled,
                isDatabaseRefreshedEnabled,
                isPromotionEnabled
            )
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override fun getCurrentUser() = userDao.getCurrentUser().map {
        it?.asDomainModel()
    }

    private fun UserDto.asEntity(): User = User(
        id = id,
        name = name,
        email = email,
        address = address,
        phone = phone,
        isDataRefreshedNotiEnabled = isDataRefreshedNotiEnabled,
        isOrderCreatedNotiEnabled = isOrderCreatedNotiEnabled,
        isPromotionNotiEnabled = isPromotionNotiEnabled,
    )
}
