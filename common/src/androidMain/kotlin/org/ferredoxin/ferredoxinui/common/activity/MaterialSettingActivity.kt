package org.ferredoxin.ferredoxinui.common.activity

import android.os.Bundle
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

    abstract val theme: Int
    abstract val fragment: T

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_setting)
        toolbar = findViewById(R.id.toolbar)
        changeFragment(fragment)
    }

    fun <T> changeFragment(fragment: T) where T : Fragment, T : TitleAble {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(fragment.title)
            .commit()
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
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, rootFragment)
            .addToBackStack(uiScreen.name).commit()
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

}