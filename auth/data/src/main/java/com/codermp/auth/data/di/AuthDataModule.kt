package com.codermp.auth.data.di

import com.codermp.auth.data.AuthRepositoryImpl
import com.codermp.auth.data.EmailPatternValidator
import com.codermp.auth.domain.AuthRepository
import com.codermp.auth.domain.PatternValidator
import com.codermp.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module for authentication data related dependencies.
 */
val authDataModule = module {
    single<PatternValidator> { EmailPatternValidator }

    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}