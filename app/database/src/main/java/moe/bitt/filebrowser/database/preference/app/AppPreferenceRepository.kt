package moe.bitt.filebrowser.database.preference.app

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import moe.bitt.filebrowser.common.extensions.isEmpty
import moe.bitt.filebrowser.common.interfaces.preferences.DataStore
import moe.bitt.filebrowser.common.model.preferens.AppPreferences
import moe.bitt.filebrowser.common.model.preferens.UserPermissions

class AppPreferenceRepository (private val context: Context): DataStore<AppPreferences> {

    companion object {
        const val PREFERENCES_NAME = "app_prefs"
    }

    private val Context.dataStore by preferencesDataStore(
        name = PREFERENCES_NAME
    )
    private val prefsKey = AppPreferenceKeys;

    override suspend fun createIfNotExist() {
        get().collect { model ->
            if (model.isEmpty()) {
                context.dataStore.edit {
                    it[prefsKey.HIDE_DOT_FILES] = false
                    it[prefsKey.DATE_FORMAT] = false
                    it[prefsKey.LOCK_PASSWORD] = false
                    it[prefsKey.VIEW_MODE] = ""
                    it[prefsKey.COMMANDS] = setOf()
                    it[prefsKey.LOCK_PASSWORD] = false
                    it[prefsKey.PERMISSION_DELETE] = false
                    it[prefsKey.PERMISSION_DOWNLOAD] = false
                    it[prefsKey.PERMISSION_SHARE] = false
                    it[prefsKey.PERMISSION_RENAME] = false
                    it[prefsKey.PERMISSION_MODIFY] = false
                    it[prefsKey.PERMISSION_CREATE] = false
                    it[prefsKey.PERMISSION_EXECUTE] = false
                    it[prefsKey.PERMISSION_ADMIN] = false
                }
            }
        }

    }

    override suspend fun update(model: AppPreferences): Flow<AppPreferences> {
        context.dataStore.edit {
            it[prefsKey.HIDE_DOT_FILES] = model.hideDotFiles?: false
            it[prefsKey.DATE_FORMAT] = model.formatDate?: false
            it[prefsKey.LOCK_PASSWORD] = model.lockPassword?: false
            it[prefsKey.VIEW_MODE] = model.viewMode?: ""
            it[prefsKey.COMMANDS] = model.commands?.toSet() ?: setOf()
            it[prefsKey.LOCK_PASSWORD] = model.lockPassword?: false
            it[prefsKey.PERMISSION_DELETE] = model.permissions.delete?: false
            it[prefsKey.PERMISSION_DOWNLOAD] = model.permissions.download?: false
            it[prefsKey.PERMISSION_SHARE] = model.permissions.share?: false
            it[prefsKey.PERMISSION_RENAME] = model.permissions.rename?: false
            it[prefsKey.PERMISSION_MODIFY] = model.permissions.modify?: false
            it[prefsKey.PERMISSION_CREATE] = model.permissions.create?: false
            it[prefsKey.PERMISSION_EXECUTE] = model.permissions.execute?: false
            it[prefsKey.PERMISSION_ADMIN] = model.permissions.admin?: false
        }
        return get()
    }

    override suspend fun delete() {
        context.dataStore.edit {
            it[prefsKey.HIDE_DOT_FILES] = false
            it[prefsKey.DATE_FORMAT] = false
            it[prefsKey.LOCK_PASSWORD] = false
            it[prefsKey.VIEW_MODE] = ""
            it[prefsKey.COMMANDS] = setOf()
            it[prefsKey.LOCK_PASSWORD] = false
            it[prefsKey.PERMISSION_DELETE] = false
            it[prefsKey.PERMISSION_DOWNLOAD] = false
            it[prefsKey.PERMISSION_SHARE] = false
            it[prefsKey.PERMISSION_RENAME] = false
            it[prefsKey.PERMISSION_MODIFY] = false
            it[prefsKey.PERMISSION_CREATE] = false
            it[prefsKey.PERMISSION_EXECUTE] = false
            it[prefsKey.PERMISSION_ADMIN] = false
        }
    }

    override fun get(): Flow<AppPreferences> {
        return flow {
            context.dataStore.data.map {
                emit(
                    AppPreferences(
                        locale = it[prefsKey.LOCALE],
                        viewMode = it[prefsKey.VIEW_MODE],
                        permissions = UserPermissions(
                            admin = it[prefsKey.PERMISSION_ADMIN],
                            execute = it[prefsKey.PERMISSION_EXECUTE],
                            create = it[prefsKey.PERMISSION_CREATE],
                            rename = it[prefsKey.PERMISSION_RENAME],
                            modify = it[prefsKey.PERMISSION_MODIFY],
                            delete = it[prefsKey.PERMISSION_DELETE],
                            share = it[prefsKey.PERMISSION_SHARE],
                            download = it[prefsKey.PERMISSION_DOWNLOAD]
                        ),
                        commands = null,
                        lockPassword = it[prefsKey.LOCK_PASSWORD],
                        hideDotFiles = it[prefsKey.HIDE_DOT_FILES],
                        formatDate = it[prefsKey.DATE_FORMAT]
                    )
                )
            }
        }
    }

}