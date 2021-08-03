package com.hieuwu.groceriesstore.presentation

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Product>?){
    val adapter = recyclerView.adapter as GridListItemAdapter
    adapter.submitList(data)
}