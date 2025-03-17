plugins {
    alias(libs.plugins.runfit.jvm.library)
    alias(libs.plugins.runfit.jvm.junit5)
}

dependencies {
    implementation(projects.core.domain)
}