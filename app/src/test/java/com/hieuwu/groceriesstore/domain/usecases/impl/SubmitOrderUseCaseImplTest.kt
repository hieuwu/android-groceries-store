package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.OrderRepository
import com.hieuwu.groceriesstore.models.OrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SubmitOrderUseCaseImplTest {

    @Mock
    lateinit var mockedOrderRepository: OrderRepository
    private lateinit var testee: SubmitOrderUseCase

    @Before
    fun setUp() {
        testee = SubmitOrderUseCaseImpl(
            orderRepository = mockedOrderRepository,
            ioDispatcher = Dispatchers.IO
        )
    }

    @Test
    fun whenExecute_thenSubmitOrderSuccessfully() {
        runBlocking {
            whenever(mockedOrderRepository.sendOrderToServer(any())).thenReturn(true)
            testee(
                SubmitOrderUseCase.Input(
                    order = OrderModel(
                        lineItemList = mutableListOf()
                    )
                )
            )

            verify(mockedOrderRepository).sendOrderToServer(
                OrderModel(
                    lineItemList = mutableListOf()
                )
            )
        }
    }

}