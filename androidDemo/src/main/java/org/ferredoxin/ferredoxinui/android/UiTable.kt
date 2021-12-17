package org.ferredoxin.ferredoxinui.android

import org.ferredoxin.ferredoxinui.android.fragment.DemoAbout
import org.ferredoxin.ferredoxinui.android.fragment.DemoFragment
import org.ferredoxin.ferredoxinui.android.fragment.DemoViewPagerFragment
import org.ferredoxin.ferredoxinui.common.base.*

val UiTable = uiScreen {
    name = "QNotified"
    contains = linkedMapOf(uiCategory {
        name = "模块设置"
        contains = linkedMapOf(uiClickableItem {
            title = "净化功能"
            onClickListener = ClickToNewPages(DemoViewPagerFragment)
        }, uiClickableItem {
            title = "增强功能"
            onClickListener = ClickToNewSetting(DemoViewPagerFragment[0].second)
                },
                uiClickableItem {
                    title = "辅助功能"
                    onClickListener = ClickToNewSetting(DemoFragment)
                },
                uiClickableItem {
                    title = "其他功能"
                    onClickListener = ClickToNewSetting(uiScreen {
                        name = "其他功能"
                    }.second)
                }
            )
        },
        uiCategory {
            name = "其他设置"
            contains = linkedMapOf(
                uiClickableItem {
                    title = "参数设定"
                },
                uiClickableItem {
                    title = "故障排查"
                }
            )
        },
        uiCategory {
            name = "更多"
            contains = linkedMapOf(
                uiClickableItem {
                    title = "鸽子画饼"
                },
                uiClickableItem {
                    title = "关于"
                    onClickListener = ClickToNewSetting(DemoAbout)
                }
            )
        }
    )
}
