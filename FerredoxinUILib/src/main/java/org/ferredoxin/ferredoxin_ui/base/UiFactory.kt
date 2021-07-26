package org.ferredoxin.ferredoxin_ui.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

open class UiClickableItemFactory : UiPreference {
    override lateinit var title: String
    override var summary: String? = null
    override var valid: Boolean = true
    override var onClickListener: (Activity) -> Boolean = { true }
}

open class UiChangeableItemFactory<T> : UiChangeablePreference<T>, UiClickableItemFactory() {
    override val value: MutableLiveData<T> = MutableLiveData()
}

open class UiSwitchItemFactory : UiSwitchPreference, UiChangeableItemFactory<Boolean>() {
    override var onClickListener: (Activity) -> Boolean = { true }
}

class UiClickableSwitchFactory : UiSwitchItemFactory(), UiClickableSwitchPreference

interface ContextWrapper {
    val contextWrapper: (Context) -> Context
}

class MaterialAlertDialogPreferenceFactory : UiChangeablePreference<String>, ContextWrapper {
    override lateinit var title: String
    override var summary: String? = null
    override var valid: Boolean = true
    override var onClickListener: (Activity) -> Boolean = { true }
    override val value: MutableLiveData<String> = MutableLiveData()
    override var contextWrapper: (Context) -> Context = { it }
    var materialAlertDialogBuilder: MaterialAlertDialogBuilder.() -> Unit = {}
}

class EditPreferenceFactory : UiChangeablePreference<String>, ContextWrapper {
    override val value: MutableLiveData<String> = MutableLiveData()
    override lateinit var title: String
    override var summary: String? = null
    override var valid: Boolean = true
    override lateinit var onClickListener: (Activity) -> Boolean
    var inputLayout: TextInputLayout.() -> Unit = {}
    override var contextWrapper: (Context) -> Context = { it }
    var textInputEditText: TextInputEditText.() -> Unit = {}
}
