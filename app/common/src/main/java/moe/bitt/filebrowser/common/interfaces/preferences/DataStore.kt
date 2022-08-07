package moe.bitt.filebrowser.common.interfaces.preferences

import kotlinx.coroutines.flow.Flow

interface DataStore <T> {

    suspend fun createIfNotExist()

    suspend fun update(model: T): Flow<T>

    suspend fun delete()

    fun get(): Flow<T>
}