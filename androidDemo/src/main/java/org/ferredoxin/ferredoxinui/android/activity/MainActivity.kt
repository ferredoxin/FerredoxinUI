package org.ferredoxin.ferredoxinui.android.activity

import androidx.fragment.app.Fragment
import org.ferredoxin.ferredoxinui.android.UiTable
import org.ferredoxin.ferredoxinui.common.base.TitleAble
import org.ferredoxin.ferredoxinui.qnotified_style.activity.MaiTungTMStyleActivity
import org.ferredoxin.ferredoxinui.qnotified_style.fragment.MaiTungTMSettingFragment

class MainActivity<T> : MaiTungTMStyleActivity<T>() where T : Fragment, T : TitleAble {

    override val fragment: T = MaiTungTMSettingFragment().setUiScreen(UiTable.second) as T

}
