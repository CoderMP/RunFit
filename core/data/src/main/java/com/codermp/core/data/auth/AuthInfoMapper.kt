package com.codermp.core.data.auth

import com.codermp.core.domain.auth.AuthInfo

/**
 * Mapper function that converts an [AuthInfo] to an [AuthInfoSerializable].
 * @return The converted [AuthInfoSerializable].
 */
fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}

/**
 * Mapper function that converts an [AuthInfoSerializable] to an [AuthInfo].
 * @return The converted [AuthInfo].
 */
fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}