package com.hieuwu.groceriesstore.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.databinding.LayoutCategoryGridItemBinding
import com.hieuwu.groceriesstore.domain.models.CategoryModel

class CategoryItemAdapter(val onClickListener: OnClickListener) :
    ListAdapter<CategoryModel, CategoryItemAdapter.CategoryItemViewHolder>(DiffCallback) {

    class CategoryItemViewHolder(private var binding: LayoutCategoryGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryModel) {
            binding.category = category
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val category = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(category)
        }
        holder.bind(category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            LayoutCategoryGridItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (category: CategoryModel) -> Unit) {
        fun onClick(category: CategoryModel) = clickListener(category)
    }
}