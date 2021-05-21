package com.hieuwu.groceriesstore.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.LayoutLineListItemBinding

class LineListItemAdapter(private val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<LineListItemAdapter.LineListItemHolder>() {

    override fun onBindViewHolder(holder: LineListItemHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineListItemHolder {
        return LineListItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_line_list_item,
                parent,
                false
            )
        )
    }


    class LineListItemHolder(
        private val binding: LayoutLineListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.apply {
                binding.productName.text = item
                binding.productDescription.text = item
                binding.productPrice.text = item
                executePendingBindings()
            }
        }
    }

    override fun getItemCount() = dataSet.size

}