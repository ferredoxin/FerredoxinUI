package org.ferredoxin.ferredoxinui.qnotified_style.item

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import org.ferredoxin.ferredoxinui.qnotified_style.databinding.ItemAboutBinding

class About @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ItemAboutBinding = ItemAboutBinding.inflate(LayoutInflater.from(context), this, true)

    fun setTitle(title: String) {
        binding.textView4.text = title
    }

    fun setIcon(drawable: Drawable) {
        binding.imageView5.setImageDrawable(drawable)
    }

}