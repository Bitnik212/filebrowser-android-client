package moe.bitt.filebrowser.common.model.preferens

data class AppPreferences(
    var locale: String?,
    var viewMode: String?,
    var permissions: UserPermissions,
    var commands: List<String>?,
    var lockPassword: Boolean?,
    var hideDotFiles: Boolean?,
    var formatDate: Boolean?
)
