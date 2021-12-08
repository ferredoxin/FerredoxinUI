package org.ferredoxin.ferredoxinui.qnotified_style.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import org.ferredoxin.ferredoxinui.qnotified_style.R
import org.ferredoxin.ferredoxinui.qnotified_style.databinding.ItemSwitchBinding

class SwitchItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ItemSwitchBinding =
        ItemSwitchBinding.inflate(LayoutInflater.from(context), this, true)

    var checked: Boolean
        get() {
            return binding.switch1.checked
        }
        set(value) {
            binding.switch1.checked = value
        }

    init {
        isClickable = true
        setOnClickListener {
            checked = !checked
        }
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

    var enable: Boolean = binding.switch1.isEnabled
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
            } else {
                isClickable = false
                binding.textView2.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.DisableTextColor
                    )
                )
                binding.summary.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.DisableTextColor
                    )
                )
            }
            binding.switch1.isEnabled = value
            field = value
        }

    var onValueChangedListener: ((Boolean) -> Unit)?
        get() {
            return binding.switch1.onValueChangedListener
        }
        set(value) {
            binding.switch1.onValueChangedListener = value
        }

}