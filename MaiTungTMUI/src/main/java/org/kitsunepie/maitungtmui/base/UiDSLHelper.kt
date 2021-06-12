package org.kitsunepie.maitungtmui.base

fun uiCategory(init: UiCategory.() -> Unit): Pair<String, UiCategory> {
    val uiCategory = UiCategoryFactory()
    uiCategory.init()
    return Pair(uiCategory.name, uiCategory)
}

fun uiScreen(init: UiScreenFactory.() -> Unit): Pair<String, UiScreen> {
    val uiScreenFactory = UiScreenFactory()
    uiScreenFactory.init()
    return Pair(uiScreenFactory.name, uiScreenFactory)
}

fun uiClickableItem(init: UiClickableItemFactory.() -> Unit): Pair<String, UiPreference> {
    val uiClickableItemFactory = UiClickableItemFactory()
    uiClickableItemFactory.init()
    return Pair(uiClickableItemFactory.title, uiClickableItemFactory)
}