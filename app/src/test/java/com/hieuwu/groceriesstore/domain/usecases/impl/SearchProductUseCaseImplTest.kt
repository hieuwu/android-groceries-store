package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.SearchProductUseCase
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchProductUseCaseImplTest {

    @Mock
    lateinit var mockedProductRepository: ProductRepository

    private lateinit var testee: SearchProductUseCase

    @Before
    fun setup() {
        testee = SearchProductUseCaseImpl(
            productRepository = mockedProductRepository,
            ioDispatcher = Dispatchers.IO
        )
    }

    @Test
    fun productsAvailable_whenExecute_thenReturnCorrectValue() {
        val input = SearchProductUseCase.Input("abc")
        val mockProducts = listOf(ProductModel("1"), ProductModel("2"))

        whenever(mockedProductRepository.searchProductsListByName(input.name)).thenReturn(flow {
            emit(mockProducts)
        })

        runBlocking {
            val result = testee.execute(input)
            result.result.collect {
                TestCase.assertTrue(it.isNotEmpty())
                TestCase.assertEquals(mockProducts[0], it[0])
                TestCase.assertEquals(mockProducts[1].id, it[1].id)
            }
        }
    }

    @Test
    fun productsNotAvailable_whenExecute_thenReturnCorrectValue() {
        val input = SearchProductUseCase.Input("abc")
        val mockProducts = listOf<ProductModel>()

        whenever(mockedProductRepository.searchProductsListByName(input.name)).thenReturn(flow {
            emit(mockProducts)
        })

        runBlocking {
            val result = testee.execute(input)
            result.result.collect {
                TestCase.assertTrue(it.isEmpty())
            }
        }
    }
}