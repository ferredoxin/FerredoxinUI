package org.ferredoxin.ferredoxinui.android.fragment

import org.ferredoxin.ferredoxinui.common.base.*

val DemoFragment: UiScreen = uiScreen {
    name = "功能示例"
    contains = linkedMapOf(
        uiCategory {
            noTitle = true
            contains = linkedMapOf(
                uiClickableSwitchItem {
                    title = "开关带二级界面"
                    summary = "辅助文字"
                    onClickListener = ClickToNewSetting(DemoViewPagerFragment[1].second)
                },
                uiClickableSwitchItem {
                    title = "禁用开关带二级界面"
                    value.value = true
                    valid = false
                }
            )
        }
    )
}.second
