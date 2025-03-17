plugins {
    alias(libs.plugins.runfit.android.library)
    alias(libs.plugins.runfit.jvm.ktor)
    alias(libs.plugins.runfit.android.junit5)
}

android {
    namespace = "com.codermp.run.network"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.data)
}