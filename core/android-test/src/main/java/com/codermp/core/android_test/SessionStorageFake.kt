package com.codermp.core.android_test

import com.codermp.core.domain.auth.AuthInfo
import com.codermp.core.domain.auth.SessionStorage

/**
 * Fake implementation of [SessionStorage] for testing purposes.
 */
class SessionStorageFake: SessionStorage {
    private var authInfo: AuthInfo? = null

    override suspend fun get(): AuthInfo? {
        return authInfo
    }

    override suspend fun set(info: AuthInfo?) {
        authInfo = info
    }
}