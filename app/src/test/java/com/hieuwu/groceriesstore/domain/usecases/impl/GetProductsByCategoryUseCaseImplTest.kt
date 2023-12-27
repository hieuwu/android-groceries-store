package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCase
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetProductsByCategoryUseCaseImplTest {

    @Mock
    lateinit var mockedProductRepository: ProductRepository

    private lateinit var testee: GetProductsByCategoryUseCaseImpl

    @Before
    fun setup() {
        testee = GetProductsByCategoryUseCaseImpl(productRepository = mockedProductRepository)
    }

    @Test
    fun productsAvailable_whenExecute_thenReturnCorrectValue() {
        val input = GetProductsByCategoryUseCase.Input("20")
        val mockedProducts = listOf(ProductModel("1"), ProductModel("2"))

        whenever(mockedProductRepository.getAllProductsByCategory(input.categoryId)).thenReturn(flow {
            emit(mockedProducts)
        })

        runBlocking {
            val result = testee.execute(input)
            result.result.collect {
                TestCase.assertTrue(it.isNotEmpty())
                TestCase.assertEquals(mockedProducts[0], it[0])
                TestCase.assertEquals(mockedProducts[0].id, it[0].id)
            }
        }
    }

    @Test
    fun productsNotAvailable_whenExecute_thenReturnCorrectValue() {
        val input = GetProductsByCategoryUseCase.Input("20")
        val mockedProducts = listOf<ProductModel>()

        whenever(mockedProductRepository.getAllProductsByCategory(input.categoryId)).thenReturn(flow {
            emit(mockedProducts)
        })

        runBlocking {
            val result = testee.execute(input)
            result.result.collect {
                TestCase.assertTrue(it.isEmpty())
            }
        }
    }
}