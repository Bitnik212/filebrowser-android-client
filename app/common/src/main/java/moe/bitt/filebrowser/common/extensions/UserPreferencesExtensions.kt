package moe.bitt.filebrowser.common.extensions

import moe.bitt.filebrowser.common.model.preferens.UserPreferences

fun UserPreferences.isEmpty(): Boolean {
    return this.login == null && this.password == null && this.hostname == null
}