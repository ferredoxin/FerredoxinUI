package org.kitsunepie.maitungtmui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.R
import org.kitsunepie.maitungtmui.databinding.ActivityMaitungTmStyleBinding

abstract class MaiTungTMStyleActivity : AppCompatActivity() {

    open val title: String = "MaiTungTM UI"
    open val showNavigationIcon: Boolean = true
    abstract val fragment: Fragment

    private lateinit var binding: ActivityMaitungTmStyleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaitungTmStyleBinding.inflate(layoutInflater)
        binding.toolbar.title = title
        if (!showNavigationIcon) {
            binding.toolbar.navigationIcon = null
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        addFragment(fragment)
    }

    fun setTitle(string: String) {
        binding.toolbar.title = title
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_right_in,
                R.anim.slide_left_out,
                R.anim.slide_left_in,
                R.anim.slide_right_out
            )
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.toString()).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}