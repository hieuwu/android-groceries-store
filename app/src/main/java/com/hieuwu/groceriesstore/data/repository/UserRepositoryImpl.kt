package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.UserDao
import com.hieuwu.groceriesstore.domain.entities.User
import com.hieuwu.groceriesstore.domain.entities.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) {
    private lateinit var auth: FirebaseAuth

    fun getUser(id: String) = Transformations.map(userDao.getById(id).asLiveData()) {
        it.asDomainModel()
    }

    suspend fun createAccount(email: String, password: String, name: String):Boolean {
        var dbUser: User? = null
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
                    dbUser = User(userId, name, email, null, null)
                    val db = Firebase.firestore
                    db.collection("users").document(userId)
                        .set(newUser)
                        .addOnSuccessListener {
                            //Handle success
                        }
                        .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }
                    //Save user information to database
                } else {
                    return false
                }

            }.addOnFailureListener { Exception -> Timber.d(Exception) }
        withContext(Dispatchers.IO) {
            userDao.insert(dbUser!!)
        }
        return true
    }
}