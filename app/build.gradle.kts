@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

apply(from = "../config/gradle/build-scripts/android.gradle")

android {
    defaultConfig {
        applicationId = "com.voitenko.testgithubapp"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    // Std-libs
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.coroutines)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.extensions)
    kapt(libs.hilt.extensions.compiler)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    // Compose third-party
    implementation(libs.activity.compose)
}