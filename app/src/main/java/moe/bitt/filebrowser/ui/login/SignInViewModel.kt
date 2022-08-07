package moe.bitt.filebrowser.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import moe.bitt.filebrowser.api.repository.AuthRepository
import moe.bitt.filebrowser.common.interfaces.preferences.DataStore
import moe.bitt.filebrowser.common.model.preferens.AppPreferences
import moe.bitt.filebrowser.common.model.preferens.UserPreferences
import moe.bitt.filebrowser.common.utils.JWTParser
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val userDataStore: DataStore<UserPreferences>,
    private val appDataStore: DataStore<AppPreferences>
    ) : ViewModel() {

    private val TAG = this.javaClass.simpleName

    val signInState: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    fun signIn(url: String, username: String, password: String) {
        viewModelScope.launch {
            repository.signIn(url, username, password)
                .catch {
                    Log.e(TAG, "signIn: $it")
                    signInState.value = false
                }
                .collect {
                    signInState.value = true
                    val result = JWTParser.parse(it, url)
                    val userPrefs = result.first
                    userPrefs.login = username
                    userPrefs.password = password
                    savePrefs(userPrefs, result.second)
                }
        }
    }

    private fun savePrefs(user: UserPreferences, app: AppPreferences) {
        viewModelScope.launch {
            userDataStore.update(model = user)
        }
        viewModelScope.launch {
            appDataStore.update(model = app)
        }
    }

}