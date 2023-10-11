@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

apply(from = "../../config/gradle/build-scripts/android.gradle")

android {
    namespace = "com.voitenko.testgithubapp.features.repositorydetails"
}

dependencies {
    implementation(projects.common.designSystem)
    implementation(projects.common.architectureComponent)

    // Compose third-party
    implementation(libs.compose.navigation)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.extensions)
    kapt(libs.hilt.extensions.compiler)
}