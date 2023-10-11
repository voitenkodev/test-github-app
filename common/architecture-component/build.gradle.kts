@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "../../config/gradle/build-scripts/android.gradle")

android {
    namespace = "com.voitenko.testgithubapp.architecturecomponent"
}