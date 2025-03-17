pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "RunFit"
include(":app")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":core:data")
include(":core:domain")
include(":core:database")
include(":core:notification")
include(":auth:data")
include(":auth:presentation")
include(":auth:domain")
include(":run:data")
include(":run:presentation")
include(":run:network")
include(":run:domain")
include(":run:location")
