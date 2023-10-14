@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.library)
    alias(libs.plugins.kapt)
}

apply(from = "../../config/gradle/build-scripts/kotlin.gradle")

dependencies {

    // Std-libs
    implementation(libs.coroutines)

    // Hilt
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}