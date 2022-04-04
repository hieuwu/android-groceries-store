package com.hieuwu.groceriesstore.presentation.adapters

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hieuwu.groceriesstore.R

class SwipeToDeleteCallback constructor(val adapter: LineListItemAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private var _icon: Drawable? =
        ContextCompat.getDrawable(adapter.context, R.drawable.ic_baseline_delete)
    private var _background: ColorDrawable = ColorDrawable(Color.RED)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        adapter.removeItemAt(position)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 20
        val iconMargin = (itemView.height - _icon?.intrinsicHeight!!) / 2
        val iconTop = itemView.top + (itemView.height - _icon?.intrinsicHeight!!) / 2
        val iconBottom = iconTop + _icon?.intrinsicHeight!!

        when {
            // Swiping to the left
            dX < 0 -> {
                val iconLeft = itemView.right - iconMargin - _icon?.intrinsicWidth!!
                val iconRight = itemView.right - iconMargin
                _icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)

                _background.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top, itemView.right, itemView.bottom
                )
            }
            else -> {
                _background.setBounds(0, 0, 0, 0)
            }
        }
        _background.draw(c)
        _icon?.draw(c)
    }
}
