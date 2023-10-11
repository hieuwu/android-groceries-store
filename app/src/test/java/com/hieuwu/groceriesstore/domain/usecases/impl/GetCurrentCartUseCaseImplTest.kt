package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)

class GetCurrentCartUseCaseImplTest {

    @Mock
    lateinit var mockedOrderRepository: OrderRepository

    private lateinit var testee: GetCurrentCartUseCaseImpl

    @Before
    fun setUp() {
        testee = GetCurrentCartUseCaseImpl(
            orderRepository = mockedOrderRepository
        )
    }

    @Test
    fun givenCartNotEmpty_whenExecute_thenReturnOrdersInCart() {
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
            val result = testee.execute(GetCurrentCartUseCase.Input())

            result.result.collect { order ->
                assertEquals(order?.id, "")
                assertEquals(order?.status, "cart")
                assertEquals(order?.address, null)
                assertEquals(order?.lineItemList!!.isEmpty(), true)
                assertEquals(order.createdAt, "")
            }
        }
    }

    @Test
    fun givenCartEmpty_whenExecute_thenReturnNull() {
        whenever(mockedOrderRepository.getOneOrderByStatus(OrderStatus.IN_CART)).thenReturn(flow {
            null
        })
        runBlocking {
            val result = testee.execute(GetCurrentCartUseCase.Input())
            result.result.collect {
                assertNull(it)
            }
        }
    }
}