package com.codermp.core.data.auth

import android.content.SharedPreferences
import com.codermp.core.domain.auth.AuthInfo
import com.codermp.core.domain.auth.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import androidx.core.content.edit

/**
 * Implementation of [SessionStorage] that uses [SharedPreferences] to store [AuthInfo] objects.
 * @param sharedPreferences The [SharedPreferences] instance to use for storage.
 */
class EncryptedSessionStorage(
    private val sharedPreferences: SharedPreferences
): SessionStorage {
    /**
     * Suspend function that retrieves the [AuthInfo] object from [SharedPreferences].
     * @return The [AuthInfo] object if found, otherwise null.
     */
    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(KEY_AUTH_INFO, null)

            json?.let {
                Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo()
            }
        }
    }

    /**
     * Suspend function that stores the [AuthInfo] object in [SharedPreferences].
     * @param info The [AuthInfo] object to store.
     */
    override suspend fun set(info: AuthInfo?) {
        withContext(Dispatchers.IO) {
            if (info == null) {
                sharedPreferences.edit { remove(KEY_AUTH_INFO) }
                return@withContext
            }

            val json = Json.encodeToString(info.toAuthInfoSerializable())
            sharedPreferences
                .edit(commit = true) {
                    putString(KEY_AUTH_INFO, json)
                }
        }
    }

    /**
     * Companion object containing the key(s) used to store [AuthInfo] objects in [SharedPreferences].
     */
    companion object {
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }
}