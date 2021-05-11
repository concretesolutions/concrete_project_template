package br.com.concrete.ghpulls.util

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemVerticalSpaceDecorator(
    @DimenRes private val itemOffsetRes: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val offset = parent.context.resources.getDimensionPixelOffset(itemOffsetRes)
        outRect.set(outRect.left, outRect.top, outRect.right, offset)
    }
}