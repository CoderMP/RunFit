package com.codermp.build_logic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Extension function to configure the build types of the project.
 * @param commonExtension [CommonExtension] scope to apply configurations.
 * @param extensionType [ExtensionType] to configure the build types for.
 */
internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {
    commonExtension.run {
        buildFeatures {
            buildConfig = true
        }

        val apiKey = gradleLocalProperties(rootDir, providers).getProperty("API_KEY")

        when(extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(
                                apiKey = apiKey,
                                commonExtension = commonExtension
                            )
                        }
                        release {
                            configureReleaseBuildType(
                                apiKey = apiKey,
                                commonExtension = commonExtension
                            )
                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(
                                apiKey = apiKey,
                                commonExtension = commonExtension
                            )
                        }

                        release {
                            configureReleaseBuildType(
                                apiKey = apiKey,
                                commonExtension = commonExtension
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Extension function to configure the debug build type.
 * @param apiKey The API key to be used in the build.
 */
private fun BuildType.configureDebugBuildType(
    apiKey: String,
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://tasky.pl-coding.com\"")

    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}

/**
 * Extension function to configure the release build type.
 * @param apiKey The API key to be used in the build.
 * @param commonExtension [CommonExtension] scope to apply configurations.
 */
private fun BuildType.configureReleaseBuildType(
    apiKey: String,
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://tasky.pl-coding.com\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}