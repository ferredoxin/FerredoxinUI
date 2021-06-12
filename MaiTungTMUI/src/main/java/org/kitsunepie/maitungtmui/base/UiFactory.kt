package org.kitsunepie.maitungtmui.base

import android.content.Context

class UiCategoryFactory : UiCategory {
    override lateinit var name: String
    override var contains: UiMap = linkedMapOf()
}

class UiScreenFactory : UiScreen {
    override var summary: String? = null
    override lateinit var name: String
    override var contains: UiMap = linkedMapOf()
}

class UiClickableItemFactory : UiPreference {
    override lateinit var title: String
    override var summary: String? = null
    override var onClickListener: (Context) -> Boolean = { true }
}
