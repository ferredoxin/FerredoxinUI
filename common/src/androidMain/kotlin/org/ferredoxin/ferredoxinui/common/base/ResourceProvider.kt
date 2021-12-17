package org.ferredoxin.ferredoxinui.common.base

import android.content.Context

actual interface ResourceProvider<T> {
    fun getValue(context: Context): T
}

actual class DirectResourceProvider<T> actual constructor(val value: T) : ResourceProvider<T> {
    override inline fun getValue(context: Context) = value
}

actual class FunctionalResourceProvider<T>(val function: (Context) -> T) : ResourceProvider<T> {
    override inline fun getValue(context: Context): T {
        return function.invoke(context)
    }
}