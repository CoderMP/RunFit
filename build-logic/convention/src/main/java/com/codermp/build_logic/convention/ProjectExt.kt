package com.codermp.build_logic.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Extension function to allow for simpler access to the project's version catalog
 */
val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")