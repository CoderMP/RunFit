package com.codermp.build_logic.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

/**
 * Extension function to add the UI-related dependencies to the project.
 * Specifically configures:
 * - [core:presentation:ui]
 * - [core:presentation:designsystem]
 * - [koin.compose]
 * - [compose] dependency bundle
 * - [compose.debug] dependency bundle
 * - [androidx.compose.ui.test.junit4]
 * @param project The [Project] to add the dependencies to.
 */
fun DependencyHandlerScope.addUiLayerDependencies(project: Project) {
    "implementation"(project(":core:presentation:ui"))
    "implementation"(project(":core:presentation:designsystem"))

    "implementation"(project.libs.findBundle("koin.compose").get())
    "implementation"(project.libs.findBundle("compose").get())
    "debugImplementation"(project.libs.findBundle("compose.debug").get())
    "androidTestImplementation"(project.libs.findLibrary("androidx.compose.ui.test.junit4").get())
}