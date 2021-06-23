package org.kitsunepie.maitungtmui.activity

import android.graphics.Rect
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.transition.TransitionValues
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.R
import org.kitsunepie.maitungtmui.base.TitleAble
import org.kitsunepie.maitungtmui.databinding.ActivityMaitungTmStyleBinding
import kotlin.properties.Delegates

abstract class MaiTungTMStyleActivity<T> : AppCompatActivity() where T : Fragment, T : TitleAble {

    private val transition = TransitionSet().apply {
        ordering = TransitionSet.ORDERING_SEQUENTIAL
        addTransition(Fade(Fade.OUT))
        addTransition(object : ChangeBounds() {
            var width by Delegates.notNull<Int>()
            override fun captureStartValues(transitionValues: TransitionValues) {
                super.captureStartValues(transitionValues)
                if (transitionValues.view == binding.title) {
                    val rect = transitionValues.values["android:changeBounds:bounds"] as Rect
                    width = rect.right - rect.left
                }
            }

            override fun captureEndValues(transitionValues: TransitionValues) {
                super.captureEndValues(transitionValues)
                if (transitionValues.view == binding.title) {
                    val rect = transitionValues.values["android:changeBounds:bounds"] as Rect
                    rect.right = rect.left + width
                }
            }
        })
        addTransition(Fade(Fade.IN))
    }

    open var showNavigationIcon: Boolean = true
        set(value) {
            TransitionManager.beginDelayedTransition(binding.toolbar, transition)
            binding.imageView3.visibility = if (value) View.VISIBLE else View.GONE
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
        setContentView(binding.root)
        addFragment(fragment)
        showNavigationIcon = false
    }

    var title: String?
        get() {
            return binding.title.text.toString()
        }
        set(value) {
            binding.title.text = value
        }

    fun <T> addFragment(fragment: T) where T : Fragment, T : TitleAble {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_right_in,
                R.anim.slide_left_out,
                R.anim.slide_left_in,
                R.anim.slide_right_out
            )
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.title).commit()
        showNavigationIcon = true
        this.title = fragment.title
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            showNavigationIcon = supportFragmentManager.backStackEntryCount != 2
            supportFragmentManager.popBackStack()
            this.title = supportFragmentManager.getBackStackEntryAt(0).name
        }
    }

    override fun recreate() {
        finish()
        startActivity(intent)
    }

}