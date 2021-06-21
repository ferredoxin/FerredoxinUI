package org.kitsunepie.maitungtmui.activity

import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.UiTable
import org.kitsunepie.maitungtmui.base.TitleAble
import org.kitsunepie.maitungtmui.fragment.MaiTungTMSettingFragment

class MainActivity<T> : MaiTungTMStyleActivity<T>() where T : Fragment, T : TitleAble {

    override val showNavigationIcon: Boolean = true
    override val fragment: T = MaiTungTMSettingFragment().setUiScreen(UiTable) as T

}