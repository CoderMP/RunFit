package com.codermp.core.domain.auth

/**
 * Interface that defines the different methods for interacting with session storage.
 */
interface SessionStorage {
    suspend fun get(): AuthInfo?
    suspend fun set(info: AuthInfo?)
}