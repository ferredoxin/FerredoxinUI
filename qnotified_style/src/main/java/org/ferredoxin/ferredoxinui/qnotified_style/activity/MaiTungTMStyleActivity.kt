package org.ferredoxin.ferredoxinui.qnotified_style.activity

import android.animation.ObjectAnimator
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.ferredoxin.ferredoxinui.common.base.TitleAble
import org.ferredoxin.ferredoxinui.qnotified_style.R
import org.ferredoxin.ferredoxinui.qnotified_style.databinding.ActivityMaitungTmStyleBinding

abstract class MaiTungTMStyleActivity<T> : AppCompatActivity() where T : Fragment, T : TitleAble {

    private val translation: Float by lazy {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40F, resources.displayMetrics)
    }

    open var showNavigationIcon: Boolean = true
        set(value) {
            if (value == field) return
            if (value) {
                ObjectAnimator.ofFloat(binding.constraintLayout3, "translationX", 0F).apply {
                    duration = 400
                    start()
                }
            } else {
                ObjectAnimator.ofFloat(binding.constraintLayout3, "translationX", -translation)
                    .apply {
                        duration = 400
                        start()
                    }
            }
            field = value
        }

    abstract val fragment: T

    private lateinit var binding: ActivityMaitungTmStyleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaitungTmStyleBinding.inflate(layoutInflater)
        binding.imageView3.setOnClickListener {
            onBackPressed()
        }
        binding.title.setFactory {
            val textView = TextView(this)
            textView.setTextColor(ContextCompat.getColor(this, R.color.FirstTextColor))
            textView.textSize = 20F
            textView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            textView
        }
        setContentView(binding.root)
        addFragment(fragment)
        showNavigationIcon = false
    }

    var title: String? = null
        set(value) {
            binding.title.setText(value)
            field = value
        }

    fun <T> addFragment(fragment: T) where T : Fragment, T : TitleAble {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_left_in,
                R.anim.slide_right_out,
                R.anim.slide_right_in,
                R.anim.slide_left_out
            )
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.title).commit()
        showNavigationIcon = true
        binding.title.inAnimation =
            AnimationUtils.loadAnimation(this, R.anim.slide_left_in_no_alpha)
        binding.title.outAnimation =
            AnimationUtils.loadAnimation(this, R.anim.slide_right_out_no_alpha)
        this.title = fragment.title
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            showNavigationIcon = supportFragmentManager.backStackEntryCount != 2
            this.title = supportFragmentManager.getBackStackEntryAt(0).name
            supportFragmentManager.popBackStack()
            binding.title.inAnimation =
                AnimationUtils.loadAnimation(this, R.anim.slide_right_in_no_alpha)
            binding.title.outAnimation =
                AnimationUtils.loadAnimation(this, R.anim.slide_left_out_no_alpha)
        }
    }

    override fun recreate() {
        finish()
        startActivity(intent)
    }

}