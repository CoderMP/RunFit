
plugins {
    alias(libs.plugins.runfit.android.library)
    alias(libs.plugins.runfit.android.junit5)
}

android {
    namespace = "com.codermp.core.android_test"
}

dependencies {
    implementation(projects.auth.data)
    implementation(projects.core.domain)
    api(projects.core.test)

    implementation(libs.ktor.client.mock)
    implementation(libs.bundles.ktor)
    implementation(libs.coroutines.test)
}