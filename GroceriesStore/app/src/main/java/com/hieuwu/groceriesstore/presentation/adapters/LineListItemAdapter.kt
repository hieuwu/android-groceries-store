package com.hieuwu.groceriesstore.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.databinding.LayoutLineListItemBinding
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import kotlinx.android.synthetic.main.layout_line_list_item.view.*

class LineListItemAdapter(val onClickListener: OnClickListener) :
    ListAdapter<ProductAndLineItem, LineListItemAdapter.LineItemViewHolder>(DiffCallback) {
    class LineItemViewHolder(private var binding: LayoutLineListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lineItemModel: ProductAndLineItem) {
            binding.lineItem = lineItemModel
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: LineItemViewHolder, position: Int) {
        val lineItem = getItem(position)
        holder.itemView.plus_btn.setOnClickListener {
            onClickListener.onPlusClick(lineItem)
        }

        holder.itemView.minus_btn.setOnClickListener {
            onClickListener.onMinusClick(lineItem)
        }

        holder.bind(lineItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineItemViewHolder {
        return LineItemViewHolder(LayoutLineListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProductAndLineItem>() {
        override fun areItemsTheSame(
            oldItem: ProductAndLineItem,
            newItem: ProductAndLineItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductAndLineItem,
            newItem: ProductAndLineItem
        ): Boolean {
            return (oldItem == newItem)
        }
    }

    class OnClickListener constructor(
        var minusListener: (lineItemModel: ProductAndLineItem) -> Unit,
        var plusListener: (lineItemModel: ProductAndLineItem) -> Unit
    ) {

        fun onMinusClick(lineItemModel: ProductAndLineItem) = minusListener(lineItemModel)
        fun onPlusClick(lineItemModel: ProductAndLineItem) = plusListener(lineItemModel)

    }
}