plugins {
    alias(libs.plugins.runfit.android.feature.ui)
    alias(libs.plugins.runfit.android.junit5)
    alias(libs.plugins.mapsplatform.secrets.plugin)
}

android {
    namespace = "com.codermp.run.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.google.maps.android.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.run.domain)
}