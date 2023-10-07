package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductsListUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)

class GetProductsListUseCaseImplTest {

    @Mock
    lateinit var mockedProductsRepository: ProductRepository

    private lateinit var testee: GetProductsListUseCase

    @Before
    fun setUp() {
        testee = GetProductsListUseCaseImpl(
            productRepository = mockedProductsRepository
        )
    }

    @Test
    fun givenProductsAvailable_whenExecute_thenReturnCorrectValue() {
        val mockedProducts = listOf(
            ProductModel(id = "1", name = "Apple", price = 1.0, image = "image1.jpg", description = "Fruit", nutrition = "Healthy"),
            ProductModel(id = "2", name = "Fries", price = 5.0, image = "image2.jpg", description = "Junk food", nutrition = "Unhealthy"),
            ProductModel(id = "3", name = "Potato", price = 10.0, image = "image3.jpg", description = "Vegetable", nutrition = "Healthy"),
        )
        whenever(mockedProductsRepository.products).thenReturn(flow {
            emit(mockedProducts)
        })
        runBlocking {
            val result = testee.execute(GetProductsListUseCase.Input())

            result.result.collect { products ->
                assertEquals(mockedProducts[0], products[0])
                assertEquals(mockedProducts[1], products[1])
                assertEquals(mockedProducts[2], products[2])
                assertEquals(mockedProducts[1].name, "Fries")
            }
        }
    }

    @Test
    fun givenProductsUnavailable_whenExecute_thenReturnCorrectValue() {
        whenever(mockedProductsRepository.products).thenReturn(flow {
            emit(listOf())
        })

        runBlocking {
            val result = testee.execute(GetProductsListUseCase.Input())
            result.result.collect {
                assertEquals(it.isEmpty(), true)
            }
        }
    }

}