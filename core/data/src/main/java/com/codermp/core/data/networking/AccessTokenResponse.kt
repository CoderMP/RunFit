package com.codermp.core.data.networking

import kotlinx.serialization.Serializable

/**
 * Data class representing a response from a request to refresh an access token.
 * @param accessToken The new access token.
 * @param expirationTimestamp The timestamp when the access token expires.
 */
@Serializable
data class AccessTokenResponse(
    val accessToken: String,
    val expirationTimestamp: String
)