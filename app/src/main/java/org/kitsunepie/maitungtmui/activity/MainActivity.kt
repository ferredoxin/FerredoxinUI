package org.kitsunepie.maitungtmui.activity

import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.UiTable
import org.kitsunepie.maitungtmui.fragment.MaiTungTMSettingFragment

class MainActivity : MaiTungTMStyleActivity() {

    override val title: String = "MaiTungTM UI Demo"
    override val showNavigationIcon: Boolean = false
    override val fragment: Fragment = MaiTungTMSettingFragment().setUiScreen(UiTable)

}