package com.codermp.core.domain.auth

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Object class that contains functions related to timing.
 */
object Timer {
    /**
     * Function that returns a [Flow] containing the elapsed time in milliseconds.
     * @return A [Flow] that emits the elapsed time in milliseconds
     */
    fun timeAndEmit(): Flow<Duration> {
        return flow {
            var lastEmitTime = System.currentTimeMillis()

            while(true) {
                delay(timeMillis = 500L)
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - lastEmitTime

                emit(elapsedTime.milliseconds)
                lastEmitTime = currentTime
            }
        }
    }
}