package org.kitsunepie.maitungtmui.base

import android.content.Context
import org.kitsunepie.maitungtmui.fragment.ViewMap

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

fun <T> uiChangeableItem(init: UiChangeableItemFactory<T>.() -> Unit): Pair<String, UiChangeablePreference<T>> {
    val uiChangeableItemFactory = UiChangeableItemFactory<T>()
    uiChangeableItemFactory.init()
    return Pair(uiChangeableItemFactory.title, uiChangeableItemFactory)
}

sealed class ClickListenerAgent : (Context) -> Boolean {
    override fun invoke(p1: Context): Boolean {
        throw IllegalAccessException("Invalid operation")
    }
}

class ClickToNewSetting(val uiScreen: UiScreen) : ClickListenerAgent()

class ClickToNewPages(val viewMap: ViewMap) : ClickListenerAgent()