plugins {
    alias(libs.plugins.runfit.android.library)
    alias(libs.plugins.runfit.jvm.ktor)
    alias(libs.plugins.runfit.android.junit5)
}

android {
    namespace = "com.codermp.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}