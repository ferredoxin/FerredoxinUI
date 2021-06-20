package org.kitsunepie.maitungtmui.item

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import org.kitsunepie.maitungtmui.R
import org.kitsunepie.maitungtmui.databinding.ItemSwitchBinding

class SwitchItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ItemSwitchBinding =
        ItemSwitchBinding.inflate(LayoutInflater.from(context), this, true)

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

    var value: Boolean? = null
        set(value) {
            if (value == null) {
                this.enable = false
                return
            }
            if (this.value == null) {
                if (value) {
                    binding.imageView2.setImageResource(R.drawable.ic_toggle_on_24px)
                } else {
                    binding.imageView2.setImageResource(R.drawable.ic_toggle_off_24px)
                }
                field = value
                return
            }
            if (this.value == value) return
            if (value) {
                isClickable = false
                binding.imageView2.setImageResource(R.drawable.switch_off_to_on)
                val animate = binding.imageView2.drawable
                AnimatedVectorDrawableCompat.clearAnimationCallbacks(animate)
                AnimatedVectorDrawableCompat.registerAnimationCallback(
                    animate,
                    object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable?) {
                            AnimatedVectorDrawableCompat.clearAnimationCallbacks(animate)
                            binding.imageView2.setImageResource(R.drawable.ic_toggle_on_24px)
                            isClickable = true
                        }
                    })
                if (animate is AnimatedVectorDrawable) {
                    animate.start()
                }
            } else {
                isClickable = false
                binding.imageView2.setImageResource(R.drawable.switch_on_to_off)
                val animate = binding.imageView2.drawable
                AnimatedVectorDrawableCompat.clearAnimationCallbacks(animate)
                AnimatedVectorDrawableCompat.registerAnimationCallback(
                    animate,
                    object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable?) {
                            AnimatedVectorDrawableCompat.clearAnimationCallbacks(animate)
                            binding.imageView2.setImageResource(R.drawable.ic_toggle_off_24px)
                            isClickable = true
                        }
                    })
                if (animate is AnimatedVectorDrawable) {
                    animate.start()
                }
            }
            field = value
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
                binding.textView2.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.SecondTextColor
                    )
                )
                if (value) {
                    binding.imageView2.setImageResource(R.drawable.ic_toggle_on_24px)
                } else {
                    binding.imageView2.setImageResource(R.drawable.ic_toggle_off_24px)
                }
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
                binding.imageView2.setImageResource(R.drawable.ic_toggle_disable_24px)
            }
            field = value
        }

}