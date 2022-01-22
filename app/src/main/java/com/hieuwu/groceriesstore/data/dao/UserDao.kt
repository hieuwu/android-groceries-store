package com.hieuwu.groceriesstore.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hieuwu.groceriesstore.data.entities.User
import com.hieuwu.groceriesstore.utilities.USER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM $USER_TABLE WHERE id = :id")
    fun getById(id: String): Flow<User>


    @Query("SELECT * FROM $USER_TABLE LIMIT 1")
    fun getCurrentUser(): Flow<User?>

    @Query("DELETE FROM  $USER_TABLE")
    suspend fun clear()

}