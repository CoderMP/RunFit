package com.codermp.auth.domain

import com.codermp.core.domain.utils.DataError
import com.codermp.core.domain.utils.EmptyResult

/**
 * Interface for the authentication repository.
 */
interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
}