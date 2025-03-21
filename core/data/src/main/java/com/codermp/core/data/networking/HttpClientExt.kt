package com.codermp.core.data.networking

import com.codermp.core.data.BuildConfig
import com.codermp.core.domain.utils.DataError
import com.codermp.core.domain.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.CancellationException
import kotlinx.serialization.SerializationException

/**
 * Function that is responsible for making a get request to the API.
 * @param route The route to be used for the request.
 * @param queryParameters The query parameters to be used for the request.
 * @return The result of the request.
 */
suspend inline fun <reified Response: Any> HttpClient.get(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError.Network> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

/**
 * Function that is responsible for making a post request to the API.
 * @param route The route to be used for the request.
 * @param body The body to be used for the request.
 * @return The result of the request.
 */
suspend inline fun <reified Request, reified Response: Any> HttpClient.post(
    route: String,
    body: Request
): Result<Response, DataError.Network> {
    return safeCall {
        post {
            url(constructRoute(route))
            setBody(body)
        }
    }
}

/**
 * Function that is responsible for making a delete request to the API.
 * @param route The route to be used for the request.
 * @param queryParameters The query parameters to be used for the request.
 * @return The result of the request.
 */
suspend inline fun <reified Response: Any> HttpClient.delete(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError.Network> {
    return safeCall {
        delete {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

/**
 * Function that is responsible for making a safe call to the API.
 * @param execute The function that is responsible for making the call.
 * @return The result of the call.
 */
suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.Network> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.SERIALIZATION)
    } catch(e: Exception) {
        if(e is CancellationException) throw e
        e.printStackTrace()
        return Result.Error(DataError.Network.UNKNOWN)
    }

    return responseToResult(response)
}

/**
 * Function that is responsible for converting the [HttpResponse] to a [Result].
 * @param response The [HttpResponse] to be converted.
 * @return The converted [Result].
 */
suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Network> {
    return when(response.status.value) {
        in 200..299 -> Result.Success(response.body<T>())
        401 -> Result.Error(DataError.Network.UNAUTHORIZED)
        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(DataError.Network.CONFLICT)
        413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }
}

/**
 * Function that is responsible for constructing the URL route.
 * @param route The route to be constructed.
 * @return The constructed URL route.
 */
fun constructRoute(route: String): String {
    return when {
        route.contains(BuildConfig.BASE_URL) -> route
        route.startsWith("/") -> BuildConfig.BASE_URL + route
        else -> BuildConfig.BASE_URL + "/$route"
    }
}