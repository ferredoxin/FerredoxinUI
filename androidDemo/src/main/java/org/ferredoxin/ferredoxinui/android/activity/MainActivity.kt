package org.ferredoxin.ferredoxinui.android.activity

import androidx.preference.PreferenceFragmentCompat
import org.ferredoxin.ferredoxinui.android.UiTable
import org.ferredoxin.ferredoxinui.common.activity.MaterialSettingActivity
import org.ferredoxin.ferredoxinui.common.base.TitleAble
import org.ferredoxin.ferredoxinui.common.fragment.MaterialSettingFragment

class MainActivity<T> : MaterialSettingActivity<T>() where T : PreferenceFragmentCompat, T : TitleAble {

    override val theme: Int = com.google.android.material.R.style.Theme_Material3_DayNight_NoActionBar
    override val fragment: T = MaterialSettingFragment().setUiScreen(UiTable.second) as T

}
