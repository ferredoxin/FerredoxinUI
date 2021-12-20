package org.ferredoxin.ferredoxinui.android.fragment

import android.widget.TextView
import org.ferredoxin.ferredoxinui.common.base.*
import org.ferredoxin.ferredoxinui.qnotified_style.base.uiDialogPreference
import org.ferredoxin.ferredoxinui.qnotified_style.base.uiEditTextPreference

val DemoFragment: UiScreen = uiScreen {
    name = "功能示例"
    contains = linkedMapOf(
        uiCategory {
            noTitle = true
            contains = linkedMapOf(uiDialogPreference {
                title = "对话框测试"
                materialAlertDialogBuilder = {
                    setTitle("对话框测试")
                    setView(TextView(context).apply {
                        text = "测试文本"
                    })
                    setPositiveButton("确定", null)
                }
            }, uiEditTextPreference {
                title = "文本测试"
                inputLayout = {
                    hint = "一个提示"
                    helperText = "帮助文本"
                }
            }, uiClickableSwitchItem {
                title = "开关带二级界面"
                summary = "辅助文字"
                onClickListener = ClickToNewSetting(DemoViewPagerFragment[1].second)
            }, uiClickableSwitchItem {
                title = "禁用开关带二级界面"
                value.value = true
                valid = false
            }
            )
        }
    )
}.second
