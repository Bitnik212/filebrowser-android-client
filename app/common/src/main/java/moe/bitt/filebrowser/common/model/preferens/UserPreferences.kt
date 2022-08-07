package moe.bitt.filebrowser.common.model.preferens

import java.time.LocalDateTime
import java.util.*

data class UserPreferences(
    var id: Int?,
    var siteName: String?,
    var hostname: String?,
    var login: String?,
    var password: String?,
    var token: String?,
    var tokenExpireIn: LocalDateTime?
)
