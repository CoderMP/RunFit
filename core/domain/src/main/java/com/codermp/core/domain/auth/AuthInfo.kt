package com.codermp.core.domain.auth

/**
 * Data class that models base-level, required authentication information.
 * @param accessToken The access token used to authenticate with the server.
 * @param refreshToken The refresh token used to refresh the access token.
 * @param userId The user id of the user.
 */
data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)