package org.ferredoxin.ferredoxinui.common.base

typealias UiMap = MutableMap<String, UiDescription>

interface UiDescription

sealed interface UiGroup : UiDescription {
    val name: String
    val nameProvider: ResourceProvider<String>
    val contains: UiMap
}

interface UiScreen : UiGroup {
    val summaryProvider: ResourceProvider<String?>
        get() = DirectResourceProvider(null)
    val onClickHook: (UiDescription) -> Unit
    val onValueChangeHook: (UiDescription) -> Unit
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
