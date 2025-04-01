@file:OptIn(InternalAPI::class)

package com.codermp.core.android_test

import com.codermp.auth.data.LoginResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Stub data for a [LoginResponse].
 */
val loginResponseStub = LoginResponse(
    accessToken = "test-access-token",
    refreshToken = "test-refresh-token",
    accessTokenExpirationTimestamp = System.currentTimeMillis(),
    userId = "test-user-id"
)

/**
 * Mock implementation of [HttpClientEngine] for testing purposes.
 * @param dispatcher The [CoroutineDispatcher] to use for the mock engine.
 * @param mockEngineConfig The [MockEngineConfig] to use for the mock engine.
 * @return An instance of [TestMockEngine].
 */
class TestMockEngine(
    override val dispatcher: CoroutineDispatcher,
    private val mockEngineConfig: MockEngineConfig
): HttpClientEngine {

    val mockEngine = MockEngine(mockEngineConfig)

    override val coroutineContext: CoroutineContext
        get() = mockEngine.coroutineContext + dispatcher

    override val config: HttpClientEngineConfig
        get() = mockEngineConfig

    override suspend fun execute(data: HttpRequestData): HttpResponseData {
        return withContext(coroutineContext) {
            mockEngine.execute(data)
        }
    }

    override fun close() {
        mockEngine.close()
    }
}