package com.codermp.auth.data

import kotlinx.serialization.Serializable

/**
 * Data class that models the response when logging in to the app.
 * @property accessToken The access token for the user.
 * @property refreshToken The refresh token for the user.
 * @property accessTokenExpirationTimestamp The expiration timestamp for the access token.
 * @property userId The user ID of the user.
 */
@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpirationTimestamp: Long,
    val userId: String
)