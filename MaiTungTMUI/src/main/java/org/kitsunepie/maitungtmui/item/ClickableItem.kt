package org.kitsunepie.maitungtmui.item

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import org.kitsunepie.maitungtmui.R
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

    var enable: Boolean = true
        set(value) {
            if (value) {
                isClickable = true
                binding.textView2.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.SecondTextColor
                    )
                )
                binding.summary.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.SecondTextColor
                    )
                )
                ImageViewCompat.setImageTintList(
                    binding.imageView,
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.ItemRightIconColor
                        )
                    )
                )
            } else {
                isClickable = false
                binding.textView2.setTextColor(ContextCompat.getColor(context,R.color.DisableTextColor))
                binding.summary.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.DisableTextColor
                    )
                )
                ImageViewCompat.setImageTintList(
                    binding.imageView,
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.DisableTextColor
                        )
                    )
                )
            }
            field = value
        }

}