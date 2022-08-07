package moe.bitt.filebrowser.common.model.preferens

data class UserPermissions(
    var admin: Boolean?,
    var execute: Boolean?,
    var create: Boolean?,
    var rename: Boolean?,
    var modify: Boolean?,
    var delete: Boolean?,
    var share: Boolean?,
    var download: Boolean?,
)
