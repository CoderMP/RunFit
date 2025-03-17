plugins {
    alias(libs.plugins.runfit.android.library)
    alias(libs.plugins.runfit.android.room)
    alias(libs.plugins.runfit.android.junit5)
}

android {
    namespace = "com.codermp.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}