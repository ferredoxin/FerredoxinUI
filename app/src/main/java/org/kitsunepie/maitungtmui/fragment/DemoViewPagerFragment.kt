package org.kitsunepie.maitungtmui.fragment

import org.kitsunepie.maitungtmui.base.uiCategory
import org.kitsunepie.maitungtmui.base.uiChangeableItem
import org.kitsunepie.maitungtmui.base.uiClickableItem
import org.kitsunepie.maitungtmui.base.uiScreen

val DemoViewPagerFragment: ViewMap = listOf(

    uiScreen {
        name = "主页"
        contains = linkedMapOf(
            uiCategory {
                name = "示例"
                contains = linkedMapOf(
                    uiClickableItem {
                        title = "不可用"
                        summary = "暂不开放"
                        enable = false
                    },
                    uiClickableItem {
                        title = "不可用 - 打开二级界面"
                        summary = "暂不开放"
                        enable = false
                    }
                )
            },
            uiCategory {
                name = "示例2"
                contains = linkedMapOf(
                    uiChangeableItem<String> {
                        title = "打开开关"
                        value.value = "虽然这不是一个开关"
                    }
                )
            },
            uiCategory {
                name = "示例3"
                contains = linkedMapOf(
                    uiClickableItem {
                        title = "不可用"
                        summary = "暂不开放"
                    },
                    uiClickableItem {
                        title = "不可用 - 打开二级界面"
                        summary = "暂不开放"
                    }
                )
            },
            uiCategory {
                name = "示例4"
                contains = linkedMapOf(
                    uiClickableItem {
                        title = "不可用"
                        summary = "暂不开放"
                    },
                    uiClickableItem {
                        title = "不可用 - 打开二级界面"
                        summary = "暂不开放"
                    }
                )
            }
        )
    },
    uiScreen {
        name = "侧滑"
        contains = linkedMapOf(
            uiCategory {
                name = "示例"
                contains = linkedMapOf(
                    uiClickableItem {
                        title = "不可用"
                        summary = "暂不开放"
                    },
                    uiClickableItem {
                        title = "不可用 - 打开二级界面"
                        summary = "暂不开放"
                    }
                )
            },
            uiCategory {
                name = "示例2"
                contains = linkedMapOf(
                    uiChangeableItem<String> {
                        title = "打开开关"
                        value.value = "虽然这不是一个开关"
                    }
                )
            }
        )
    },
    uiScreen {
        name = "聊天"
        contains = linkedMapOf(
            uiCategory {
                name = "示例"
                contains = linkedMapOf(
                    uiClickableItem {
                        title = "不可用"
                        summary = "暂不开放"
                    },
                    uiClickableItem {
                        title = "不可用 - 打开二级界面"
                        summary = "暂不开放"
                    }
                )
            },
            uiCategory {
                name = "示例2"
                contains = linkedMapOf(
                    uiChangeableItem<String> {
                        title = "打开开关"
                        value.value = "虽然这不是一个开关"
                    }
                )
            }
        )
    },
    uiScreen {
        name = "群聊"
        contains = linkedMapOf(
            uiCategory {
                name = "示例"
                contains = linkedMapOf(
                    uiClickableItem {
                        title = "不可用"
                        summary = "暂不开放"
                    },
                    uiClickableItem {
                        title = "不可用 - 打开二级界面"
                        summary = "暂不开放"
                    }
                )
            },
            uiCategory {
                name = "示例2"
                contains = linkedMapOf(
                    uiChangeableItem<String> {
                        title = "打开开关"
                        value.value = "虽然这不是一个开关"
                    }
                )
            }
        )
    },
    uiScreen {
        name = "扩展"
        contains = linkedMapOf(
            uiCategory {
                name = "示例"
                contains = linkedMapOf(
                    uiClickableItem {
                        title = "不可用"
                        summary = "暂不开放"
                    },
                    uiClickableItem {
                        title = "不可用 - 打开二级界面"
                        summary = "暂不开放"
                    }
                )
            },
            uiCategory {
                name = "示例2"
                contains = linkedMapOf(
                    uiChangeableItem<String> {
                        title = "打开开关"
                        value.value = "虽然这不是一个开关"
                    }
                )
            }
        )
    },
)