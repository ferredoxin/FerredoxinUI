@file:JvmName("UiDSLHelperKtAndroid")

package org.ferredoxin.ferredoxinui.common.base

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.ferredoxin.ferredoxinui.common.R

class ClickToActivity(val activity: Class<out Activity>) : (Activity) -> Boolean {
    override fun invoke(p1: Activity): Boolean {
        p1.startActivity(Intent(p1, activity))
        return true
    }
}

sealed class ClickListenerAgent : (Activity) -> Boolean {
    override fun invoke(p1: Activity): Boolean {
        throw IllegalAccessException("Invalid operation")
    }
}

class ClickToNewSetting(val uiScreen: UiScreen) : ClickListenerAgent()

class ClickToNewPages(val viewMap: ViewMap) : ClickListenerAgent()

fun uiDialogPreference(init: MaterialAlertDialogPreferenceFactory.() -> Unit): MaterialAlertDialogPreferenceFactory {
    val builder = MaterialAlertDialogPreferenceFactory()
    builder.init()
    builder.onClickListener = {
        val context = builder.contextWrapper(it)
        val builder2 = MaterialAlertDialogBuilder(context, R.style.MaterialDialog)
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
        val context = builder.contextWrapper(it)
        val builder2 = MaterialAlertDialogBuilder(context, R.style.MaterialDialog)
        val inputLayout = TextInputLayout(context, null, R.style.MaterialDialog)
        inputLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        builder.inputLayout.invoke(inputLayout)
        val textInputEditText = TextInputEditText(context, null, R.style.MaterialDialog)
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

fun <T> LifecycleOwner.observeStateFlow(stateFlow: StateFlow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateFlow.collect(action)
        }
    }
}