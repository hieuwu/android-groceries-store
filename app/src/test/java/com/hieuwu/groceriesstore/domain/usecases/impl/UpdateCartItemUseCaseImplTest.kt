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

    @Test
    fun updateLineItem_correctValue_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, 1)
            testee.updateLineItem(input)

            verify(mockedProductRepository).updateLineItemQuantityById(input.quantity!!, input.id!!)
        }
    }

    @Test
    fun updateLineItem_quantityNull_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, null)
            testee.updateLineItem(input)

            Mockito.verifyNoInteractions(mockedOrderRepository)
        }
    }

    @Test
    fun updateLineItem_idNull_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(null, null, null, null, null, 1)
            testee.updateLineItem(input)

            Mockito.verifyNoInteractions(mockedOrderRepository)
        }
    }

    @Test
    fun removeLineItem_correctValue_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(1L, null, null, null, null, null)
            testee.removeLineItem(input)

            verify(mockedProductRepository).removeLineItemById(input.id!!)
        }
    }

    @Test
    fun removeLineItem_null_whenExecute_thenCallProductRepository() {
        runBlocking {
            val input = LineItemModel(null, null, null, null, null, null)
            testee.removeLineItem(input)

            Mockito.verifyNoInteractions(mockedOrderRepository)
        }
    }

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
}