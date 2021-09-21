package com.hieuwu.groceriesstore.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.utilities.CATEGORY_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)

    @Query("SELECT * FROM `${CATEGORY_TABLE}`")
    fun getAll(): Flow<List<Category>>
}

