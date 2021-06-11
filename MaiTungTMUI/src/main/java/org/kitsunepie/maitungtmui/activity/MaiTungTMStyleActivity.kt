package org.kitsunepie.maitungtmui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kitsunepie.maitungtmui.databinding.ActivityMaitungTmStyleBinding

abstract class MaiTungTMStyleActivity : AppCompatActivity() {

    open val title: String = "MaiTungTM UI"
    open val showNavigationIcon: Boolean = true

    lateinit var binding: ActivityMaitungTmStyleBinding

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
    }

    fun setTitle(string: String) {
        binding.toolbar.title = title
    }

}