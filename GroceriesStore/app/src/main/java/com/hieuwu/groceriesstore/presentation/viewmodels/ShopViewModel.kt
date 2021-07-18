package com.hieuwu.groceriesstore.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import java.io.IOException
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    var jsonFileString: String

    init {
        jsonFileString = ""
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

}