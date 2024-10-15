package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UpdateCartItemUseCaseImplTest {

    @Mock
    lateinit var mockedProductRepository: ProductRepository

    @Mock
    lateinit var mockedOrderRepository: OrderRepository

    private lateinit var testee: UpdateCartItemUseCase

    @Before
    fun setup() {
        testee = UpdateCartItemUseCaseImpl(
            productRepository = mockedProductRepository,
            orderRepository = mockedOrderRepository,
            ioDispatcher = Dispatchers.IO
        )
    }

    /**
     * Test for updating line item with a valid value
     */
    @Test
    fun updateLineItem_correctValue_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, 1)
            testee.updateLineItem(input)

            verify(mockedProductRepository).updateLineItemQuantityById(input.quantity!!, input.id!!)
        }
    }

    /**
     * Test for updateLineItem when quantity is null
     * Should throw IllegalArgumentException because quantity must be greater than zero
     */
    @Test(expected = IllegalArgumentException::class)
    fun updateLineItem_quantityNull_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, null)
            testee.updateLineItem(input)

//            Mockito.verifyNoInteractions(mockedOrderRepository)
        }
    }

    /**
     * Test for null ID should throw IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException::class)
    fun updateLineItem_idNull_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(null, null, null, null, null, 1)
            testee.updateLineItem(input)

            Mockito.verifyNoInteractions(mockedOrderRepository)
        }
    }

    /**
     * Test for removing line item with a valid ID
     */
    @Test
    fun removeLineItem_correctValue_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, null)
            testee.removeLineItem(input)

            verify(mockedProductRepository).removeLineItemById(input.id!!)
        }
    }

    /**
     * Test for null ID when removing line item should throw IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException::class)
    fun removeLineItem_null_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(null, null, null, null, null, null)
            testee.removeLineItem(input)

            Mockito.verifyNoInteractions(mockedOrderRepository)
        }
    }

    /**
     * Test for retrieving a non-empty cart
     */
    @Test
    fun getCurrentCartNotEmpty_whenExecute_thenReturnOrdersInCart() {
        val mockOrder = OrderModel(
            id = "",
            status = OrderStatus.IN_CART.value,
            address = null,
            lineItemList = mutableListOf(),
            createdAt = ""
        )
        whenever(mockedOrderRepository.getOneOrderByStatus(OrderStatus.IN_CART)).thenReturn(flow {
            emit(mockOrder)
        })
        runBlocking {
            val result = testee.getCurrentCart()

            result.collect { order ->
                TestCase.assertEquals(order?.id, "")
                TestCase.assertEquals(order?.status, "cart")
                TestCase.assertEquals(order?.address, null)
                TestCase.assertEquals(order?.lineItemList!!.isEmpty(), true)
                TestCase.assertEquals(order.createdAt, "")
            }
        }
    }

    /**
     * Test for retrieving an empty cart
     */
    @Test
    fun getCurrentCartEmpty_whenExecute_thenReturnNull() {
        whenever(mockedOrderRepository.getOneOrderByStatus(OrderStatus.IN_CART)).thenReturn(flow {
            emit(null)
        })
        runBlocking {
            val result = testee.getCurrentCart()
            result.collect {
                TestCase.assertNull(it)
            }
        }
    }

    /**
     * Edge case for updateLineItem with minimum quantity
     */
    @Test
    fun updateLineItem_minQuantity_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, 1) // minimum valid quantity
            testee.updateLineItem(input)

            verify(mockedProductRepository).updateLineItemQuantityById(1, input.id!!)
        }
    }

    /**
     * Edge case for updateLineItem with maximum quantity
     */
    @Test
    fun updateLineItem_maxQuantity_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, 1000) // maximum valid quantity
            testee.updateLineItem(input)

            verify(mockedProductRepository).updateLineItemQuantityById(1000, input.id!!)
        }
    }

    /**
     * Test for updateLineItem when invalid quantity
     */
    @Test(expected = IllegalArgumentException::class)
    fun updateLineItem_invalidQuantity_whenExecute_thenThrowException() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, -1) // invalid quantity
            testee.updateLineItem(input)
        }
    }

    /**
     * Test for updateLineItem when Zero quantity
     */
    @Test(expected = IllegalArgumentException::class)
    fun updateLineItem_zeroQuantity_whenExecute_thenThrowException() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, 0) // zero quantity
            testee.updateLineItem(input)
        }
    }

    /**
     * Test removeLineItem with null values
     */
    @Test(expected = IllegalArgumentException::class)
    fun removeLineItem_nullId_whenExecute_thenThrowException() {
        runBlocking {
            val input = LineItemModel(null, null, null, null, null, null)
            testee.removeLineItem(input)
        }
    }

    /**
     * Test getCurrentCart with a populated cart
     */
    @Test
    fun getCurrentCartWithItems_whenExecute_thenReturnCorrectCart() {
        val mockOrder = OrderModel(
            id = "123",
            status = OrderStatus.IN_CART.value,
            address = "Test Address",
            lineItemList = mutableListOf(
                LineItemModel(1L, null, null, null, null, 2),
                LineItemModel(2L, null, null, null, null, 3)
            ),
            createdAt = "2024-10-15"
        )
        whenever(mockedOrderRepository.getOneOrderByStatus(OrderStatus.IN_CART)).thenReturn(flow {
            emit(mockOrder)
        })
        runBlocking {
            val result = testee.getCurrentCart()

            result.collect { order ->
                TestCase.assertNotNull(order)
                TestCase.assertEquals(order?.lineItemList?.size, 2)
                TestCase.assertEquals(order?.id, "123")
                TestCase.assertEquals(order?.address, "Test Address")
            }
        }
    }

    /**
     * Verify interaction with OrderRepository during removeLineItem
     */
    @Test
    fun removeLineItem_correctValue_whenExecute_thenNoInteractionWithOrderRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, null)
            testee.removeLineItem(input)

            verify(mockedProductRepository).removeLineItemById(input.id!!)
            Mockito.verifyNoInteractions(mockedOrderRepository)
        }
    }
}