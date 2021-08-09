package com.hieuwu.groceriesstore.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.databinding.LayoutGridListItemBinding
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel

class GridListItemAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Product, GridListItemAdapter.ProductGridViewHolder>(DiffCallback) {

    class ProductGridViewHolder(private var binding: LayoutGridListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productModel: Product) {
            binding.product = productModel
            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: ProductGridViewHolder, position: Int) {
        val product = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(product)
        }
        holder.bind(product)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductGridViewHolder {
        return ProductGridViewHolder(LayoutGridListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }
}