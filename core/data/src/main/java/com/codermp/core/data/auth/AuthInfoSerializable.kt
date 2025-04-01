package com.codermp.core.data.auth

import kotlinx.serialization.Serializable

/**
 * Serializable data class that models [AuthInfo] objects.
 * @property accessToken The access token for the user.
 * @property refreshToken The refresh token for the user.
 * @property userId The user ID of the user.
 */
@Serializable
data class AuthInfoSerializable(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)