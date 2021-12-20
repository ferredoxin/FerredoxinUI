package org.ferredoxin.ferredoxinui.qnotified_style.base

import android.content.DialogInterface
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.ferredoxin.ferredoxinui.common.base.EditPreferenceFactory
import org.ferredoxin.ferredoxinui.common.base.MaterialAlertDialogPreferenceFactory
import org.ferredoxin.ferredoxinui.common.base.UiChangeablePreference
import org.ferredoxin.ferredoxinui.qnotified_style.R

fun uiDialogPreference(init: MaterialAlertDialogPreferenceFactory.() -> Unit): Pair<String, MaterialAlertDialogPreferenceFactory> {
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
    return builder.title to builder
}

fun uiEditTextPreference(init: EditPreferenceFactory.() -> Unit): Pair<String, UiChangeablePreference<String>> {
    val builder = EditPreferenceFactory()
    builder.init()
    builder.onClickListener = {
        val context = builder.contextWrapper(it)
        val builder2 = MaterialAlertDialogBuilder(context, R.style.MaterialDialog)
        val inputLayout = TextInputLayout(context, null, builder.theme)
        inputLayout.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        builder.inputLayout.invoke(inputLayout)
        val textInputEditText = TextInputEditText(context, null)
        inputLayout.addView(textInputEditText, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
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
    return builder.title to builder
}
