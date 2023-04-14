package com.hieuwu.groceriesstore.data.repository.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hieuwu.groceriesstore.data.database.dao.UserDao
import com.hieuwu.groceriesstore.data.database.entities.User
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.convertUserDocumentToEntity
import com.hieuwu.groceriesstore.utilities.convertUserEntityToDocument
import com.hieuwu.groceriesstore.utilities.createUpdateUserRequest
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val authService: GoTrue
) : UserRepository {

    override suspend fun createAccount(email: String, password: String, name: String): Boolean {
        val result = authService.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        return if (result != null) {
            val user = User(
                id = result.id,
                name = name,
                email = email,
                address = null,
                phone = null,
                isOrderCreatedNotiEnabled = false,
                isPromotionNotiEnabled = false,
                isDataRefreshedNotiEnabled = false
            )
            userDao.insert(user)
            true
        } else {
            false
        }
    }

    override suspend fun authenticate(email: String, password: String): Boolean {
        //TODO Make this method smaller by separating the concerns
        var isSuccess = false
        var id: String? = null
        try {
            val result = authService.loginWith(Email) {
                this.email = email
                this.password = password
                return@loginWith
            }
        } catch (e: Exception) {
            Timber.d(e.message)
            throw e
        }

        var user: User? = null
        fireStore.collection(CollectionNames.users).document(auth.currentUser?.uid!!).get()
            .addOnSuccessListener {
                it?.let {
                    user = convertUserDocumentToEntity(id!!, it)
                }
            }
            .addOnFailureListener { e -> Timber.d(e) }.await()
        userDao.insert(user!!)
        return isSuccess
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
        val newUser = convertUserEntityToDocument(dbUser)
        var isSuccess = false
        try {
            fireStore.collection(CollectionNames.users).document(userId)
                .set(newUser)
                .addOnSuccessListener {
                    isSuccess = true
                }
                .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }.await()
        } catch (e: Exception) {
            isSuccess = false
            Timber.e(e)
        }

        if (isSuccess) {
            withContext(Dispatchers.IO) {
                userDao.insert(dbUser)
            }
        }
    }

    override suspend fun clearUser() {
        withContext(Dispatchers.IO) {
            userDao.clear()
        }
    }

    override suspend fun updateUserSettings(
        id: String,
        isOrderCreatedEnabled: Boolean,
        isDatabaseRefreshedEnabled: Boolean,
        isPromotionEnabled: Boolean
    ) {
        val updateRequest = createUpdateUserRequest(
            isOrderCreatedEnabled,
            isDatabaseRefreshedEnabled,
            isPromotionEnabled
        )
        var isSuccess = false
        fireStore.collection(CollectionNames.users).document(id)
            .set(updateRequest)
            .addOnSuccessListener {
                isSuccess = true
            }
            .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }.await()
        if (isSuccess) {
            withContext(Dispatchers.IO) {
                userDao.updateUserSettings(
                    id,
                    isOrderCreatedEnabled,
                    isDatabaseRefreshedEnabled,
                    isPromotionEnabled
                )
            }
        }
    }

    override fun getCurrentUser() = userDao.getCurrentUser().map {
        it?.asDomainModel()
    }

}
