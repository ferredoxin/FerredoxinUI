package org.ferredoxin.ferredoxin_ui.base

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.ferredoxin.ferredoxin_ui.R
import org.ferredoxin.ferredoxin_ui.fragment.ViewMap

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

class ClickToActivity(val activity: Class<out Activity>) : (FragmentActivity) -> Boolean {
    override fun invoke(p1: FragmentActivity): Boolean {
        p1.startActivity(Intent(p1, activity))
        return true
    }
}

sealed class ClickListenerAgent : (FragmentActivity) -> Boolean {
    override fun invoke(p1: FragmentActivity): Boolean {
        throw IllegalAccessException("Invalid operation")
    }
}

class ClickToNewSetting(val uiScreen: UiScreen) : ClickListenerAgent()

class ClickToNewPages(val viewMap: ViewMap) : ClickListenerAgent()

fun uiDialogPreference(init: MaterialAlertDialogPreferenceFactory.() -> Unit): MaterialAlertDialogPreferenceFactory {
    val builder = MaterialAlertDialogPreferenceFactory()
    builder.init()
    builder.onClickListener = {
        val builder2 = MaterialAlertDialogBuilder(it, R.style.MaterialDialog)
        builder2.setTitle(builder.title)
        builder.materialAlertDialogBuilder.invoke(builder2)
        builder2.create().show()
        true
    }
    return builder
}

fun uiEditTextPreference(init: EditPreferenceFactory.() -> Unit): UiChangeablePreference<String> {
    val builder = EditPreferenceFactory()
    builder.init()
    builder.onClickListener = {
        val builder2 = MaterialAlertDialogBuilder(it, R.style.MaterialDialog)
        val inputLayout = TextInputLayout(it, null, R.style.MaterialDialog)
        inputLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        builder.inputLayout.invoke(inputLayout)
        val textInputEditText = TextInputEditText(it, null, R.style.MaterialDialog)
        inputLayout.addView(
            textInputEditText,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        builder.textInputEditText.invoke(textInputEditText)
        builder2.setTitle(builder.title)
        builder2.setView(inputLayout)
        builder2.setNegativeButton("取消", null)
        builder2.setPositiveButton("确定") { dialog: DialogInterface, _: Int ->
            builder.value.value = textInputEditText.text.toString()
            dialog.dismiss()
        }
        builder2.create().show()
        true
    }
    return builder
}
