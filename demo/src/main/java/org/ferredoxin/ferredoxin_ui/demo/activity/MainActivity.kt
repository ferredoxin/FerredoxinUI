package org.ferredoxin.ferredoxin_ui.demo.activity

import androidx.fragment.app.Fragment
import org.ferredoxin.ferredoxin_ui.activity.MaiTungTMStyleActivity
import org.ferredoxin.ferredoxin_ui.base.TitleAble
import org.ferredoxin.ferredoxin_ui.demo.UiTable
import org.ferredoxin.ferredoxin_ui.fragment.MaiTungTMSettingFragment

class MainActivity<T> : MaiTungTMStyleActivity<T>() where T : Fragment, T : TitleAble {

    override val fragment: T = MaiTungTMSettingFragment().setUiScreen(UiTable) as T

}
