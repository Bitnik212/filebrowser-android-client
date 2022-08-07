package moe.bitt.filebrowser.common.model.api

import org.json.JSONObject

data class JWToken(
    val header: JSONObject,
    val body: JSONObject,
    val signature: String
)
