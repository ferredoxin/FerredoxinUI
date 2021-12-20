package org.ferredoxin.ferredoxinui.common.base

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.MutableStateFlow

actual open class UiClickableItemFactory : UiPreference {
    actual override var title: String = ""
    actual override var summary: String? = null
    actual override var valid: Boolean = true
    actual override var clickAble: Boolean = true
    actual override var subSummary: String? = null
    override var onClickListener: (Activity) -> Boolean = { true }
    private lateinit var titleProviderCache: ResourceProvider<String>
    override var titleProvider: ResourceProvider<String>
        get() {
            if (this::titleProviderCache.isInitialized) {
                return titleProviderCache
            } else {
                return DirectResourceProvider(title)
            }
        }
        set(value) {
            titleProviderCache = value
        }
    private lateinit var summaryProviderCache: ResourceProvider<String?>
    override var summaryProvider: ResourceProvider<String?>
        get() {
            if (this::summaryProviderCache.isInitialized) {
                return summaryProviderCache
            } else {
                return DirectResourceProvider(summary)
            }
        }
        set(value) {
            summaryProviderCache = value
        }
}

actual open class UiChangeableItemFactory<T> : UiChangeablePreference<T>, UiClickableItemFactory() {
    actual override val value: MutableStateFlow<T?> = MutableStateFlow(null)
}

actual open class UiSwitchItemFactory : UiSwitchPreference, UiChangeableItemFactory<Boolean>() {
    override var onClickListener: (Activity) -> Boolean = { true }
}

interface ContextWrapper {
    val contextWrapper: (Context) -> Context
}

class MaterialAlertDialogPreferenceFactory : UiChangeablePreference<String>, ContextWrapper {
    override lateinit var title: String
    override var summary: String? = null
    override var valid: Boolean = true
    override var clickAble: Boolean = true
    override var subSummary: String? = null
    override var onClickListener: (Activity) -> Boolean = { true }
    override val value: MutableStateFlow<String?> = MutableStateFlow(null)
    override var contextWrapper: (Context) -> Context = { it }
    var materialAlertDialogBuilder: MaterialAlertDialogBuilder.() -> Unit = {}
    private lateinit var titleProviderCache: ResourceProvider<String>
    override var titleProvider: ResourceProvider<String>
        get() {
            if (this::titleProviderCache.isInitialized) {
                return titleProviderCache
            } else {
                return DirectResourceProvider(title)
            }
        }
        set(value) {
            titleProviderCache = value
        }
    private lateinit var summaryProviderCache: ResourceProvider<String?>
    override var summaryProvider: ResourceProvider<String?>
        get() {
            if (this::summaryProviderCache.isInitialized) {
                return summaryProviderCache
            } else {
                return DirectResourceProvider(summary)
            }
        }
        set(value) {
            summaryProviderCache = value
        }
}

class EditPreferenceFactory : UiChangeablePreference<String>, ContextWrapper {
    override val value: MutableStateFlow<String?> = MutableStateFlow(null)
    override lateinit var title: String
    override var summary: String? = null
    override var valid: Boolean = true
    override var clickAble: Boolean = true
    override var subSummary: String? = null
    var theme = com.google.android.material.R.style.Widget_MaterialComponents_TextInputLayout_FilledBox
    override lateinit var onClickListener: (Activity) -> Boolean
    var inputLayout: TextInputLayout.() -> Unit = {}
    override var contextWrapper: (Context) -> Context = { it }
    var textInputEditText: TextInputEditText.() -> Unit = {}
    private lateinit var titleProviderCache: ResourceProvider<String>
    override var titleProvider: ResourceProvider<String>
        get() {
            if (this::titleProviderCache.isInitialized) {
                return titleProviderCache
            } else {
                return DirectResourceProvider(title)
            }
        }
        set(value) {
            titleProviderCache = value
        }
    private lateinit var summaryProviderCache: ResourceProvider<String?>
    override var summaryProvider: ResourceProvider<String?>
        get() {
            if (this::summaryProviderCache.isInitialized) {
                return summaryProviderCache
            } else {
                return DirectResourceProvider(summary)
            }
        }
        set(value) {
            summaryProviderCache = value
        }
}

actual class UiAboutItemFactory : UiAboutItem {
    override lateinit var title: String
    override lateinit var icon: (Context) -> Drawable
    private lateinit var titleProviderCache: ResourceProvider<String>
    override var titleProvider: ResourceProvider<String>
        get() {
            if (this::titleProviderCache.isInitialized) {
                return titleProviderCache
            } else {
                return DirectResourceProvider(title)
            }
        }
        set(value) {
            titleProviderCache = value
        }
}
