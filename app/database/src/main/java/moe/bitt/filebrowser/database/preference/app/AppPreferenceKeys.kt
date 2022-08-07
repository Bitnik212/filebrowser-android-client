package moe.bitt.filebrowser.database.preference.app

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object AppPreferenceKeys {
    val LOCALE = stringPreferencesKey("LOCALE")
    val VIEW_MODE = stringPreferencesKey("VIEW_MODE")
    val COMMANDS = stringSetPreferencesKey("COMMANDS")
    val LOCK_PASSWORD = booleanPreferencesKey("LOCK_PASSWORD")
    val HIDE_DOT_FILES = booleanPreferencesKey("HIDE_DOT_FILES")
    val DATE_FORMAT = booleanPreferencesKey("DATE_FORMAT")
    val PERMISSION_ADMIN = booleanPreferencesKey("PERMISSION_ADMIN")
    val PERMISSION_EXECUTE = booleanPreferencesKey("PERMISSION_EXECUTE")
    val PERMISSION_CREATE = booleanPreferencesKey("PERMISSION_CREATE")
    val PERMISSION_RENAME = booleanPreferencesKey("PERMISSION_RENAME")
    val PERMISSION_MODIFY = booleanPreferencesKey("PERMISSION_MODIFY")
    val PERMISSION_DELETE = booleanPreferencesKey("PERMISSION_DELETE")
    val PERMISSION_SHARE = booleanPreferencesKey("PERMISSION_SHARE")
    val PERMISSION_DOWNLOAD = booleanPreferencesKey("PERMISSION_DOWNLOAD")
}