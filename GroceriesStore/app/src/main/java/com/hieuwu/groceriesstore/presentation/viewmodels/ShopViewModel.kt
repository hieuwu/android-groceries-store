package com.hieuwu.groceriesstore.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.*
import java.io.IOException
import androidx.lifecycle.asLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.domain.entities.Product
import timber.log.Timber

import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        fetchProductsFromServer()
        getProductsFromDatabase()
    }

    private fun fetchProductsFromServer() {
        uiScope.launch {
            getProductFromServer()
        }
    }

    private fun getProductsFromDatabase() {
        uiScope.launch {
            getProductFromLocal()
        }
    }

    lateinit var products: List<ProductModel>


    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList


    private suspend fun getProductFromLocal(){
        return withContext(Dispatchers.IO) {
            _productList = productRepository.getAllProducts().asLiveData() as MutableLiveData<List<Product>>
        }
    }

    private suspend fun getProductFromServer() {
        return withContext(Dispatchers.IO) {
            productRepository.getFromServer()
        }
    }
}