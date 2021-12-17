package org.ferredoxin.ferredoxinui.android.fragment

import org.ferredoxin.ferredoxinui.common.base.*
import org.ferredoxin.ferredoxinui.qnotified_style.R

val DemoAbout: UiScreen = uiScreen {
    name = "关于"
    contains = linkedMapOf(uiAboutItem {
        title = "Ferredoxin UI——QNotified Style demo"
        icon = {
            it.getDrawable(R.mipmap.ic_launcher_demo)!!
        }
    }, uiCategory {
        noTitle = true
        contains = linkedMapOf(uiClickableItem {
            title = "用户协议"
        }, uiClickableItem {
            title = "隐私条款"
        }, uiClickableItem {
            title = "模块版本"
            clickAble = false
            subSummary = "0.8.10.260.badce21"
        }, uiClickableItem {
            title = "QQ版本"
            clickAble = false
            subSummary = "8.5.5(1630)"
        }, uiClickableItem {
            title = "检查更新"
        })
    })
}.second