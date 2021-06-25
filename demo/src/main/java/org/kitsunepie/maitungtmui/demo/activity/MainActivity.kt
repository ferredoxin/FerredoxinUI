package org.kitsunepie.maitungtmui.demo.activity

import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.activity.MaiTungTMStyleActivity
import org.kitsunepie.maitungtmui.base.TitleAble
import org.kitsunepie.maitungtmui.demo.UiTable
import org.kitsunepie.maitungtmui.fragment.MaiTungTMSettingFragment

class MainActivity<T> : MaiTungTMStyleActivity<T>() where T : Fragment, T : TitleAble {

    override val fragment: T = MaiTungTMSettingFragment().setUiScreen(UiTable) as T

}