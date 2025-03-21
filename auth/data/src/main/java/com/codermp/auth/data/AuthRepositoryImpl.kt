package com.codermp.auth.data

import com.codermp.auth.domain.AuthRepository
import com.codermp.core.data.networking.post
import com.codermp.core.domain.utils.DataError
import com.codermp.core.domain.utils.EmptyResult
import io.ktor.client.HttpClient

/**
 * Implementation of the [AuthRepository] interface.
 *
 * @param httpClient The [HttpClient] to use for making requests.
 */
class AuthRepositoryImpl(
    private val httpClient: HttpClient,
): AuthRepository {
    /**
     * Function that sends a registration request for a new user with the provided email and password.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return An [EmptyResult] of [DataError.Network] as we're not expecting any data back from the API.
     */
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}