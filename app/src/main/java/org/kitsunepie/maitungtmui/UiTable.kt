package org.kitsunepie.maitungtmui

import org.kitsunepie.maitungtmui.base.UiMap
import org.kitsunepie.maitungtmui.base.UiScreen
import org.kitsunepie.maitungtmui.base.uiCategory
import org.kitsunepie.maitungtmui.base.uiClickableItem

object UiTable : UiScreen {
    override var name: String = "QNotified"
    override var summary: String? = null
    override var contains: UiMap = linkedMapOf(
        uiCategory {
            name = "模块设置"
            contains = linkedMapOf(
                uiClickableItem {
                    title = "净化功能"
                },
                uiClickableItem {
                    title = "增强功能"
                },
                uiClickableItem {
                    title = "辅助功能"
                },
                uiClickableItem {
                    title = "其他功能"
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
        }
    )
}