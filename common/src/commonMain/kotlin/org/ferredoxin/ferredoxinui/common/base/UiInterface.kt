package org.ferredoxin.ferredoxinui.common.base

typealias UiMap = MutableMap<String, UiDescription>

interface UiDescription

sealed interface UiGroup : UiDescription {
    val name: String
    val contains: UiMap
}

interface UiScreen : UiGroup {
    val summary: String?
        get() = null
}

interface UiCategory : UiGroup {
    val noTitle: Boolean
        get() = false
}

interface UiItem : UiDescription {
    val preference: UiDescription
    val preferenceLocate: Array<String>?
        get() = null
}

interface UiSwitchItem : UiItem {
    override val preference: UiSwitchPreference
}

expect interface UiAboutItem : UiDescription, TitleAble
