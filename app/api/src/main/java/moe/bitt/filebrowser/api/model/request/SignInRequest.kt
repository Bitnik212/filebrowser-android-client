package moe.bitt.filebrowser.api.model.request

data class SignInRequest(
    val username: String,
    val password: String,
    val recaptcha: String = ""
)
