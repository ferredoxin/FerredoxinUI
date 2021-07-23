package org.ferredoxin.ferredoxin_ui.base

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
    override var onClickListener: (FragmentActivity) -> Boolean = { true }
}

open class UiChangeableItemFactory<T> : UiChangeablePreference<T>, UiClickableItemFactory() {
    override val value: MutableLiveData<T> = MutableLiveData()
}

open class UiSwitchItemFactory : UiSwitchPreference, UiChangeableItemFactory<Boolean>() {
    override var onClickListener: (FragmentActivity) -> Boolean = { true }
}

class UiClickableSwitchFactory : UiSwitchItemFactory(), UiClickableSwitchPreference

class MaterialAlertDialogPreferenceFactory : UiChangeablePreference<String> {
    override lateinit var title: String
    override var summary: String? = null
    override var valid: Boolean = true
    override var onClickListener: (FragmentActivity) -> Boolean = { true }
    override val value: MutableLiveData<String> = MutableLiveData()
    var materialAlertDialogBuilder: MaterialAlertDialogBuilder.() -> Unit = {}
}

class EditPreferenceFactory : UiChangeablePreference<String> {
    override val value: MutableLiveData<String> = MutableLiveData()
    override lateinit var title: String
    override var summary: String? = null
    override var valid: Boolean = true
    override lateinit var onClickListener: (FragmentActivity) -> Boolean
    var inputLayout: TextInputLayout.() -> Unit = {}
    var textInputEditText: TextInputEditText.() -> Unit = {}
}
