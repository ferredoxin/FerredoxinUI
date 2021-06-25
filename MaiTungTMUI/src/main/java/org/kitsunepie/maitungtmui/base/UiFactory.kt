package org.kitsunepie.maitungtmui.base

import android.content.Context
import androidx.lifecycle.MutableLiveData

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
    override var enable: Boolean = true
    override var onClickListener: (Context) -> Boolean = { true }
}

open class UiChangeableItemFactory<T> : UiChangeablePreference<T>, UiClickableItemFactory() {
    override val value: MutableLiveData<T> = MutableLiveData()
}

open class UiSwitchItemFactory : UiSwitchPreference, UiChangeableItemFactory<Boolean>() {
    override var onClickListener: (Context) -> Boolean = { true }
}

class UiClickableSwitchFactory : UiSwitchItemFactory(), UiClickableSwitchPreference