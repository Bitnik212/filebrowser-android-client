package moe.bitt.filebrowser.database.preference.user

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferenceKeys {
    val ID = intPreferencesKey("ID")
    val ISSUER_NAME = stringPreferencesKey("ISSUER_NAME")
    val HOSTNAME = stringPreferencesKey("HOSTNAME")
    val LOGIN = stringPreferencesKey("LOGIN")
    val PASSWORD = stringPreferencesKey("PASSWORD")
    val TOKEN = stringPreferencesKey("TOKEN")
    val TOKEN_EXPIRE_IN = longPreferencesKey("TOKEN_EXPIRE_IN")
}