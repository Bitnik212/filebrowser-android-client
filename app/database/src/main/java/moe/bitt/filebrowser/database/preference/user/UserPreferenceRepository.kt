package moe.bitt.filebrowser.database.preference.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import moe.bitt.filebrowser.common.extensions.isEmpty
import moe.bitt.filebrowser.common.interfaces.preferences.DataStore
import moe.bitt.filebrowser.common.model.preferens.UserPreferences
import java.time.LocalDateTime
import java.time.ZoneOffset

class UserPreferenceRepository (private val context: Context): DataStore<UserPreferences> {

    companion object {
        const val PREFERENCES_NAME = "user_prefs"
    }

    private val Context.dataStore by preferencesDataStore(
        name = PREFERENCES_NAME
    )
    private val prefsKey = UserPreferenceKeys
    private val zoneOffset: ZoneOffset = ZoneOffset.UTC

    override suspend fun update(model: UserPreferences): Flow<UserPreferences> {
        context.dataStore.edit {
            it[prefsKey.ID] = model.id?: -1
            it[prefsKey.ISSUER_NAME] = model.siteName?: ""
            it[prefsKey.HOSTNAME] = model.hostname?: ""
            it[prefsKey.LOGIN] = model.login?: ""
            it[prefsKey.PASSWORD] = model.password?: ""
            it[prefsKey.TOKEN] = model.token?: ""
            it[prefsKey.TOKEN_EXPIRE_IN] = model.tokenExpireIn?.toEpochSecond(zoneOffset)?: 0
        }
        return get()
    }

    override suspend fun delete() {
        context.dataStore.edit {
            it[prefsKey.ID] = -1
            it[prefsKey.ISSUER_NAME] = ""
            it[prefsKey.HOSTNAME] = ""
            it[prefsKey.LOGIN] = ""
            it[prefsKey.PASSWORD] = ""
            it[prefsKey.TOKEN] = ""
            it[prefsKey.TOKEN_EXPIRE_IN] = -1
        }
    }

    override fun get(): Flow<UserPreferences> {
       return flow {
           context.dataStore.data.map {
               emit(
                   UserPreferences(
                       id = it[prefsKey.ID],
                       siteName = it[prefsKey.ISSUER_NAME],
                       hostname = it[prefsKey.HOSTNAME],
                       login = it[prefsKey.LOGIN],
                       password = it[prefsKey.PASSWORD],
                       token = it[prefsKey.TOKEN],
                       tokenExpireIn = handleTokenExpire(it[prefsKey.TOKEN_EXPIRE_IN])
                   )
               )
           }
       }
    }

    override suspend fun createIfNotExist() {
        get().collect { model ->
            if (!model.isEmpty())
                context.dataStore.edit {
                    it[prefsKey.ID] = -1
                    it[prefsKey.ISSUER_NAME] = ""
                    it[prefsKey.HOSTNAME] = ""
                    it[prefsKey.LOGIN] = ""
                    it[prefsKey.PASSWORD] = ""
                    it[prefsKey.TOKEN] = ""
                    it[prefsKey.TOKEN_EXPIRE_IN] = -1
                }
        }
    }

    private fun handleTokenExpire(tokenExpireIn: Long?): LocalDateTime? {
        tokenExpireIn?: return null
        return LocalDateTime.ofEpochSecond(tokenExpireIn, 0, zoneOffset)
    }
}