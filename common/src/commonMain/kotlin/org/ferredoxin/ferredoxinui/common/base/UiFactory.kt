package org.ferredoxin.ferredoxinui.common.base

import kotlinx.coroutines.flow.MutableStateFlow

class UiCategoryFactory : UiCategory {
    override var name: String = ""
    override var contains: UiMap = linkedMapOf()
    override var noTitle: Boolean = false
}

class UiScreenFactory : UiScreen {
    override var summary: String? = null
    override lateinit var name: String
    override var contains: UiMap = linkedMapOf()
}

expect open class UiClickableItemFactory() : UiPreference {
    override var title: String
    override var summary: String?
    override var valid: Boolean
    override var clickAble: Boolean
    override var subSummary: String?
}

expect open class UiChangeableItemFactory<T>() : UiChangeablePreference<T>, UiClickableItemFactory {
    override val value: MutableStateFlow<T?>
}

expect open class UiSwitchItemFactory() : UiSwitchPreference, UiChangeableItemFactory<Boolean>

class UiClickableSwitchFactory : UiSwitchItemFactory(), UiClickableSwitchPreference

expect class UiAboutItemFactory() : UiAboutItem