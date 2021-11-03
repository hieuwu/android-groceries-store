package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.UserDao
import com.hieuwu.groceriesstore.domain.entities.User
import com.hieuwu.groceriesstore.domain.entities.asDomainModel
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

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
                        "email" to email,
                    )
                    isSucess = true

                    dbUser = User(userId, name, email, null, null)
                    val db = Firebase.firestore
                    db.collection("users").document(userId)
                        .set(newUser)
                        .addOnSuccessListener {
                            //Handle success
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
        return true
    }

    override suspend fun authenticate(email: String, password: String): Boolean {
        var isSucess = false
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                Timber.d(task.exception)
                isSucess = task.isSuccessful
            }.addOnFailureListener { Exception -> Timber.d(Exception) }.await()
        return isSucess
    }

    override fun getCurrentUser() =
        Transformations.map(userDao.getCurrentUser().asLiveData()) {
            it.asDomainModel()
        }

}