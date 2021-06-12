package org.kitsunepie.maitungtmui.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import org.kitsunepie.maitungtmui.databinding.ItemClickableBinding

class ClickableItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ItemClickableBinding =
        ItemClickableBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isClickable = true
    }

    fun setTitle(string: String) {
        binding.textView2.text = string
    }

}