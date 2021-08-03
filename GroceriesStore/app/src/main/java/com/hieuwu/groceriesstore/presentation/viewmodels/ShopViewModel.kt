package com.hieuwu.groceriesstore.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.*
import java.io.IOException
import androidx.lifecycle.asLiveData
import com.hieuwu.groceriesstore.domain.entities.Product

import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var jsonFileString: String

    init {
        jsonFileString = ""
        viewModelScope.launch {
            getMarsRealEstateProperties()
        }

    }

    lateinit var products: List<ProductModel>

    fun getJsonDataFromAsset(context: Context, fileName: String) {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return
        }

        jsonFileString = jsonString
        val gson = Gson()
        val listPersonType = object : TypeToken<List<ProductModel>>() {}.type
        products = gson.fromJson(jsonFileString, listPersonType)
        productRepository.saveAll(products)
    }

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private suspend fun getMarsRealEstateProperties() {
        withContext(Dispatchers.IO) {
            _productList =
                productRepository.getAllProducts().asLiveData() as MutableLiveData<List<Product>>
        }
    }
}