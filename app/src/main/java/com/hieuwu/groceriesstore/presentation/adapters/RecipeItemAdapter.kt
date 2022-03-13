package com.hieuwu.groceriesstore.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.databinding.RecipeItemLayoutBinding
import com.hieuwu.groceriesstore.domain.models.RecipeModel

class RecipeItemAdapter(val onClickListener: OnClickListener) :
    ListAdapter<RecipeModel, RecipeItemAdapter.RecipeItemViewHolder>(DiffCallback) {

    class RecipeItemViewHolder(private var binding: RecipeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeModel) {
            binding.recipeModel = recipe
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val category = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(category)
        }
        holder.bind(category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        return RecipeItemViewHolder(
            RecipeItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    companion object DiffCallback : DiffUtil.ItemCallback<RecipeModel>() {
        override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (category: RecipeModel) -> Unit = {}) {
        fun onClick(category: RecipeModel) = clickListener(category)
    }
}