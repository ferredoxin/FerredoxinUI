package org.kitsunepie.maitungtmui.base

typealias UiMap = MutableMap<String, UiDescription>

sealed interface UiDescription

sealed interface UiGroup : UiDescription {
    var name: String
    var contains: UiMap
}

interface UiScreen : UiGroup {
    var summary: String?
}

interface UiCategory : UiGroup