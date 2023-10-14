@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.android)
}

apply(from = "../config/gradle/build-scripts/android.gradle")
apply(from = "../config/gradle/build-scripts/compose.gradle")

android.defaultConfig {
    applicationId = "com.voitenko.testgithubapp"
}

dependencies {
    implementation(projects.common.designSystem)
    implementation(projects.common.architectureComponent)

    implementation(projects.data.githubRepository)

    // Features
    implementation(projects.features.githubRepositories)
    implementation(projects.features.repositoryDetails)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)

    // Compose third-party
    implementation(libs.activity.compose)
}