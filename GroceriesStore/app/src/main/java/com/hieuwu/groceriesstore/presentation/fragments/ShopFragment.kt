package com.hieuwu.groceriesstore.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentShopBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import com.hieuwu.groceriesstore.presentation.viewmodels.ShopViewModel
import com.hieuwu.groceriesstore.presentation.viewmodels.factory.ShopViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding

    @ProductRepo
    @Inject lateinit var productRepository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentShopBinding>(
            inflater, R.layout.fragment_shop, container, false
        )

        val viewModelFactory = ShopViewModelFactory(productRepository)
        val shopViewModel = ViewModelProvider(this, viewModelFactory)
            .get(ShopViewModel::class.java)

        shopViewModel.getJsonDataFromAsset(this.requireContext(),"SampleData.json")

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataSet: ArrayList<String> = ArrayList()
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")

        setUpRecyclerView(dataSet)
    }


    private fun setUpRecyclerView(dataSet: ArrayList<String>) {
        val adapter =
            GridListItemAdapter(
                dataSet
            )
        binding.exclusiveOfferRecyclerview.adapter = adapter
        binding.exclusiveOfferRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)

        binding.bestSellingRecyclerview.adapter = adapter
        binding.bestSellingRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)

        binding.recommendedRecyclerview.adapter = adapter
        binding.recommendedRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
    }


}