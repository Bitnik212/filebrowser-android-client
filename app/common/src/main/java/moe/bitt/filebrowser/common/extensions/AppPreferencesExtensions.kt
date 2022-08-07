package moe.bitt.filebrowser.common.extensions

import moe.bitt.filebrowser.common.model.preferens.AppPreferences

fun AppPreferences.isEmpty(): Boolean {
    return this.formatDate == null
            && this.lockPassword == null
            && this.hideDotFiles == null
            && this.commands == null
            && this.permissions.isEmpty()
            && this.locale == null
            && this.viewMode == null
}