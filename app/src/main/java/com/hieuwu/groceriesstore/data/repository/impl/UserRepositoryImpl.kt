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
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : UserRepository {

    override suspend fun createAccount(email: String, password: String, name: String): Boolean {
        var dbUser: User? = null
        var isSucess = false
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                Timber.d(task.exception)
                if (task.isSuccessful) {
                    // Create account success, update UI with the signed-in user's information
                    val userId = auth.currentUser!!.uid
                    val newUser = hashMapOf(
                        "name" to name,
                        "email" to email
                    )
                    isSucess = true

                    dbUser = User(
                        userId, name, email, null, null,
                        isOrderCreatedNotiEnabled = false,
                        isPromotionNotiEnabled = false,
                        isDataRefreshedNotiEnabled = false
                    )
                    fireStore.collection(CollectionNames.users).document(userId)
                        .set(newUser)
                        .addOnSuccessListener {
                            // Handle success
                            isSucess = true
                        }
                        .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }
                }
            }.addOnFailureListener { Exception -> Timber.d(Exception) }
            .await()
        if (isSucess) {
            withContext(Dispatchers.IO) {
                userDao.insert(dbUser!!)
            }
            return true
        } else return false
    }

    override suspend fun authenticate(email: String, password: String): Boolean {
        //TODO Make this method smaller by separating the concerns
        var isSuccess = false
        var id: String? = null
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        isSuccess = task.isSuccessful
                        id = auth.currentUser?.uid!!
                    }
                }.addOnFailureListener { ex -> throw ex }.await()
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

        withContext(Dispatchers.IO) {
            userDao.insert(user!!)
        }
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
