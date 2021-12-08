package org.ferredoxin.ferredoxinui.qnotified_style.item

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import org.ferredoxin.ferredoxinui.qnotified_style.R

class Switch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    var checked: Boolean = false
        set(value) {
            if (field == value) return
            field = value
            update(true)
            onValueChangedListener?.invoke(value)
        }

    init {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.Switch)
        val on = typedArray.getBoolean(R.styleable.Switch_checked, false)
        this.checked = on
        val enable = typedArray.getBoolean(R.styleable.Switch_enabled, true)
        this.isEnabled = enable
        typedArray.recycle()
        val params: ViewGroup.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        this.layoutParams = params
        setOnClickListener {
            checked = !checked
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        update(false)
    }

    @Synchronized
    private fun update(animate: Boolean) {
        if (!isEnabled) {
            if (checked) {
                setImageResource(R.drawable.ic_toggle_disable_on)
            } else {
                setImageResource(R.drawable.ic_toggle_disable_24px)
            }
        } else {
            if (animate) {
                showAnimate(checked)
            } else {
                if (checked) {
                    setImageResource(R.drawable.ic_toggle_on_24px)
                } else {
                    setImageResource(R.drawable.ic_toggle_off_24px)
                }
            }
        }
    }

    private fun showAnimate(on: Boolean) {
        isClickable = false
        val animateRes = if (on) R.drawable.switch_off_to_on else R.drawable.switch_on_to_off
        setImageResource(animateRes)
        AnimatedVectorDrawableCompat.clearAnimationCallbacks(drawable)
        AnimatedVectorDrawableCompat.registerAnimationCallback(
            drawable,
            object : Animatable2Compat.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) {
                    AnimatedVectorDrawableCompat.clearAnimationCallbacks(drawable)
                    val drawableRes =
                        if (on) R.drawable.ic_toggle_on_24px else R.drawable.ic_toggle_off_24px
                    setImageResource(drawableRes)
                    isClickable = true
                }
            })
        (drawable as? AnimatedVectorDrawable)?.start()
    }

    var onValueChangedListener: ((Boolean) -> Unit)? = null

}