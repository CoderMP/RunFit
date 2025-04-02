package com.codermp.core.data.di

import com.codermp.core.data.auth.EncryptedSessionStorage
import com.codermp.core.data.networking.HttpClientFactory
import com.codermp.core.domain.auth.SessionStorage
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module for providing dependencies related to the core data package.
 */
val coreDataModule = module {
    single {
        HttpClientFactory(get()).build(CIO.create())
    }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}