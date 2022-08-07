package moe.bitt.filebrowser.common.extensions

import moe.bitt.filebrowser.common.model.preferens.UserPermissions

fun UserPermissions.isEmpty(): Boolean {
    return this.admin == null
            && this.create == null
            && this.delete == null
            && this.download == null
            && this.execute == null
            && this.modify == null
            && this.rename == null
            && this.share == null
}