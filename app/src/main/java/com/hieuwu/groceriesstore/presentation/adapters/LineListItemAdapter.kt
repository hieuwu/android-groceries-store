package com.hieuwu.groceriesstore.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.databinding.LayoutLineListItemBinding
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import kotlinx.android.synthetic.main.layout_line_list_item.view.*

class LineListItemAdapter(val onClickListener: OnClickListener, val context: Context) :
    ListAdapter<LineItemModel, LineListItemAdapter.LineItemViewHolder>(DiffCallback) {

    class LineItemViewHolder(private var binding: LayoutLineListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lineItemModel: LineItemModel) {
            binding.lineItem = lineItemModel
            binding.executePendingBindings()
        }
    }

    fun removeItemAt(position: Int) {
        val lineItem = getItem(position)
        onClickListener.onRemoveItem(lineItem)
    }

    override fun onBindViewHolder(holder: LineItemViewHolder, position: Int) {
        val lineItem = getItem(position)
        holder.itemView.plus_btn.setOnClickListener {
            onClickListener.onPlusClick(lineItem)
        }

        holder.itemView.minus_btn.setOnClickListener {
            onClickListener.onMinusClick(lineItem)
        }

        holder.itemView.delete_btn.setOnClickListener {
            onClickListener.onRemoveItem(lineItem)
        }

        holder.bind(lineItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineItemViewHolder {
        return LineItemViewHolder(LayoutLineListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<LineItemModel>() {
        override fun areItemsTheSame(
            oldItem: LineItemModel,
            newItem: LineItemModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: LineItemModel,
            newItem: LineItemModel
        ): Boolean {
            return (oldItem.id == newItem.id)
        }
    }

    class OnClickListener constructor(
        var minusListener: (lineItemModel: LineItemModel) -> Unit = {},
        var plusListener: (lineItemModel: LineItemModel) -> Unit = {},
        var removeListener: (lineItemModel: LineItemModel) -> Unit = {}
    ) {
        fun onMinusClick(lineItemModel: LineItemModel) = minusListener(lineItemModel)
        fun onPlusClick(lineItemModel: LineItemModel) = plusListener(lineItemModel)
        fun onRemoveItem(lineItemModel: LineItemModel) = removeListener(lineItemModel)
    }
}
