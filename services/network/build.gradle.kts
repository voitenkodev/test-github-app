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
    namespace = "com.voitenko.testgithubapp.services.network"

    buildTypes {
        all {
            buildConfigField(name = "API_URL", type = "String", value = "\"https://api.github.com\"")
        }
    }
}

dependencies {
    implementation(projects.common.architectureComponent)

    // Std-libs
    implementation(libs.serialization)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.logger)
    implementation(libs.retrofit.convertor)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}