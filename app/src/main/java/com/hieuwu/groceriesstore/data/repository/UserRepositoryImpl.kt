package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.UserDao
import com.hieuwu.groceriesstore.data.entities.User
import com.hieuwu.groceriesstore.data.entities.asDomainModel
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.convertUserDocumentToEntity
import com.hieuwu.groceriesstore.utilities.convertUserEntityToDocument
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber

@Singleton
class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

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

                    dbUser = User(userId, name, email, null, null)
                    val db = Firebase.firestore
                    db.collection(CollectionNames.users).document(userId)
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
        var isSuccess = false
        val db = Firebase.firestore
        var id: String? = null
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                Timber.d(task.exception)
                isSuccess = task.isSuccessful
                id = auth.currentUser?.uid!!
            }.addOnFailureListener { Exception -> Timber.d(Exception) }.await()

        var user: User? = null
        db.collection(CollectionNames.users).document(auth.currentUser?.uid!!).get()
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
        val db = Firebase.firestore
        val dbUser = User(userId, name, email, address, phone)
        val newUser = convertUserEntityToDocument(dbUser)
        var isSuccess = false
        db.collection(CollectionNames.users).document(userId)
            .set(newUser)
            .addOnSuccessListener {
                isSuccess = true
            }
            .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }.await()
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

    override fun getCurrentUser() =
        Transformations.map(userDao.getCurrentUser().asLiveData()) {
            it?.asDomainModel()
        }
}
