package com.hieuwu.groceriesstore

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.hieuwu.groceriesstore.data.GroceriesStoreDatabase
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.domain.entities.Product
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GroceriesStoreDatabaseTest {


    private lateinit var productDao: ProductDao
    private lateinit var db: GroceriesStoreDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, GroceriesStoreDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        productDao = db.productDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetProduct() {
        val aProduct = Product("123", "Product Test","Test", 12.0)
        productDao.insert(aProduct)
        val product = productDao.getById(aProduct.id)
        assertEquals(product?.price, 12.0)
    }
}