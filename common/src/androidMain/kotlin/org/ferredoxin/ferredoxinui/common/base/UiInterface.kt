package org.ferredoxin.ferredoxinui.common.base

import android.content.Context
import android.graphics.drawable.Drawable

actual interface UiAboutItem : UiDescription, TitleAble {
    val icon: (Context) -> Drawable
}
