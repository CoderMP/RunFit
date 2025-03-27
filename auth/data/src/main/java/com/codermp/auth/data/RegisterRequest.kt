package com.codermp.auth.data

import kotlinx.serialization.Serializable

/**
 * Data class representing a registration request.
 *
 * @param email The email address of the user.
 * @param password The password of the user.
 */
@Serializable
data class RegisterRequest(
    val email: String,
    val password: String
)