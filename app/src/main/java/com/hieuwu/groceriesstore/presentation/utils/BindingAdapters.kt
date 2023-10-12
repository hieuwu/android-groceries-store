package com.hieuwu.groceriesstore.presentation.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.RecipeModel
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.RecipeItemAdapter


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
fun bindRecyclerViewLine(recyclerView: RecyclerView, data: MutableList<LineItemModel>?) {
    val adapter = recyclerView.adapter as LineListItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("recipeListData")
fun bindRecyclerViewRecipe(recyclerView: RecyclerView, data: MutableList<RecipeModel>?) {
    val adapter = recyclerView.adapter as RecipeItemAdapter
    adapter.submitList(data)
}
