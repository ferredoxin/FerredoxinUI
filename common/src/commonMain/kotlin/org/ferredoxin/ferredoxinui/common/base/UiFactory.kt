package org.ferredoxin.ferredoxinui.common.base

import kotlinx.coroutines.flow.MutableStateFlow

class UiCategoryFactory : UiCategory {
    override var name: String = ""
    private lateinit var nameProviderCache: ResourceProvider<String>
    override var nameProvider: ResourceProvider<String>
        get() {
            if (this::nameProviderCache.isInitialized) {
                return nameProviderCache
            } else {
                return DirectResourceProvider(name)
            }
        }
        set(value) {
            nameProviderCache = value
        }
    override var contains: UiMap = linkedMapOf()
    override var noTitle: Boolean = false
}

class UiScreenFactory : UiScreen {
    override var name: String = ""
    private lateinit var nameProviderCache: ResourceProvider<String>
    override var nameProvider: ResourceProvider<String>
        get() {
            if (this::nameProviderCache.isInitialized) {
                return nameProviderCache
            } else {
                return DirectResourceProvider(name)
            }
        }
        set(value) {
            nameProviderCache = value
        }
    var summary: String? = null
    private lateinit var summaryProviderCache: ResourceProvider<String?>
    override var summaryProvider: ResourceProvider<String?>
        get() {
            if (this::summaryProviderCache.isInitialized) {
                return summaryProviderCache
            } else {
                return DirectResourceProvider(name)
            }
        }
        set(value) {
            summaryProviderCache = value
        }
    override var contains: UiMap = linkedMapOf()
    override var onClickHook: (UiDescription) -> Unit = {}
    override var onValueChangeHook: (UiDescription) -> Unit = {}
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