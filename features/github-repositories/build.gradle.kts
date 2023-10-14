@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.android)
}

apply(from = "../../config/gradle/build-scripts/android.gradle")
apply(from = "../../config/gradle/build-scripts/compose.gradle")

android {
    namespace = "com.voitenko.testgithubapp.features.githubrepositories"
}

dependencies {
    implementation(projects.common.designSystem)
    implementation(projects.common.architectureComponent)
    implementation(projects.domain.repositories)

    // Std-libs
    implementation(libs.immutable.collections)

    // Compose third-party
    implementation(libs.compose.navigation)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.tooling)
    implementation(libs.ui.graphics)
    implementation(libs.material3)

    // Pager
    implementation(libs.pager)
    implementation(libs.pager.compose)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
}