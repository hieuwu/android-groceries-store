package com.hieuwu.groceriesstore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.google.firestore.v1.StructuredQuery
import com.hieuwu.groceriesstore.data.GroceriesStoreDatabase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.OrderDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.domain.entities.Product
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GroceriesStoreDatabaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var productDao: ProductDao
    private lateinit var linetItemDao: LineItemDao
    private lateinit var orderDao: OrderDao

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
        productDao = db.productDao()
        linetItemDao = db.lineItemDao()
        orderDao = db.orderDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetProduct() {
        val aProduct = Product("123", "Product Test", "Test", 12.0, "empty")
        productDao.insert(aProduct)
        var product = productDao.getById(aProduct.id).asLiveData()
        assertEquals(product.getOrAwaitValue().price, 12.0)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetLineItem() {
        val aProduct = Product("123", "Product Test", "Test", 12.0, "empty")
        productDao.insert(aProduct)
        val lineItem = LineItem(1, aProduct.id, "123", 4, 5.6)
        linetItemDao.insert(lineItem)
        var lineItemAfter = linetItemDao.getById(lineItem.id).asLiveData()

        assertEquals(lineItemAfter.getOrAwaitValue().productId, "123")
        assertEquals(lineItemAfter.getOrAwaitValue().quantity, 4)
        assertEquals(lineItemAfter.getOrAwaitValue().subtotal, 5.6)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetOrder() {
        val firstProduct = Product("1", "First Product", "Test", 12.0, "empty")
        val secondProduct = Product("2", "Second Product", "Test", 13.0, "empty")

        val order = Order("12", "Cart")

        val firstLineItem = LineItem(1, firstProduct.id, "12", 4, 5.6)
        val secondLineItem = LineItem(2, secondProduct.id, "12", 5, 7.0)

        orderDao.insert(order)
        linetItemDao.insert(firstLineItem)
        linetItemDao.insert(secondLineItem)

        var completedOrder = orderDao.getById("12").asLiveData()

        assertEquals(completedOrder.getOrAwaitValue().order.status, "Cart")
        assertEquals(completedOrder.getOrAwaitValue().order.id, "12")
        assertEquals(completedOrder.getOrAwaitValue().lineItemList.size, 2)
    }
}