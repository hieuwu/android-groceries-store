package com.hieuwu.groceriesstore.presentation

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.presentation.adapters.CategoryItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Product>?) {
    val adapter = recyclerView.adapter as GridListItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("lineListData")
fun bindRecyclerViewLine(recyclerView: RecyclerView, data: MutableList<ProductAndLineItem>?) {
    val adapter = recyclerView.adapter as LineListItemAdapter
    adapter.submitList(data)
}


@BindingAdapter("categoryListData")
fun bindRecyclerViewCategory(recyclerView: RecyclerView, data: MutableList<Category>?) {
    val adapter = recyclerView.adapter as CategoryItemAdapter
    adapter.submitList(data)
}