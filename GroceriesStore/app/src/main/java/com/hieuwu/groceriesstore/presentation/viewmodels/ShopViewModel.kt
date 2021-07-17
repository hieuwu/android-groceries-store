package com.hieuwu.groceriesstore.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hieuwu.groceriesstore.di.ProductMapper
import com.hieuwu.groceriesstore.domain.mapper.ProductModelToEntity
import com.hieuwu.groceriesstore.domain.models.ProductModel
import java.io.IOException
import javax.inject.Inject

class ShopViewModel : ViewModel() {

    @ProductMapper
    @Inject
    lateinit var productModelToEntity: ProductModelToEntity

    lateinit var jsonFileString: String
    lateinit var products: List<ProductModel>

    init {
        val gson = Gson()
        val listPersonType = object : TypeToken<List<ProductModel>>() {}.type
        var products: List<ProductModel> = gson.fromJson(jsonFileString, listPersonType)
    }

    fun saveProducts() {
        products.forEachIndexed { idx, product ->
            var pro = productModelToEntity.map(product)
            Log.i("data", "> Item $idx:\n$product")
            Log.i("data x", "> Item $idx:\n$pro")
        }
    }


    fun getJsonDataFromAsset(context: Context, fileName: String) {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return
        }
        jsonFileString = jsonString
    }

}