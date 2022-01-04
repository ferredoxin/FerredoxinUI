package org.ferredoxin.ferredoxinui.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import org.ferredoxin.ferredoxinui.common.R
import org.ferredoxin.ferredoxinui.common.base.TitleAble
import org.ferredoxin.ferredoxinui.common.base.UiDescription
import org.ferredoxin.ferredoxinui.common.base.ViewMap
import org.ferredoxin.ferredoxinui.common.base.uiScreen
import org.ferredoxin.ferredoxinui.common.fragment.MaterialSettingFragment
import java.io.*

abstract class MaterialSettingActivity<T> : AppCompatActivity() where T : PreferenceFragmentCompat, T : TitleAble {

    abstract val theme: MaterialTheme
    abstract val fragment: T

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        @StyleRes val themeInt: Int = when (theme) {
            MaterialTheme.Classic -> com.google.android.material.R.style.Theme_MaterialComponents_DayNight_NoActionBar
            MaterialTheme.You -> com.google.android.material.R.style.Theme_Material3_DayNight_NoActionBar
        }
        setTheme(themeInt)
        super.onCreate(savedInstanceState)
        @LayoutRes val layoutInt: Int = when (theme) {
            MaterialTheme.Classic -> R.layout.activity_material_setting
            MaterialTheme.You -> R.layout.activity_material3_setting
        }
        setContentView(layoutInt)
        toolbar = findViewById(R.id.toolbar)
        changeFragment(fragment)
    }

    fun <T> changeFragment(fragment: T) where T : Fragment, T : TitleAble {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
            .replace(R.id.content_frame, fragment).addToBackStack(fragment.title).commit()
        toolbar.title = fragment.title
    }

    fun changeFragment(viewMap: ViewMap, string: String) {
        val uiScreen = uiScreen {
            title = string
            contains = mutableMapOf<String, UiDescription>().apply {
                for (pair in viewMap) {
                    this[pair.first] = pair.second
                }
            }
        }.second
        val rootFragment = MaterialSettingFragment().setUiScreen(uiScreen)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
            .replace(R.id.content_frame, rootFragment).addToBackStack(uiScreen.name).commit()
        toolbar.title = uiScreen.name
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            toolbar.title = supportFragmentManager.getBackStackEntryAt(0).name
            supportFragmentManager.popBackStack()
        }
    }

    override fun recreate() {
        finish()
        startActivity(intent)
    }

    enum class MaterialTheme {
        Classic, You
    }

}