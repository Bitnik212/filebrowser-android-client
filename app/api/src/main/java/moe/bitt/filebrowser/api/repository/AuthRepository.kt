package moe.bitt.filebrowser.api.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import moe.bitt.filebrowser.api.model.request.SignInRequest
import moe.bitt.filebrowser.api.model.request.SignUpRequest
import moe.bitt.filebrowser.api.service.AuthService
import retrofit2.await

class AuthRepository(private val service: AuthService) {

    fun signIn(url: String, username: String, password: String): Flow<String> {
        return flow {
            emit(service.signIn(
                body = SignInRequest(username, password),
                url = url
            ).await())
        }
    }

    fun signUp(url: String, username: String, password: String): Flow<String> {
        return flow {
            emit(service.signUp(
                body = SignUpRequest(username, password),
                url = url
            ).await())
        }
    }

    fun renew(url: String, token: String): Flow<String> {
        return flow {
            emit(service.renew(token = token, url = url).await())
        }
    }

}