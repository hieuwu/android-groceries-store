package com.hieuwu.groceriesstore.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.databinding.LayoutGridListItemBinding
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.android.synthetic.main.layout_grid_list_item.view.*

class GridListItemAdapter(val onClickListener: OnClickListener) :
    ListAdapter<ProductModel, GridListItemAdapter.ProductGridViewHolder>(DiffCallback) {

    class ProductGridViewHolder(private var binding: LayoutGridListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productModel: ProductModel) {
            binding.product = productModel
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ProductGridViewHolder, position: Int) {
        val product = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(product)
        }

        holder.itemView.product_add_button.setOnClickListener {
            onClickListener.addToCartListener(product)
        }

        holder.bind(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductGridViewHolder {
        return ProductGridViewHolder(LayoutGridListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(
        val clickListener: (product: ProductModel) -> Unit,
        val addToCartListener: (product: ProductModel) -> Unit
    ) {
        fun onClick(product: ProductModel) = clickListener(product)
        fun onAddButtonClick(product: ProductModel) = addToCartListener(product)
    }
}
