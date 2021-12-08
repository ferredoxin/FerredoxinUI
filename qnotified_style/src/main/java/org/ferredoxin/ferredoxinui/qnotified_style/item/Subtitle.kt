package org.ferredoxin.ferredoxinui.qnotified_style.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import org.ferredoxin.ferredoxinui.qnotified_style.databinding.ItemSubtitleBinding

class Subtitle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ItemSubtitleBinding =
        ItemSubtitleBinding.inflate(LayoutInflater.from(context), this, true)

    fun setTitle(string: String) {
        binding.textView.text = string
    }

}