@file:JvmName("UiDSLHelperKtAndroid")

package org.ferredoxin.ferredoxinui.common.base

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

fun <T> LifecycleOwner.observeStateFlow(stateFlow: StateFlow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateFlow.collect(action)
        }
    }
}