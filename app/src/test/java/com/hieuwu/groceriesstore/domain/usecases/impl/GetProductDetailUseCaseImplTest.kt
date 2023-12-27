package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
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
class GetProductDetailUseCaseImplTest {

    @Mock
    lateinit var mockedProductRepository: ProductRepository

    private lateinit var testee: GetProductDetailUseCase

    @Before
    fun setup() {
        testee = GetProductDetailUseCaseImpl(productRepository = mockedProductRepository)
    }

    @Test
    fun productDetailsAvailable_whenExecuted_thenReturnCorrectValue() {
        val input = "1"
        val mockProduct = ProductModel("1")

        whenever(mockedProductRepository.getProductById(input)).thenReturn(flow {
            emit(mockProduct)
        })

        runBlocking {
            val result = testee.getProductDetail(input)
            result.collect {
                TestCase.assertEquals(mockProduct, it)
            }
        }
    }
}