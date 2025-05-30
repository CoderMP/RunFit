plugins {
    alias(libs.plugins.runfit.android.library)
    alias(libs.plugins.runfit.android.junit5)
}

android {
    namespace = "com.codermp.run.location"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.koin)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)

    implementation(projects.core.domain)
    implementation(projects.run.domain)
}