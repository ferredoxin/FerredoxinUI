package org.kitsunepie.maitungtmui.activity

import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.R
import org.kitsunepie.maitungtmui.base.TitleAble
import org.kitsunepie.maitungtmui.databinding.ActivityMaitungTmStyleBinding

abstract class MaiTungTMStyleActivity<T> : AppCompatActivity() where T : Fragment, T : TitleAble {

    open var showNavigationIcon: Boolean = true
        set(value) {
            TransitionManager.beginDelayedTransition(binding.constraintLayout3)
            binding.imageView3.isVisible = value
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