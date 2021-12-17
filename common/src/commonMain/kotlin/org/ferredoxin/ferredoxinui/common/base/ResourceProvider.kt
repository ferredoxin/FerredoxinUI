package org.ferredoxin.ferredoxinui.common.base

expect interface ResourceProvider<T>

expect class DirectResourceProvider<T> constructor(value: T) : ResourceProvider<T>

expect class FunctionalResourceProvider<T> : ResourceProvider<T>