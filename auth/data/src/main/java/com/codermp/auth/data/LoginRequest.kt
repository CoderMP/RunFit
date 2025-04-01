package com.codermp.auth.data

import kotlinx.serialization.Serializable

/**
 * Data class that models a login request.
 * @param email The email address of the user.
 * @param password The password of the user.
 */
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)