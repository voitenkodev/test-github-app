@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "../../config/gradle/build-scripts/android.gradle")
apply(from = "../../config/gradle/build-scripts/compose.gradle")

android {
    namespace = "com.voitenko.testgithubapp.designsystem"
}

dependencies {
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
}