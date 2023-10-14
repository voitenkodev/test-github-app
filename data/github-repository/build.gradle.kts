@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.serialization)
}

apply(from = "../../config/gradle/build-scripts/android.gradle")

android {
    namespace = "com.voitenko.testgithubapp.data.remote"
}

dependencies {
    implementation(projects.services.network)
    implementation(projects.domain.repositories)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.extensions)
    kapt(libs.hilt.extensions.compiler)
}