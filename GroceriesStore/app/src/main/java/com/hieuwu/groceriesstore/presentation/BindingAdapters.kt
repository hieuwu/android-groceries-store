package com.hieuwu.groceriesstore.presentation

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ProductModel>?){
    val adapter = recyclerView.adapter as GridListItemAdapter
    adapter.submitList(data)
}