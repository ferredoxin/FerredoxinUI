package org.kitsunepie.maitungtmui.base

typealias UiMap = MutableMap<String, UiDescription>

sealed interface UiDescription

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