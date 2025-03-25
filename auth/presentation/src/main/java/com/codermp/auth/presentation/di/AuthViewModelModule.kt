package com.codermp.auth.presentation.di

import com.codermp.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module that exposes the viewmodels used within the auth feature's presentation module.
 */
val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
}