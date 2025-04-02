package com.codermp.core.data.networking

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to refresh an access token.
 * @param refreshToken The refresh token to use for the request.
 * @param userId The user ID to use for the request.
 */
@Serializable
data class AccessTokenRequest(
    val refreshToken: String,
    val userId: String
)