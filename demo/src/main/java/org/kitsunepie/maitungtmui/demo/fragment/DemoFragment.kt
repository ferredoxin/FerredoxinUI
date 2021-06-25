package org.kitsunepie.maitungtmui.demo.fragment

import org.kitsunepie.maitungtmui.base.ClickToNewSetting
import org.kitsunepie.maitungtmui.base.UiScreen
import org.kitsunepie.maitungtmui.base.uiClickableSwitchItem
import org.kitsunepie.maitungtmui.base.uiScreen

val DemoFragment: UiScreen = uiScreen {
    name = "功能示例"
    contains = linkedMapOf(
        uiClickableSwitchItem {
            title = "开关带二级界面"
            summary = "辅助文字"
            onClickListener = ClickToNewSetting(DemoViewPagerFragment[1].second)
        },
        uiClickableSwitchItem {
            title = "禁用开关带二级界面"
            value.value = true
            enable = false
        }
    )
}.second