@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

apply(from = "../../config/gradle/build-scripts/android.gradle")
apply(from = "../../config/gradle/build-scripts/kotlin.gradle")

android {
    namespace = "com.voitenko.testgithubapp.domain.repositories"
}

dependencies {
    implementation(projects.data.remote)

    // Std-libs
    implementation(libs.coroutines)
    implementation(libs.serialization)

    // Pager
    implementation(libs.pager)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.extensions)
    kapt(libs.hilt.extensions.compiler)
}