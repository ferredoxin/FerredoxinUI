package org.kitsunepie.maitungtmui.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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

    var title: String
        get() = binding.textView2.text.toString()
        set(value) {
            binding.textView2.text = value
        }

    var summary: String?
        get() = binding.summary.text.toString()
        set(value) {
            binding.summary.text = value
            if (value.isNullOrBlank()) {
                binding.summary.visibility = View.GONE
            } else {
                binding.summary.visibility = View.VISIBLE
            }
        }

    var value: String?
        get() = binding.value.text.toString()
        set(value) {
            binding.value.text = value
        }

}