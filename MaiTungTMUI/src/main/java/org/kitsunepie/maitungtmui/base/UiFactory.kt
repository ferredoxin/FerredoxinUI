package org.kitsunepie.maitungtmui.base

import android.content.Context
import androidx.lifecycle.MutableLiveData

class UiCategoryFactory : UiCategory {
    override lateinit var name: String
    override var contains: UiMap = linkedMapOf()
}

class UiScreenFactory : UiScreen {
    override var summary: String? = null
    override lateinit var name: String
    override var contains: UiMap = linkedMapOf()
}

class UiClickableItemFactory : UiPreference {
    override lateinit var title: String
    override var summary: String? = null
    override var enable: Boolean = true
    override var onClickListener: (Context) -> Boolean = { true }
}

class UiChangeableItemFactory<T> : UiChangeablePreference<T> {
    override lateinit var title: String
    override var summary: String? = null
    override var enable: Boolean = true
    override var onClickListener: (Context) -> Boolean = { true }
    override val value: MutableLiveData<T?> = MutableLiveData()
}

class UiSwitchItemFactory : UiSwitchPreference {
    override lateinit var title: String
    override var summary: String? = null
    override var enable: Boolean = true
    override var onClickListener: (Context) -> Boolean = {
        val boolean = value.value
        if (boolean == null) {
            value.value = true
        } else {
            value.value = !boolean
        }
        true
    }
    override val value: MutableLiveData<Boolean> = MutableLiveData()
}