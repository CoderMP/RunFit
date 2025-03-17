plugins {
    alias(libs.plugins.runfit.android.feature.ui)
    alias(libs.plugins.runfit.android.junit5)
}

android {
    namespace = "com.codermp.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}