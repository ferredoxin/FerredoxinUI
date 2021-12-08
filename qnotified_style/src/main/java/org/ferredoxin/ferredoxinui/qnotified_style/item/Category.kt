package org.ferredoxin.ferredoxinui.qnotified_style.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import org.ferredoxin.ferredoxinui.qnotified_style.databinding.ItemCategoryBinding

class Category @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ItemCategoryBinding =
        ItemCategoryBinding.inflate(LayoutInflater.from(context), this, true)

    fun addItem(view: View) {
        binding.linearLayout.addView(view)
    }

    val linearLayout = binding.linearLayout

}