plugins {
    alias(libs.plugins.runfit.android.library)
    alias(libs.plugins.runfit.android.junit5)
}

android {
    namespace = "com.codermp.core.notification"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.ui)
    implementation(projects.core.presentation.designsystem)
}