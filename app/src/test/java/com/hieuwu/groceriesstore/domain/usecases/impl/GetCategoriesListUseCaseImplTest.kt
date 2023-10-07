package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.usecases.GetCategoriesListUseCase
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

class GetCategoriesListUseCaseImplTest {

    @Mock
    lateinit var mockedCategoryRepository: CategoryRepository

    private lateinit var testee: GetCategoriesListUseCaseImpl

    @Before
    fun setUp() {
        testee = GetCategoriesListUseCaseImpl(
            categoryRepository = mockedCategoryRepository
        )
    }

    @Test
    fun givenCategoriesAvailable_whenExecute_thenReturnCorrectValue() {
        val mockCategories = listOf(
            CategoryModel(id = "1", name = "Category 1", image = "image1.jpg"),
            CategoryModel(id = "2", name = "Category 2", image = "image2.jpg"),
            CategoryModel(id = "3", name = "Category 3", image = "image3.jpg"),
            CategoryModel(id = "4", name = "Category 4", image = "image4.jpg")
        )
        whenever(mockedCategoryRepository.getFromLocal()).thenReturn(flow {
            emit(mockCategories)
        })
        runBlocking {
            val result = testee.execute(GetCategoriesListUseCase.Input())

            result.result.collect { categories ->
                assertEquals(mockCategories[0], categories[0])
                assertEquals(mockCategories[1], categories[1])
                assertEquals(mockCategories[2], categories[2])
                assertEquals(mockCategories[1].name, "Category 2")
                assertEquals(mockCategories[3].image, "image4.jpg")
            }
        }
    }

    @Test
    fun givenCategoriesUnavailable_whenExecute_thenReturnCorrectValue() {
        whenever(mockedCategoryRepository.getFromLocal()).thenReturn(flow {
            emit(listOf())
        })
        runBlocking {
            val result = testee.execute(GetCategoriesListUseCase.Input())
            result.result.collect {
                assertEquals(it.isEmpty(), true)
            }
        }
    }
}