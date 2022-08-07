package moe.bitt.filebrowser.common.utils

import moe.bitt.filebrowser.common.model.api.JWToken
import moe.bitt.filebrowser.common.model.preferens.AppPreferences
import moe.bitt.filebrowser.common.model.preferens.UserPermissions
import moe.bitt.filebrowser.common.model.preferens.UserPreferences
import moe.bitt.filebrowser.common.usecase.URLUseCase
import org.json.JSONObject
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

object JWTParser {

    private val zoneOffset = ZoneOffset.systemDefault().rules.getOffset(Instant.now())

    fun parse(token: String, url: String): Pair<UserPreferences, AppPreferences> {
        val hostname = URLUseCase(url).hostname
        val body = parseToken(token).body;
        val user = body.getJSONObject("user")
        val permissions = user.getJSONObject("perm")
        val userPrefs = UserPreferences(
            id = user.getInt("id"),
            siteName = body.getString("iss"),
            hostname = hostname,
            login = null,
            password = null,
            token = token,
            tokenExpireIn = LocalDateTime.ofEpochSecond(body.getLong("exp"), 0, zoneOffset)
        )
        val appPrefs = AppPreferences(
            locale = user.getString("locale"),
            viewMode = user.getString("viewMode"),
            permissions = UserPermissions(
                admin = permissions.getBoolean("admin"),
                execute = permissions.getBoolean("execute"),
                create = permissions.getBoolean("create"),
                rename = permissions.getBoolean("rename"),
                modify = permissions.getBoolean("modify"),
                delete = permissions.getBoolean("delete"),
                share = permissions.getBoolean("share"),
                download = permissions.getBoolean("download")
            ),
            lockPassword = user.getBoolean("lockPassword"),
            hideDotFiles = user.getBoolean("hideDotfiles"),
            formatDate = user.getBoolean("dateFormat"),
            commands = listOf()
        )
        return Pair(userPrefs, appPrefs)
    }

    private fun parseToken(token: String): JWToken {
        val raw = token.split('.')
        val decoder = Base64.getDecoder()
        val headerBA = decoder.decode(raw[0])
        val bodyBA = decoder.decode(raw[1])
        val signBA = raw[2]
        return JWToken(
            header = JSONObject(String(headerBA)),
            body = JSONObject(String(bodyBA)),
            signature = signBA
        )
    }
}