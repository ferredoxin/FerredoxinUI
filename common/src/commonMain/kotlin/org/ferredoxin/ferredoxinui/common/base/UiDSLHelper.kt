package org.ferredoxin.ferredoxinui.common.base

typealias ViewMap = List<Pair<String, UiScreen>>

fun uiCategory(init: UiCategoryFactory.() -> Unit): Pair<String, UiCategory> {
    val uiCategory = UiCategoryFactory()
    uiCategory.init()
    if (uiCategory.noTitle && uiCategory.name.isBlank()) {
        uiCategory.name = uiCategory.toString()
    }
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

fun <T> uiChangeableItem(init: UiChangeableItemFactory<T>.() -> Unit): Pair<String, UiChangeablePreference<T>> {
    val uiChangeableItemFactory = UiChangeableItemFactory<T>()
    uiChangeableItemFactory.init()
    return Pair(uiChangeableItemFactory.title, uiChangeableItemFactory)
}

fun uiSwitchItem(init: UiSwitchItemFactory.() -> Unit): Pair<String, UiSwitchPreference> {
    val uiSwitchItemFactory = UiSwitchItemFactory()
    uiSwitchItemFactory.init()
    return Pair(uiSwitchItemFactory.title, uiSwitchItemFactory)
}

fun uiClickableSwitchItem(init: UiClickableSwitchFactory.() -> Unit): Pair<String, UiClickableSwitchPreference> {
    val uiClickableSwitchFactory = UiClickableSwitchFactory()
    uiClickableSwitchFactory.init()
    return Pair(uiClickableSwitchFactory.title, uiClickableSwitchFactory)
}

fun uiAboutItem(init: UiAboutItemFactory.() -> Unit): Pair<String, UiAboutItem> {
    val uiAboutItemFactory = UiAboutItemFactory()
    uiAboutItemFactory.init()
    return Pair(uiAboutItemFactory.title, uiAboutItemFactory)
}