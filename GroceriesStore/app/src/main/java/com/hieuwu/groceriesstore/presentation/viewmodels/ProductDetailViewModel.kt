package com.hieuwu.groceriesstore.presentation.viewmodels

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    id: String,
    private val productRepository: ProductRepository
) : ViewModel() {

    val product = productRepository.getById(id).asLiveData()

}