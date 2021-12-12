package com.example.fingraph.utils.views

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SelectTagItemDecorator(context: Context): RecyclerView.ItemDecoration() {

    private val itemMargin = 10

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemCount = state.itemCount
        val itemPosition = parent.getChildAdapterPosition(view)

        if(itemPosition == RecyclerView.NO_POSITION) return
        outRect.set(
            if(itemPosition == 0) itemMargin else itemMargin / 2,
            0,
            if(itemPosition == itemCount - 1) itemMargin else itemMargin / 2, 0
        )
    }
}