package org.kitsunepie.maitungtmui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.R
import org.kitsunepie.maitungtmui.base.TitleAble
import org.kitsunepie.maitungtmui.databinding.ActivityMaitungTmStyleBinding

abstract class MaiTungTMStyleActivity<T> : AppCompatActivity() where T : Fragment, T : TitleAble {

    open val showNavigationIcon: Boolean = true
    abstract val fragment: T

    private lateinit var binding: ActivityMaitungTmStyleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaitungTmStyleBinding.inflate(layoutInflater)
        if (!showNavigationIcon) {
            binding.imageView3.visibility = View.GONE
        }
        binding.imageView3.setOnClickListener {
            onBackPressed()
        }
        setContentView(binding.root)
        addFragment(fragment)
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
        this.title = fragment.title
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
            this.title = supportFragmentManager.getBackStackEntryAt(0).name
        }
    }

    override fun recreate() {
        finish()
        startActivity(intent)
    }

}