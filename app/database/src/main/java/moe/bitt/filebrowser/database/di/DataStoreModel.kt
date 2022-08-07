package moe.bitt.filebrowser.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import moe.bitt.filebrowser.common.interfaces.preferences.DataStore
import moe.bitt.filebrowser.common.model.preferens.AppPreferences
import moe.bitt.filebrowser.common.model.preferens.UserPreferences
import moe.bitt.filebrowser.database.preference.app.AppPreferenceRepository
import moe.bitt.filebrowser.database.preference.user.UserPreferenceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModel {

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(@ApplicationContext context: Context): DataStore<UserPreferences> {
        return UserPreferenceRepository(context)
    }

    @Provides
    @Singleton
    fun provideAppPreferenceRepository(@ApplicationContext context: Context): DataStore<AppPreferences> {
        return AppPreferenceRepository(context)
    }

}