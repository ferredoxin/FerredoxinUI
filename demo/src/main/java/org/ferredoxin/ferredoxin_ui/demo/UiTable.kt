package org.ferredoxin.ferredoxin_ui.demo

import org.ferredoxin.ferredoxin_ui.base.*
import org.ferredoxin.ferredoxin_ui.demo.fragment.DemoFragment
import org.ferredoxin.ferredoxin_ui.demo.fragment.DemoViewPagerFragment

object UiTable : UiScreen {
    override var name: String = "QNotified"
    override var summary: String? = null
    override var contains: UiMap = linkedMapOf(
        uiCategory {
            name = "模块设置"
            contains = linkedMapOf(
                uiClickableItem {
                    title = "净化功能"
                    onClickListener = ClickToNewPages(DemoViewPagerFragment)
                },
                uiClickableItem {
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
                }
            )
        }
    )
}
