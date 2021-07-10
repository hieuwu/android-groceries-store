package com.hieuwu.groceriesstore.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.LayoutGridListItemBinding

class GridListItemAdapter(private val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<GridListItemAdapter.GridListItemHolder>() {

    override fun onBindViewHolder(holder: GridListItemHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridListItemHolder {
        return GridListItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_grid_list_item,
                parent,
                false
            )
        )
    }


    class GridListItemHolder(
        private val binding: LayoutGridListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.productAddButton.setOnClickListener {
                print("Product item add button click")
            }
        }

        fun bind(item: String) {
            binding.apply {
                binding.productTitle.text = item
                binding.productDescription.text = item
                binding.productPrice.text = item
                executePendingBindings()
            }
        }
    }


    override fun getItemCount() = dataSet.size

}