package org.ferredoxin.ferredoxinui.common.base

interface TitleAble {
    var title: String
    var titleProvider: ResourceProvider<String>
}