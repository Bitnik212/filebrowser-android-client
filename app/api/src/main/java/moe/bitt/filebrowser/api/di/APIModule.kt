package moe.bitt.filebrowser.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import moe.bitt.filebrowser.api.repository.AuthRepository
import moe.bitt.filebrowser.api.service.AuthService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    fun provideAuthRepository(service: AuthService): AuthRepository {
        return AuthRepository(service)
    }

}